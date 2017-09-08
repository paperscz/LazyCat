package com.sky.lazycat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import com.sky.lazycat.R;
import com.sky.lazycat.data.meizhi.MeizhiData;
import com.sky.lazycat.widget.PhotoViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuetu-develop on 2017/8/16.
 */

public class PhotoViewActivity extends AppCompatActivity{

    @BindView(R.id.pvp_viewpager) PhotoViewPager mPhotoViewPager;
    @BindView(R.id.tv_number) AppCompatTextView tv_number;
    private PhotoViewAdapter mPhotoViewAdapter;
    private List<String> mUrls;
    private int currentPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("currentPosition",0);
        mUrls = intent.getStringArrayListExtra("mUrls");
        mPhotoViewAdapter = new PhotoViewAdapter(mUrls,this);
        mPhotoViewPager.setAdapter(mPhotoViewAdapter);
        mPhotoViewPager.setCurrentItem(currentPosition);
        tv_number.setText(currentPosition+1 + "/" +mUrls.size());
        mPhotoViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                tv_number.setText(currentPosition + 1 + "/" + mUrls.size());
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
