package com.sky.lazycat.timeline.zhihu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sky.lazycat.R;
import com.sky.lazycat.data.zhihu.Zhihu;
import com.sky.lazycat.details.DetailsActivity;
import com.sky.lazycat.interfaces.OnViewScrollListener;
import com.sky.lazycat.retrofit.RetrofitService;
import com.sky.lazycat.util.GlideImageLoader;
import com.sky.lazycat.util.DateFormatUtil;
import com.sky.lazycat.util.ShareUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuetu-develop on 2017/8/24.
 */

public class ZhihuFragment extends Fragment implements ZhihuDataContract.View{

   // @BindView(R.id.banner)
    private Banner mBanner;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_zhihu) RecyclerView mRecyclerView;
//    private List<String> bannerImages;
//    private List<String> bannerTitles;
//    private List<Zhihu.TopStoriesBean> listTopStrories;
    private FloatingActionButton mFab;
    private ZhihuDataContract.Presenter mPresenter;
    private boolean mIsFirstLoad = true;
    private Unbinder mUnbinder;
    private ZhihuQuickAdapter mZhihuQuickAdapter;
    private String date;
    private List<Zhihu.StoriesBean> listStories;
    private boolean mHide = false;
    OnViewScrollListener onViewScrollListener;

    public ZhihuFragment(){}

    public static ZhihuFragment newInstance() {
        return new ZhihuFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onViewScrollListener = (OnViewScrollListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date = DateFormatUtil.formatZhihuDailyDateToString(System.currentTimeMillis());
        listStories = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_zhihu,container,false);
        mUnbinder = ButterKnife.bind(this,view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        // 正常滚动banner 简单使用banner
        // mBanner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        //setBanner();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadZhihu(true,true,date);
                setLoadingIndicator(true);
            }
        });

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0 && !mHide){
                    onViewScrollListener.onViewScrolled(false);
                    mHide = true;
                    mFab.hide();
                } else if(dy < 0 && mHide){
                    onViewScrollListener.onViewScrolled(true);
                    mHide = false;
                    mFab.show();
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        setLoadingIndicator(mIsFirstLoad);
        if(mIsFirstLoad){
            // 首次访问加载带topstories数据
            mPresenter.loadZhihu(true,true,date);
            mIsFirstLoad = false;
        } else {
            //mPresenter.loadZhihu(false,true,date);
        }
    }

    @Override
    public void setPresenter(ZhihuDataContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {  }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showResult(List<Zhihu.StoriesBean> listStoriesNew, List<Zhihu.TopStoriesBean> listTopStories,List<String> listTopImg,List<String> listTopTitle) {
        listStories.clear();
        listStories = listStoriesNew;
        if(mZhihuQuickAdapter == null){
            mZhihuQuickAdapter = new ZhihuQuickAdapter(listStories);
            mZhihuQuickAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            mRecyclerView.setAdapter(mZhihuQuickAdapter);

            mZhihuQuickAdapter.addHeaderView(getHeaderView(listTopImg,listTopTitle));
            setAdapterClick(listTopStories);
        } else {
            mZhihuQuickAdapter.setNewData(listStories);
        }
        //setMzBanner(listTopStories);
    }

    private void setAdapterClick(final List<Zhihu.TopStoriesBean> listTopStories) {

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                DetailsActivity.newIntent(getActivity(),listTopStories.get(position).getId(),listTopStories.get(position).getTitle());
            }
        });

        mZhihuQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DetailsActivity.newIntent(getActivity(),listStories.get(position).getId(),listStories.get(position).getTitle());
            }
        });

        mZhihuQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                PopupMenu popup = new PopupMenu(getContext(), view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.list_zhihu_share, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_share_url:
                                ShareUtils.share(getActivity(), RetrofitService.ZHIHU_SHARE_URL+listStories.get(position).getId(),listStories.get(position).getTitle());
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

    }

    private View getHeaderView(List<String> listTopImg,List<String> listTopTitle) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.banner_zhihuheader, (ViewGroup) mRecyclerView.getParent(), false);
        mBanner = (Banner) view.findViewById(R.id.banner);

        if(null != listTopImg && listTopImg.size() > 0){
            setBanner(listTopImg,listTopTitle);
        }
        return view;
    }

    // 弹跳式Banner
    private void setMzBanner(List<Zhihu.TopStoriesBean> listTopStories) {

//        mBanner.setPages(listTopStories, new MZHolderCreator<BannerViewHolder>() {
//            @Override
//            public BannerViewHolder createViewHolder() {
//                return new BannerViewHolder();
//            }
//        });
//        mBanner.start();

    }

    private void setBanner(List<String> listTopImg,List<String> listTopTitle) {
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(listTopImg);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(listTopTitle);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(4000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.LEFT);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

    }

    public void showDatePickerDialog(){
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                date = year + String.format("%02d",monthOfYear) + String.format("%02d",dayOfMonth);
                mPresenter.loadZhihu(false,true,date);
            }
        },c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        // 设置最大时间
        dialog.setMaxDate(Calendar.getInstance());
        // 设置最小时间
        Calendar minDate = Calendar.getInstance();
        minDate.set(2013, 5, 20);
        dialog.setMinDate(minDate);
        dialog.vibrate(false);

        dialog.show(getActivity().getFragmentManager(), ZhihuFragment.class.getSimpleName());
    }

//    public static class BannerViewHolder implements MZViewHolder<Zhihu.TopStoriesBean> {
//        private ImageView mImageView;
//        @Override
//        public View createView(Context context) {
//            // 返回页面布局
//            View view = LayoutInflater.from(context).inflate(R.layout.remote_banner_item,null);
//            mImageView = (ImageView) view.findViewById(R.id.remote_item_image);
//            return view;
//        }
//        //remote_banner_item remote_item_image
//
//        @Override
//        public void onBind(Context context, int position, Zhihu.TopStoriesBean topStoriesBean) {
//            // 数据绑定
//            Glide.with(context).load(topStoriesBean.getImage()).into(mImageView);
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if(null != mBanner){
            mBanner.stopAutoPlay();
        }
    }

}
