package com.sky.lazycat.timeline;

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
import com.sky.lazycat.data.remote.ZhihuRemoteDataSource;
import com.sky.lazycat.timeline.neihan.NeiHanDataPresenter;
import com.sky.lazycat.timeline.neihan.NeiHanDataRepository;
import com.sky.lazycat.timeline.neihan.NeiHanFragment;
import com.sky.lazycat.timeline.zhihu.ZhihuDataRepository;
import com.sky.lazycat.timeline.zhihu.ZhihuFragment;
import com.sky.lazycat.timeline.zhihu.ZhihuPresenter;

/**
 * Created by yuetu-develop on 2017/7/6.
 */

public class TimelineFragment extends Fragment {

    private NeiHanFragment mNeiHanFragment;
    private ZhihuFragment mZhihuFragment;
    private FloatingActionButton mFab;
    private TabLayout mTabLayout;

    public TimelineFragment() {
        // Requires the empty constructor
    }

    public static TimelineFragment newInstance() {
        return new TimelineFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            FragmentManager fm = getChildFragmentManager();
            mNeiHanFragment = (NeiHanFragment) fm.getFragment(savedInstanceState,NeiHanFragment.class.getSimpleName());
            mZhihuFragment = (ZhihuFragment) fm.getFragment(savedInstanceState,ZhihuFragment.class.getSimpleName());
        } else {
            mNeiHanFragment = NeiHanFragment.newInstance();
            mZhihuFragment = ZhihuFragment.newInstance();
        }

        new NeiHanDataPresenter(mNeiHanFragment, NeiHanDataRepository.getInstance(NeiHanRemoteDataSource.geInstance()));
        new ZhihuPresenter(mZhihuFragment, ZhihuDataRepository.getInstance(ZhihuRemoteDataSource.geInstance()));
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
        if(mZhihuFragment.isAdded()){
            fm.putFragment(outState,ZhihuFragment.class.getSimpleName(),mZhihuFragment);
        }
        if(mNeiHanFragment.isAdded()){
            fm.putFragment(outState,NeiHanFragment.class.getSimpleName(),mNeiHanFragment);
        }
    }

    private void initViews(View view) {
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setAdapter(new TimelinePagerAdapter(getChildFragmentManager(),
                getContext(),mZhihuFragment,mNeiHanFragment));

        mViewPager.setOffscreenPageLimit(3);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);

    }
}
