package com.sky.lazycat.first;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.sky.lazycat.R;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public class FirstPagerAdapter extends FragmentPagerAdapter {

    private final int pageCount = 1;
    private String[] titles;

    private NeiHanFragment mNeiHanFragment;

    public FirstPagerAdapter(FragmentManager fm,
                             Context context,
                             NeiHanFragment neiHanFragment){
        super(fm);
        titles = new String[]{context.getString(R.string.neihan_data)};
        this.mNeiHanFragment = neiHanFragment;

    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return mNeiHanFragment;
        }
        return mNeiHanFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
