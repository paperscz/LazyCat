package com.sky.lazycat.meizhi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.lazycat.R;
import com.sky.lazycat.data.Meizhi.MeizhiData;
import com.sky.lazycat.widget.MultiSwipeRefreshLayout;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/21.
 */

public class MeizhiFragment extends Fragment implements MeizhiDataContract.View{

    private RecyclerView mRecyclerView;
    private MultiSwipeRefreshLayout mMultiSwipeRefreshLayout;
    private MeizhiDataContract.Presenter mPresenter;

    private int mPage = 1;

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
                //mPresenter.load(true);
                //mLoadMore = false;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        mPresenter.loadMeizhi(mPage,true);

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
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public boolean isActive() {
        return isAdded() && isResumed();
    }

    @Override
    public void showResult(@NonNull List<MeizhiData.MeizhiBean> list) {

    }
}
