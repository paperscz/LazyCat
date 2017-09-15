package com.sky.lazycat.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sky.lazycat.R;

/**
 * Created by yuetu-develop on 2017/9/12.
 */

public class TestActivity extends AppCompatActivity {

    // Service中自定义的Binder对象
    private TestService.MyBinder myBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ZhihuDailyContent zhihuDailyContent = new ZhihuDailyContent();
//        String json = "";
//        Gson gson = new Gson();
//        zhihuDailyContent = gson.fromJson(json, ZhihuDailyContent.class);


        bind();
    }

    // 创建一个Service连接对象
    private ServiceConnection serviceConnection = new ServiceConnection() {
        // Service连接时调用
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // 使用向下转型来给myBinder赋值，然后可以调用自定义Binder中的方法
            myBinder = (TestService.MyBinder) iBinder;
            myBinder.service_connect_Activity();
        }
        // Service断开连接或异常丢失连接时调用
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    /**
     * 绑定服务
     */
    private void bind(){
        Intent intent = new Intent(this, TestService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    /**
     * 解绑服务
     */
    private void unBind(){
        unbindService(serviceConnection);
    }



}
