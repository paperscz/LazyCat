package com.sky.lazycat.meizhi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sky.lazycat.R;
import com.sky.lazycat.data.meizhi.MeizhiData;
import com.sky.lazycat.interfaces.OnMeizhiTouchListener;
import com.sky.lazycat.ui.PhotoViewActivity;
import com.sky.lazycat.util.ToastUtils;
import com.sky.lazycat.widget.MultiSwipeRefreshLayout;
import com.sky.lazycat.widget.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/21.
 */

public class MeizhiFragment extends Fragment implements MeizhiDataContract.View{

    private RecyclerView mRecyclerView;
    private MultiSwipeRefreshLayout mMultiSwipeRefreshLayout;
    private MeizhiDataContract.Presenter mPresenter;
    private MeizhiListAdapter mMeiZhiAdapter;
    private int mPage = 1;
    private static final int PRELOAD_SIZE = 6;
    private boolean mIsFirstTimeTouchBottom = true;
    private boolean mIsFirstLoad = true;
    private boolean mLoadMore = false;
    private boolean mIsLoading = false;
    private boolean mGoBigImage = false;
    private List<String> mUrls = new ArrayList<>();

    public MeizhiFragment() {
        // Requires the empty constructor
    }

    public static MeizhiFragment newInstance() {
        return new MeizhiFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizhi,container,false);
        initViews(view);

        mMultiSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                mPresenter.loadMeizhi(mPage,true);
                mLoadMore = false;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        // 第一次加载判定，到大图activity再回来不要加载
            if(mIsFirstLoad){
                mPresenter.loadMeizhi(mPage,true);
                mIsFirstLoad = false;
            }else {
                //mPresenter.loadMeizhi(mPage,false);
            }

    }

    @Override
    public void setPresenter(MeizhiDataContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mMultiSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {

        mMultiSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mMultiSwipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void showResult(@NonNull List<MeizhiData.MeizhiBean> list) {
        mIsLoading = false;
        if(mMeiZhiAdapter == null){
            final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mMeiZhiAdapter = new MeizhiListAdapter(getActivity(),list);
            mRecyclerView.setAdapter(mMeiZhiAdapter);

            mRecyclerView.addOnScrollListener(getOnBottomListener(layoutManager));
            mMeiZhiAdapter.setOnMeizhiTouchListener(getOnMeizhiTouchListener());
        }else {
            if(mLoadMore){
                mMeiZhiAdapter.addData(list);
            }else {
                mMeiZhiAdapter.updateData(list);
            }

        }
    }

    RecyclerView.OnScrollListener getOnBottomListener(final StaggeredGridLayoutManager layoutManager){
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isBottom = layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1] >=
                        mMeiZhiAdapter.getItemCount() - PRELOAD_SIZE;
                if(!mMultiSwipeRefreshLayout.isRefreshing() && isBottom){
                    // 第一次划到底部
                    if(!mIsFirstTimeTouchBottom && !mIsLoading){
                        setLoadingIndicator(true);
                        mPage += 1;
                        mPresenter.loadMeizhi(mPage,false);
                        mIsLoading = true;
                        mLoadMore = true;
                    }else {
                        mIsFirstTimeTouchBottom = false;
                    }
                }

            }
        };
    }



    private OnMeizhiTouchListener getOnMeizhiTouchListener() {
        return new OnMeizhiTouchListener() {
            @Override
            public void onTouch(View v, View meizhiView, View card, List<MeizhiData.MeizhiBean> meizhi,MeizhiData.MeizhiBean meizhiBean) {
                if (meizhi == null) return;
                if (v == meizhiView) {
                    // && !mMeizhiBeTouched
                    //mMeizhiBeTouched = true;
                    startPictureActivity(meizhi, meizhiView,meizhi.indexOf(meizhiBean));


                } else if (v == card) {
                    // startGankActivity(meizhi.publishedAt);
                }

            }

        };

    }


    // 遍历拿出url
    private List<String> getUrils(List<MeizhiData.MeizhiBean> meizhi) {
        if(mUrls.size() != 0){
            mUrls.clear();
        }
        for (MeizhiData.MeizhiBean meizhiBean : meizhi) {
            if(!TextUtils.isEmpty(meizhiBean.getUrl())){
                mUrls.add(meizhiBean.getUrl());
            }
        }
        return mUrls;
    }

    private void startPictureActivity(List<MeizhiData.MeizhiBean> meizhiList, View transitView,int index) {

        Intent intent = new Intent(getActivity(), PhotoViewActivity.class);
        intent.putExtra("currentPosition",index);
        intent.putStringArrayListExtra("mUrls", (ArrayList<String>) getUrils(meizhiList));

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(), transitView, "picture");
        try {
            ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            startActivity(intent);
        }
    }
}
