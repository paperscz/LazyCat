package com.sky.lazycat.timeline.neihan;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sky.lazycat.R;
import com.sky.lazycat.data.neihanduanzi.NeiHanDuanZi;
import com.sky.lazycat.retrofit.RetrofitService;
import com.sky.lazycat.ui.PhotoViewActivity;
import com.sky.lazycat.util.ToastUtils;
import com.sky.lazycat.widget.NeiHanDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuetu-develop on 2017/9/30.
 */

public class NeiHanTuiJianFragment extends Fragment implements NeiHanDataContract.View{

    // View references.
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.empty_view)
    View mEmptyView;

    private LinearLayoutManager mLayoutManager;
    private FloatingActionButton fab;
    private int mListSize = 0;
    private boolean mIsFirstLoad = true;
    private NeiHanDataContract.Presenter mPresenter;
    private NeiHanTuiJianQuickAdapter mAdapter;
    private boolean mLoadMore = false;
    private Unbinder mUnbinder;

    public NeiHanTuiJianFragment() {
        // An empty constructor is needed as a fragment.
    }

    public static NeiHanTuiJianFragment newInstance() {
        return new NeiHanTuiJianFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neihanduanzi,container,false);
        mUnbinder = ButterKnife.bind(this,view);
        initViews(view);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadNeiHan(true, RetrofitService.TYPE_NEIHAN_DUANZI);
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
            mPresenter.loadNeiHan(true, RetrofitService.TYPE_NEIHAN_TUIJIAN);
            mIsFirstLoad = false;
        }else {
            //mPresenter.loadNeiHan(false);
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
        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(),R.color.mdtp_dark_gray));
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
    }

    private void loadMore(){
        setLoadingIndicator(true);
        mPresenter.loadNeiHan(true, RetrofitService.TYPE_NEIHAN_TUIJIAN);
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
    public void showResult(final List<NeiHanDuanZi.DuanziX.Duanzi> list) {
        if(mAdapter == null){
            mAdapter = new NeiHanTuiJianQuickAdapter(list);
            mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
            mRecyclerView.setAdapter(mAdapter);
            mListSize = list.size();
            setClick(list);
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

    private void setClick(final List<NeiHanDuanZi.DuanziX.Duanzi> list) {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                // 获取某个Item的信息可以通过mPresenter新增方法来返回
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_neihan_img:
                        if(list.get(position).getGroup().getIs_gif()==1){
                            Glide.with(getActivity())
                                    .load(list.get(position).getGroup().getLarge_image().getUrl_list().get(0).getUrl())
                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                    .into((ImageView)view);
                        } else {
                            List<String> urlList = new ArrayList<>();
                            urlList.add(list.get(position).getGroup().getLarge_image().getUrl_list().get(0).getUrl());
                            PhotoViewActivity.newIntent(getActivity(),view,urlList,0);
                        }
                        break;
                    case R.id.iv_head:
                        List<String> urlList = new ArrayList<>();
                        urlList.add(list.get(position).getGroup().getUser().getAvatar_url());
                        PhotoViewActivity.newIntent(getActivity(),view,urlList,0);
                        break;
                }
            }
        });
//        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
//            }
//        });
    }

    public void scroll2Top(){
        if(null != mRecyclerView){
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

}
