package com.sky.lazycat.timeline.neihan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sky.lazycat.R;
import com.sky.lazycat.data.neihanduanzi.NeiHanAll;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/6.
 */

public class NeiHanFragment extends Fragment implements NeiHanDataContract.View{

    // View references.
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private View mEmptyView;
    private LinearLayoutManager mLayoutManager;
    private FloatingActionButton fab;
    private int mListSize = 0;
    private boolean mIsFirstLoad = true;
    private NeiHanDataContract.Presenter mPresenter;
    private NeiHanDataQuickAdapter mAdapter;
    private boolean mLoadMore = false;

    public NeiHanFragment() {
        // An empty constructor is needed as a fragment.
    }
    public static NeiHanFragment newInstance() {
        return new NeiHanFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_neihan,container,false);

        initViews(view);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadNeiHan(true);
                mLoadMore = false;
            }
        });

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    fab.hide();
                    if (mLayoutManager.findLastCompletelyVisibleItemPosition() == mListSize - 1) {
                        loadMore();
                        mLoadMore = true;
                    }
                } else {
                    fab.show();
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
            mPresenter.loadNeiHan(true);
            mIsFirstLoad = false;
        }else {
            mPresenter.loadNeiHan(false);
        }
    }

    @Override
    public void setPresenter(NeiHanDataContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(),R.color.colorAccent));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mEmptyView = view.findViewById(R.id.empty_view);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
    }

    private void loadMore(){
        mPresenter.loadNeiHan(true);
    }

    @Override
    public boolean isActive() {
        return isAdded()&&isResumed();
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showResult(List<NeiHanAll.DataBean> list) {
        if(mAdapter == null){
            mAdapter = new NeiHanDataQuickAdapter(getContext(),list);
            mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            mRecyclerView.setAdapter(mAdapter);
            mListSize = list.size();
        } else {
            if(mLoadMore){
                mAdapter.addData(list);
                mListSize += list.size();
            }else {
                mAdapter.setNewData(list);
                mListSize = list.size();
            }
        }
        mEmptyView.setVisibility(list.isEmpty()?View.VISIBLE:View.INVISIBLE);
    }
}
