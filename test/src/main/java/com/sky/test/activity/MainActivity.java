package com.sky.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sky.test.R;

/**
 * 类名称：
 * 类功能：
 * 类作者：Administrator
 * 类日期：2018/12/11 0011.
 **/
public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void base(View view){
        startActivity(new Intent(this,RxBaseTestActivity.class));
    }

    public void retrofit(View view){
        startActivity(new Intent(this,RxRetrofitActivity.class));
    }

    public void flowable(View view){
        startActivity(new Intent(this,FlowableActivity.class));
    }

}
