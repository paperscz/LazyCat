package com.sky.lazycat.timeline;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sky.lazycat.R;
import com.sky.lazycat.timeline.neihan.NeiHanFragment;
import com.sky.lazycat.timeline.zhihu.ZhihuFragment;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public class TimelinePagerAdapter extends FragmentPagerAdapter {

    private final int pageCount = 1;
    private String[] titles;

    private NeiHanFragment mNeiHanFragment;
    private ZhihuFragment mZhihuFragment;

    public TimelinePagerAdapter(FragmentManager fm,
                                Context context,
                                ZhihuFragment zhihuFragment,
                                NeiHanFragment neiHanFragment){
        super(fm);
        titles = new String[]{context.getString(R.string.zhihu_daily),context.getString(R.string.neihan_data)};
        this.mZhihuFragment = zhihuFragment;
        this.mNeiHanFragment = neiHanFragment;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return mZhihuFragment;
        } else if(position == 1){
            return mNeiHanFragment;
        }
        return mNeiHanFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
