package com.sky.lazycat.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.sky.lazycat.R;
import com.sky.lazycat.widget.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuetu-develop on 2017/8/16.
 */

public class PhotoViewActivity extends AppCompatActivity{

    @BindView(R.id.pvp_viewpager) PhotoViewPager mPhotoViewPager;
    @BindView(R.id.tv_number) AppCompatTextView tv_number;
    private PhotoViewAdapter mPhotoViewAdapter;
    private List<String> mUrls;
    private int currentPosition;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏，已设置全屏stype
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photo_view);
        mUnbinder = ButterKnife.bind(this);
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
        if(mUrls.size() == 1){
            tv_number.setVisibility(View.GONE);
        }
        mPhotoViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                tv_number.setText(currentPosition + 1 + "/" + mUrls.size());
            }
        });
    }

    public static void newIntent(Context context, View view, List<String> urls, int index) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra("currentPosition",index);
        intent.putStringArrayListExtra("mUrls", (ArrayList<String>) urls);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(
                view,(int)view.getWidth()/2, (int)view.getHeight()/2, //拉伸开始的坐标
                0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
        ActivityCompat.startActivity(context,intent,optionsCompat.toBundle());
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        overridePendingTransition(0, android.R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
