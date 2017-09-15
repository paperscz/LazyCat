package com.sky.lazycat.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Debug;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sky.lazycat.R;

/**
 * Created by yuetu-develop on 2017/9/11.
 */

public class TestService extends Service {

    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        // 创建Intent，跳转到指定的类
        Intent notificationIntent = new Intent(this,TestActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("前台服务标题"); // 设置标题
        builder.setContentText("前台服务内容");  // 设置内容
        builder.setSmallIcon(R.mipmap.ic_launcher);  // 设置图标
        builder.setContentIntent(pendingIntent); // 设置点击跳转后的intent

        Notification notification = builder.getNotification(); // builder获取Notification对象
        startForeground(1,notification); // 开启前台服务，第一个参数为id

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //新建一个子类继承自Binder类
    class MyBinder extends Binder {

        // service和Activity连接用
        public void service_connect_Activity() {
            Log.i("tag","Activity调用Service，并成功调用此方法。");
        }
    }

}
