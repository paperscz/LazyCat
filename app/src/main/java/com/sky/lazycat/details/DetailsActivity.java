package com.sky.lazycat.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.sky.lazycat.R;
import com.sky.lazycat.data.remote.ZhihuDailyContentRemoteDataSource;

/**
 * Created by yuetu-develop on 2017/9/4.
 */

public class DetailsActivity extends AppCompatActivity{

    public static final String KEY_ARTICLE_ID = "KEY_ARTICLE_ID";
    public static final String KEY_ARTICLE_TITLE = "KEY_ARTICLE_TITLE";

    private DetailsFragment mDetailsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FrameLayout(this));

        if (savedInstanceState != null) {
            mDetailsFragment = (DetailsFragment) getSupportFragmentManager().getFragment(savedInstanceState, DetailsFragment.class.getSimpleName());
        } else {
            mDetailsFragment = DetailsFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mDetailsFragment, DetailsFragment.class.getSimpleName())
                    .commit();
        }

        new DetailsPresenter(mDetailsFragment,ZhihuDailyContentRepository.getInstance(ZhihuDailyContentRemoteDataSource.getInstance()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mDetailsFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, DetailsFragment.class.getSimpleName(), mDetailsFragment);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZhihuDailyContentRepository.destroyInstance();
    }
}