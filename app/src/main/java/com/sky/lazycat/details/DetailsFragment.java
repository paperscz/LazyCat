package com.sky.lazycat.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sky.lazycat.R;
import com.sky.lazycat.data.zhihucontent.ZhihuDailyContent;
import com.sky.lazycat.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuetu-develop on 2017/9/4.
 */

public class DetailsFragment extends Fragment implements DetailsContract.View{

    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.nested_scroll_view) NestedScrollView mScrollView;
    @BindView(R.id.web_view) WebView mWebView;
    @BindView(R.id.image_view) ImageView mImageView;

    private DetailsContract.Presenter mPresenter;
    private int mId;
    private String mTitle;
    private Unbinder mUnbinder;
    private boolean mIsNightMode = false;

    public DetailsFragment() {
        // Requires an empty constructor.
    }

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getActivity().getIntent().getIntExtra(DetailsActivity.KEY_ARTICLE_ID, -1);
        mTitle = getActivity().getIntent().getStringExtra(DetailsActivity.KEY_ARTICLE_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        mUnbinder = ButterKnife.bind(this,view);
        setTitle(mTitle);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScrollView.smoothScrollTo(0,0);
            }
        });
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        mPresenter.loadZhihuDailyContent(mId);
    }

    private void setTitle(String title) {
        mToolbarLayout.setTitle(title);
        mToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        mToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public void showMessage(@StringRes int stringRes) {
        ToastUtils.showShort(getActivity(),stringRes);
    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void showZhihuDailyContent(@NonNull ZhihuDailyContent content) {
        if (content.getBody() != null) {
            String result = content.getBody();
            result = result.replace("<div class=\"img-place-holder\">", "");
            result = result.replace("<div class=\"headline\">", "");
            String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";
            String theme = "<body className=\"\" onload=\"onLoaded()\">";
            if (mIsNightMode) {
                theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
            }
            result = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                    + "<head>\n"
                    + "\t<meta charset=\"utf-8\" />"
                    + css
                    + "\n</head>\n"
                    + theme
                    + result
                    + "</body></html>";
            mWebView.loadDataWithBaseURL("x-data://base", result,"text/html","utf-8",null);
        } else {
            mWebView.loadDataWithBaseURL("x-data://base", content.getShare_url(),"text/html","utf-8",null);
        }
        setCover(content.getImage());
    }

    private void setCover(@Nullable String url) {
        if (url != null) {
            Glide.with(getContext())
                    .load(url)
                    .asBitmap()
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .error(R.drawable.placeholder)
                    .into(mImageView);
        } else {
            mImageView.setImageResource(R.drawable.placeholder);
        }
    }
}
