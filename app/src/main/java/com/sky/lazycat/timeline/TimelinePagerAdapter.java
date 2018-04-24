package com.sky.lazycat.timeline;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sky.lazycat.R;
import com.sky.lazycat.timeline.doubanmovie.DouBanMovieFragment;
import com.sky.lazycat.timeline.neihan.NeiHanFragment;
import com.sky.lazycat.timeline.neihan.NeiHanTuiJianFragment;
import com.sky.lazycat.timeline.zhihu.ZhihuFragment;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public class TimelinePagerAdapter extends FragmentPagerAdapter {

    private final int pageCount = 2;
    private String[] titles;

    private ZhihuFragment mZhihuFragment;
    private DouBanMovieFragment mDouBanMovieFragment;
//    private NeiHanFragment mNeiHanFragment;
//    private NeiHanTuiJianFragment mNeiHanTuiJianFragment;

    public TimelinePagerAdapter(FragmentManager fm,
                                Context context,
                                ZhihuFragment zhihuFragment,
                                DouBanMovieFragment douBanMovieFragment){
        super(fm);
        this.mZhihuFragment = zhihuFragment;
        this.mDouBanMovieFragment = douBanMovieFragment;
        titles = new String[]{context.getString(R.string.zhihu_daily),context.getString(R.string.douban_movie)};
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
            return mDouBanMovieFragment;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
