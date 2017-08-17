package com.sky.lazycat.ui;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sky.lazycat.R;

import butterknife.ButterKnife;

/**
 * Created by yuetu-develop on 2017/8/17.
 */

public class GankActivity extends AppCompatActivity {

    public static final String EXTRA_GANK_DATE = "gank_date";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        ButterKnife.bind(this);

    }












}
