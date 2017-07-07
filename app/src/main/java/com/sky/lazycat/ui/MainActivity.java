package com.sky.lazycat.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sky.lazycat.R;
import com.sky.lazycat.first.FirstFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private FirstFragment mFirstFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragments(savedInstanceState);
    }

    private void initFragments(Bundle savedInstanceState) {
        FragmentManager fm = getSupportFragmentManager();
        if(savedInstanceState == null){
            mFirstFragment = FirstFragment.newInstance();
        }else {
            mFirstFragment = (FirstFragment) fm.getFragment(savedInstanceState,FirstFragment.class.getSimpleName());

        }
        if(!mFirstFragment.isAdded()){
            fm.beginTransaction()
                    .add(R.id.container,mFirstFragment,FirstFragment.class.getSimpleName())
                    .commit();
        }

    }

    private void initViews() {
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

    }


}
