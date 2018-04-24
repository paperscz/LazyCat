package com.sky.lazycat.timeline.doubanmovie;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sky.lazycat.R;
import com.sky.lazycat.data.doubanmovie.DouBanMovie;
import com.sky.lazycat.interfaces.OnViewScrollListener;
import com.sky.lazycat.retrofit.RetrofitService;
import com.sky.lazycat.ui.PhotoViewActivity;
import com.sky.lazycat.ui.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DouBanMovieFragment extends Fragment implements DouBanMovieDataContract.View{

    private Unbinder mUnbinder;
    private DouBanMovieDataContract.Presenter mPresenter;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_douban_movie)
    RecyclerView mRecyclerView;

    private boolean mIsFirstLoad = true;
    private int mStartCount = 0;
    private boolean mLoadMore = false;
    OnViewScrollListener onViewScrollListener;

    private DouBanMovieQuickAdapter mDouBanMovieQuickAdapter;

    public DouBanMovieFragment() {
    }

    public static DouBanMovieFragment newInstance() {
        return new DouBanMovieFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dou_ban_movie, container, false);
        mUnbinder = ButterKnife.bind(this,view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(getOnBottomListener(layoutManager));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mStartCount = 0;
                mLoadMore = false;
                mPresenter.loadDouBanMovie(true, RetrofitService.TYPE_MOVIE_IN_THEATERS,0);
            }
        });
        return view;
    }

    private boolean mHide = false;

    private RecyclerView.OnScrollListener getOnBottomListener(final LinearLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0 && !mHide){
                    onViewScrollListener.onViewScrolled(false);
                    mHide = true;
                } else if(dy < 0 && mHide){
                    onViewScrollListener.onViewScrolled(true);
                    mHide = false;
                }
//                layoutManager.findLastCompletelyVisibleItemPosition();
//                mDouBanMovieQuickAdapter.getItemCount();
                boolean isBottom = layoutManager.findLastCompletelyVisibleItemPosition() >= mDouBanMovieQuickAdapter.getItemCount() - 1;
//                Log.i("TAG_ITEM","layout:"+layoutManager.findLastCompletelyVisibleItemPosition()+"/t"+"itemCount:"+mDouBanMovieQuickAdapter.getItemCount());
                if(isBottom){
                    loadMore();
                }
            }
        };
    }

    private void loadMore(){
        setLoadingIndicator(true);
        mLoadMore = true;
        mStartCount += 20;
        mPresenter.loadDouBanMovie(true, RetrofitService.TYPE_MOVIE_IN_THEATERS,mStartCount);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        if(mIsFirstLoad){
            setLoadingIndicator(true);
            mPresenter.loadDouBanMovie(true, RetrofitService.TYPE_MOVIE_IN_THEATERS,mStartCount);
            mIsFirstLoad = false;
        }
    }

    @Override
    public void setPresenter(DouBanMovieDataContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
    }

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
    public void showResult(DouBanMovie douBanMovie) {
        if(null == mDouBanMovieQuickAdapter){
            mDouBanMovieQuickAdapter = new DouBanMovieQuickAdapter(douBanMovie.getSubjects());
            mDouBanMovieQuickAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            mRecyclerView.setAdapter(mDouBanMovieQuickAdapter);
            setAdapterClick(douBanMovie);
        } else {
            if(mLoadMore){
                mDouBanMovieQuickAdapter.addData(douBanMovie.getSubjects());
            } else {
                mDouBanMovieQuickAdapter.setNewData(douBanMovie.getSubjects());
            }
        }
    }

    private void setAdapterClick(final DouBanMovie douBanMovie) {

        mDouBanMovieQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId() == R.id.rl_item_douban_movie){
                    startActivity(WebActivity.newIntent
                            (getActivity(),RetrofitService.API_M_DOUBAN_MOVIE + douBanMovie.getSubjects().get(position).getId(),douBanMovie.getSubjects().get(position).getTitle()));
                } else if(view.getId() == R.id.iv_poster){
                    List<String> list = new ArrayList<String>();
                    list.add(douBanMovie.getSubjects().get(position).getImages().getLarge());
                    PhotoViewActivity.newIntent(getActivity(),null,list,0);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
