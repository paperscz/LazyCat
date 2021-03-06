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
import android.widget.TableLayout;

import com.sky.lazycat.R;
import com.sky.lazycat.data.remote.DouBanMoiveRemoteDataSource;
import com.sky.lazycat.data.remote.NeiHanRemoteDataSource;
import com.sky.lazycat.data.remote.ZhihuRemoteDataSource;
import com.sky.lazycat.timeline.doubanmovie.DouBanMovieDataPresenter;
import com.sky.lazycat.timeline.doubanmovie.DouBanMovieDataRepository;
import com.sky.lazycat.timeline.doubanmovie.DouBanMovieFragment;
import com.sky.lazycat.timeline.neihan.NeiHanDataPresenter;
import com.sky.lazycat.timeline.neihan.NeiHanDataRepository;
import com.sky.lazycat.timeline.neihan.NeiHanFragment;
import com.sky.lazycat.timeline.neihan.NeiHanTuiJianFragment;
import com.sky.lazycat.timeline.zhihu.ZhihuDataRepository;
import com.sky.lazycat.timeline.zhihu.ZhihuFragment;
import com.sky.lazycat.timeline.zhihu.ZhihuPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuetu-develop on 2017/7/6.
 */

public class TimelineFragment extends Fragment {

    private ZhihuFragment mZhihuFragment;
//    private NeiHanFragment mNeiHanFragment;
//    private NeiHanTuiJianFragment mNeiHanTuiJianFragment;
    private DouBanMovieFragment mDouBanMovieFragment;
    @BindView(R.id.fab) FloatingActionButton mFab;
    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    private Unbinder mUnbinder;

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
            mZhihuFragment = (ZhihuFragment) fm.getFragment(savedInstanceState,ZhihuFragment.class.getSimpleName());
            mDouBanMovieFragment = (DouBanMovieFragment) fm.getFragment(savedInstanceState,DouBanMovieFragment.class.getSimpleName());
//            mNeiHanFragment = (NeiHanFragment) fm.getFragment(savedInstanceState,NeiHanFragment.class.getSimpleName());
//            mNeiHanTuiJianFragment = (NeiHanTuiJianFragment) fm.getFragment(savedInstanceState,NeiHanTuiJianFragment.class.getSimpleName());
        } else {
            mZhihuFragment = ZhihuFragment.newInstance();
            mDouBanMovieFragment = DouBanMovieFragment.newInstance();
//            mNeiHanFragment = NeiHanFragment.newInstance();
//            mNeiHanTuiJianFragment = NeiHanTuiJianFragment.newInstance();
        }

        new ZhihuPresenter(mZhihuFragment, ZhihuDataRepository.getInstance(ZhihuRemoteDataSource.geInstance()));
//        new NeiHanDataPresenter(mNeiHanFragment, NeiHanDataRepository.getInstance(NeiHanRemoteDataSource.geInstance()));
//        new NeiHanDataPresenter(mNeiHanTuiJianFragment, NeiHanDataRepository.getInstance(NeiHanRemoteDataSource.geInstance()));
        new DouBanMovieDataPresenter(mDouBanMovieFragment, DouBanMovieDataRepository.getInstance(DouBanMoiveRemoteDataSource.getInstance()));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first,container,false);
        mUnbinder = ButterKnife.bind(this,view);
        initViews(view);

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    mFab.setImageResource(R.drawable.ic_calendar_white_24dp);
                    mFab.show();
                } else if(tab.getPosition() == 1){
                    mFab.show();
                    mFab.setImageResource(R.drawable.ic_arrow_up_24dp);
                }else {
                    mFab.hide();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTabLayout.getSelectedTabPosition() == 0) {
                    mZhihuFragment.showDatePickerDialog();
                } else {
//                    mNeiHanFragment.scroll2Top();
                }
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager fm = getChildFragmentManager();
        if(mZhihuFragment.isAdded()){
            fm.putFragment(outState,ZhihuFragment.class.getSimpleName(),mZhihuFragment);
        }
//        if(mNeiHanFragment.isAdded()){
//            fm.putFragment(outState,NeiHanFragment.class.getSimpleName(),mNeiHanFragment);
//        }
//        if(mNeiHanTuiJianFragment.isAdded()){
//            fm.putFragment(outState,NeiHanTuiJianFragment.class.getSimpleName(),mNeiHanTuiJianFragment);
//        }
        if(mDouBanMovieFragment.isAdded()){
            fm.putFragment(outState,DouBanMovieFragment.class.getSimpleName(),mDouBanMovieFragment);
        }
    }

    private void initViews(View view) {
        TimelinePagerAdapter timelinePagerAdapter = new TimelinePagerAdapter(getChildFragmentManager(),
                getContext(),mZhihuFragment,mDouBanMovieFragment);

        mViewPager.setAdapter(timelinePagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
