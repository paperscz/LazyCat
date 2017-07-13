package com.sky.lazycat.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.lazycat.R;
import com.sky.lazycat.data.remote.NeiHanRemoteDataSource;
import com.sky.lazycat.firstneihan.NeiHanDataPresenter;
import com.sky.lazycat.firstneihan.NeiHanDataRepository;
import com.sky.lazycat.firstneihan.NeiHanFragment;

/**
 * Created by yuetu-develop on 2017/7/6.
 */

public class FirstFragment extends Fragment {

    private NeiHanFragment mNeiHanFragment;
    private FloatingActionButton mFab;
    private TabLayout mTabLayout;

    public FirstFragment() {
        // Requires the empty constructor
    }

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            FragmentManager fm = getChildFragmentManager();
            mNeiHanFragment = (NeiHanFragment) fm.getFragment(savedInstanceState,NeiHanFragment.class.getSimpleName());
        }else {
            mNeiHanFragment = NeiHanFragment.newInstance();
        }

        new NeiHanDataPresenter(mNeiHanFragment, NeiHanDataRepository.getInstance(NeiHanRemoteDataSource.geInstance()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first,container,false);
        initViews(view);


        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager fm = getChildFragmentManager();
        if(mNeiHanFragment.isAdded()){
            fm.putFragment(outState,NeiHanFragment.class.getSimpleName(),mNeiHanFragment);
        }
    }

    private void initViews(View view) {
        ViewPager mViewPager = view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new FirstPagerAdapter(getChildFragmentManager(),
                getContext(),mNeiHanFragment));

        mViewPager.setOffscreenPageLimit(3);

        mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        mFab = view.findViewById(R.id.fab);

    }
}
