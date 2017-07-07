package com.sky.lazycat.first;

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

import com.sky.lazycat.R;
import com.sky.lazycat.data.NeiHanGroup;

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

        return view;
    }

    @Override
    public void setPresenter(NeiHanDataContract.Presenter presenter) {

    }

    @Override
    public void initViews(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(),R.color.colorAccent));
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mEmptyView = view.findViewById(R.id.empty_view);
        fab = getActivity().findViewById(R.id.fab);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showResult(List<NeiHanGroup> list) {

    }
}
