package com.sky.lazycat.timeline.zhihu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sky.lazycat.R;
import com.sky.lazycat.data.zhihu.Zhihu;
import com.sky.lazycat.timeline.GlideImageLoader;
import com.sky.lazycat.util.DateFormatUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuetu-develop on 2017/8/24.
 */

public class ZhihuFragment extends Fragment implements ZhihuDataContract.View{

    @BindView(R.id.banner) MZBannerView mBanner;
    @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
//    private List<String> bannerImages;
//    private List<String> bannerTitles;
//    private List<Zhihu.TopStoriesBean> listTopStrories;
    private ZhihuDataContract.Presenter mPresenter;
    private boolean mIsFirstLoad = true;
    private Unbinder mUnbinder;

    public ZhihuFragment(){}

    public static ZhihuFragment newInstance() {
        return new ZhihuFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        mUnbinder = ButterKnife.bind(this,view);
        // 另一个banner 简单使用banner
        // mBanner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        //setBanner();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

        String date = DateFormatUtil.formatZhihuDailyDateToString(System.currentTimeMillis());

        if(mIsFirstLoad){
            // 首次访问加载带topstories数据
            mPresenter.loadZhihu(true,true,date);
            mIsFirstLoad = false;
        }else {
            mPresenter.loadZhihu(false,true,date);
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
    public void showResult(List<Zhihu.StoriesBean> listStories, List<Zhihu.TopStoriesBean> listTopStories) {
        setMzBanner(listTopStories);
    }

    private void setMzBanner(List<Zhihu.TopStoriesBean> listTopStories) {

        mBanner.setPages(listTopStories, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mBanner.start();

    }

//    private void setBanner() {
//        //设置banner样式
//        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//        //设置图片加载器
//        mBanner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
//        mBanner.setImages(bannerImages);
//        //设置banner动画效果
//        mBanner.setBannerAnimation(Transformer.DepthPage);
//        //设置标题集合（当banner样式有显示title时）
//        mBanner.setBannerTitles(bannerTitles);
//        //设置自动轮播，默认为true
//        mBanner.isAutoPlay(true);
//        //设置轮播时间
//        mBanner.setDelayTime(1500);
//        //设置指示器位置（当banner模式中有指示器时）
//        mBanner.setIndicatorGravity(BannerConfig.CENTER);
//        //banner设置方法全部调用完毕时最后调用
//        mBanner.start();
//    }



    public static class BannerViewHolder implements MZViewHolder<Zhihu.TopStoriesBean> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.remote_banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.remote_item_image);
            return view;
        }
        //remote_banner_item remote_item_image

        @Override
        public void onBind(Context context, int position, Zhihu.TopStoriesBean topStoriesBean) {
            // 数据绑定
            Glide.with(context).load(topStoriesBean.getImage()).into(mImageView);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mBanner.pause();
    }




}
