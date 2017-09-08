package com.sky.lazycat.details;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sky.lazycat.R;
import com.sky.lazycat.data.zhihucontent.ZhihuDailyContent;
import com.sky.lazycat.util.CustomTabsHelper;
import com.sky.lazycat.util.HtmlUtil;
import com.sky.lazycat.util.InfoConstants;
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
    @BindView(R.id.tv_source) TextView mSourceText;

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
        initViews(view);
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
        DetailsActivity activity = (DetailsActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);

        mWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        //设置 缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        mWebView.getSettings().setDomStorageEnabled(true);

        // Show the images or not.
        //mWebView.getSettings().setBlockNetworkImage(PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(InfoConstants.KEY_NO_IMG_MODE, false));

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                CustomTabsHelper.openUrl(getContext(), url);
                return true;
            }

        });
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mNestedScrollView.setElevation(0);
//            mWebView.setElevation(0);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
        }
        return true;
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

        mSourceText.setText(content.getImageSource());
        String htmlData = HtmlUtil.createHtmlData(content);
        mWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);

//        if (content.getBody() != null) {
//            String result = content.getBody();
//            result = result.replace("<div class=\"img-place-holder\">", "");
//            result = result.replace("<div class=\"headline\">", "");
//            String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";
//            String theme = "<body className=\"\" onload=\"onLoaded()\">";
//            if (mIsNightMode) {
//                theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
//            }
//            result = "<!DOCTYPE html>\n"
//                    + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
//                    + "<head>\n"
//                    + "\t<meta charset=\"utf-8\" />"
//                    + css
//                    + "\n</head>\n"
//                    + theme
//                    + result
//                    + "</body></html>";
//            mWebView.loadDataWithBaseURL("x-data://base", result,"text/html","utf-8",null);
//        } else {
//            mWebView.loadDataWithBaseURL("x-data://base", content.getShare_url(),"text/html","utf-8",null);
//        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
