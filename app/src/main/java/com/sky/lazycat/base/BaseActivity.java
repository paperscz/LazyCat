package com.sky.lazycat.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * 暂时没用
 * Created by yuetu-develop on 2017/9/7.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BaseActivity",getClass().getSimpleName());
    }

    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activityList){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    public void mStart(){

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

    public static void startNewActivity(Context context,String data1,String data2){
        Intent intent = new Intent(context,BaseActivity.class);
        intent.putExtra("data1",data1);
        intent.putExtra("data2",data2);
        context.startActivity(intent);
    }


}
