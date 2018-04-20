package com.sky.lazycat.ui;

import android.animation.ObjectAnimator;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sky.lazycat.R;
import com.sky.lazycat.data.remote.GankVideoRemoteDataSource;
import com.sky.lazycat.data.remote.MeizhiRemoteDataSource;
import com.sky.lazycat.info.InfoFragment;
import com.sky.lazycat.interfaces.OnViewScrollListener;
import com.sky.lazycat.timeline.TimelineFragment;
import com.sky.lazycat.meizhi.MeizhiDataRepository;
import com.sky.lazycat.meizhi.MeizhiFragment;
import com.sky.lazycat.meizhi.MeizhiPresenter;
import com.sky.lazycat.meizhi.gankvideo.GankVideoDataRepository;

public class MainActivity extends AppCompatActivity implements OnViewScrollListener{
    // 标记当前所在的Fragment，再次打开直接定位
    private static final String KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID = "KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID";
    private BottomNavigationView mBottomNavigationView;
    private TimelineFragment mTimelineFragment;
    private MeizhiFragment mMeiZiFragment;
    private InfoFragment mInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragments(savedInstanceState);
        // 创建妹纸Fragment界面的Presenter
        new MeizhiPresenter(mMeiZiFragment, MeizhiDataRepository.getInstance(MeizhiRemoteDataSource.geInstance()), GankVideoDataRepository.getInstance(GankVideoRemoteDataSource.geInstance()));

        // 根据储存的fragment状态来展示相应fragment
        if(savedInstanceState != null){
            int id = savedInstanceState.getInt(KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID,R.id.nav_timeline);
            switch (id) {
                case R.id.nav_timeline:
                    showFragment(mTimelineFragment);
                    break;
                case R.id.nav_meizi:
                    showFragment(mMeiZiFragment);
                    break;
                case R.id.nav_info:
                    showFragment(mInfoFragment);
                    break;
            }
        }else {
            showFragment(mTimelineFragment);
        }

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_timeline:
                        showFragment(mTimelineFragment);
                        break;
                    case R.id.nav_meizi:
                        showFragment(mMeiZiFragment);
                        break;
                    case R.id.nav_info:
                        showFragment(mInfoFragment);
                        break;
                    default:
                        break;
                }
                ft.commit();
                return true;
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // 储存当前fragment
        outState.putInt(KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID, mBottomNavigationView.getSelectedItemId());
        FragmentManager fm = getSupportFragmentManager();
        if (mTimelineFragment.isAdded()) {
            fm.putFragment(outState, TimelineFragment.class.getSimpleName(), mTimelineFragment);
        }
        if (mMeiZiFragment.isAdded()) {
            fm.putFragment(outState, MeizhiFragment.class.getSimpleName(), mMeiZiFragment);
        }
        if (mInfoFragment.isAdded()) {
            fm.putFragment(outState, InfoFragment.class.getSimpleName(), mInfoFragment);
        }
    }

    private void initFragments(Bundle savedInstanceState) {
        FragmentManager fm = getSupportFragmentManager();
        if(savedInstanceState == null){
            mTimelineFragment = TimelineFragment.newInstance();
            mMeiZiFragment = MeizhiFragment.newInstance();
            mInfoFragment = InfoFragment.newInstance();
        }else {
            mTimelineFragment = (TimelineFragment) fm.getFragment(savedInstanceState,TimelineFragment.class.getSimpleName());
            mMeiZiFragment = (MeizhiFragment) fm.getFragment(savedInstanceState,MeizhiFragment.class.getSimpleName());
            mInfoFragment = (InfoFragment) fm.getFragment(savedInstanceState,InfoFragment.class.getSimpleName());
        }

        if(!mTimelineFragment.isAdded()){
            fm.beginTransaction()
                    .add(R.id.container,mTimelineFragment,TimelineFragment.class.getSimpleName())
                    .commit();
        }
        if(!mMeiZiFragment.isAdded()){
            fm.beginTransaction()
                    .add(R.id.container,mMeiZiFragment,MeizhiFragment.class.getSimpleName())
                    .commit();
        }
        if(!mInfoFragment.isAdded()){
            fm.beginTransaction()
                    .add(R.id.container,mInfoFragment,IDNA.Info.class.getSimpleName())
                    .commit();
        }
    }

    private void initViews() {
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

    }
    private void showFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        if (fragment instanceof TimelineFragment) {
            fm.beginTransaction()
                    .show(mTimelineFragment)
                    .hide(mMeiZiFragment)
                    .hide(mInfoFragment)
                    .commit();

        } else if (fragment instanceof MeizhiFragment) {
            fm.beginTransaction()
                    .show(mMeiZiFragment)
                    .hide(mTimelineFragment)
                    .hide(mInfoFragment)
                    .commit();
        } else if (fragment instanceof InfoFragment)  {
            fm.beginTransaction()
                    .show(mInfoFragment)
                    .hide(mMeiZiFragment)
                    .hide(mTimelineFragment)
                    .commit();
        }
    }

    @Override
    public void onViewScrolled(boolean up) {
        hideBottomNavigationView(up);
    }
    private boolean mHide = false;

    public void hideBottomNavigationView(boolean isHide){
        if(null != mBottomNavigationView){
            int height;
            if(isHide){
                height = 0;
            } else {
                height = mBottomNavigationView.getHeight();
            }
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBottomNavigationView,"translationY",height);
            objectAnimator.setDuration(300);
            objectAnimator.start();
        }
    }

}
