package com.sky.lazycat.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sky.lazycat.R;
import com.sky.lazycat.data.remote.MeizhiRemoteDataSource;
import com.sky.lazycat.first.FirstFragment;
import com.sky.lazycat.meizhi.MeizhiDataRepository;
import com.sky.lazycat.meizhi.MeizhiFragment;
import com.sky.lazycat.meizhi.MeizhiPresenter;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private FirstFragment mFirstFragment;
    private MeizhiFragment mMeiZiFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragments(savedInstanceState);
        new MeizhiPresenter(mMeiZiFragment, MeizhiDataRepository.getInstance(MeizhiRemoteDataSource.geInstance()));

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
