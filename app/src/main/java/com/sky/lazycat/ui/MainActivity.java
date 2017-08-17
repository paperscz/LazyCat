package com.sky.lazycat.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.sky.lazycat.first.FirstFragment;
import com.sky.lazycat.meizhi.MeizhiDataRepository;
import com.sky.lazycat.meizhi.MeizhiFragment;
import com.sky.lazycat.meizhi.MeizhiPresenter;
import com.sky.lazycat.meizhi.gankvideo.GankVideoDataRepository;

public class MainActivity extends AppCompatActivity {
    // 标记当前所在的Fragment，再次打开直接定位
    private static final String KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID = "KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID";
    private BottomNavigationView mBottomNavigationView;
    private FirstFragment mFirstFragment;
    private MeizhiFragment mMeiZiFragment;

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
                    showFragment(mFirstFragment);
                    break;
                case R.id.nav_meizi:
                    showFragment(mMeiZiFragment);
                    break;
                case R.id.nav_info:
                   // showFragment(mInfoFragment);
                    break;
            }
        }else {
            showFragment(mFirstFragment);
        }

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_timeline:
                        showFragment(mFirstFragment);
                        break;
                    case R.id.nav_meizi:
                        showFragment(mMeiZiFragment);
                        break;
//                    case R.id.nav_info:
//                        showFragment(mInfoFragment);
//                        break;
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
        if (mFirstFragment.isAdded()) {
            fm.putFragment(outState, FirstFragment.class.getSimpleName(), mFirstFragment);
        }
        if (mMeiZiFragment.isAdded()) {
            fm.putFragment(outState, MeizhiFragment.class.getSimpleName(), mMeiZiFragment);
        }

    }

    private void initFragments(Bundle savedInstanceState) {
        FragmentManager fm = getSupportFragmentManager();
        if(savedInstanceState == null){
            mFirstFragment = FirstFragment.newInstance();
            mMeiZiFragment = MeizhiFragment.newInstance();
        }else {
            mFirstFragment = (FirstFragment) fm.getFragment(savedInstanceState,FirstFragment.class.getSimpleName());
            mMeiZiFragment = (MeizhiFragment) fm.getFragment(savedInstanceState,MeizhiFragment.class.getSimpleName());

        }
        if(!mFirstFragment.isAdded()){
            fm.beginTransaction()
                    .add(R.id.container,mFirstFragment,FirstFragment.class.getSimpleName())
                    .commit();
        }
        if(!mMeiZiFragment.isAdded()){
            fm.beginTransaction()
                    .add(R.id.container,mMeiZiFragment,MeizhiFragment.class.getSimpleName())
                    .commit();
        }
    }

    private void initViews() {
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

    }
    private void showFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        if (fragment instanceof FirstFragment) {
            fm.beginTransaction()
                    .show(mFirstFragment)
                    .hide(mMeiZiFragment)
            //       .hide(mFavoritesFragment)
                    .commit();

        } else if (fragment instanceof MeizhiFragment) {
            fm.beginTransaction()
                    .show(mMeiZiFragment)
                    .hide(mFirstFragment)
            //        .hide(mFavoritesFragment)
                    .commit();
        }
    }

}
