package com.sky.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;

import com.sky.test.R;
import com.sky.test.net.GankData;
import com.sky.test.net.HttpUtil;
import com.sky.test.net.MeizhiData;
import com.sky.test.net.RetrofitService;
import com.sky.test.util.LogUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/10 0010.
 **/
public class RxRetrofitActivity extends AppCompatActivity{

    private static final String TAG = RxRetrofitActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_retrofit);
    }

    public void load(View view){
        RetrofitService meizi = HttpUtil.getInstance().create(RetrofitService.class);
        meizi.getMeizhiData(1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MeizhiData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull MeizhiData meizhiData) {
                        LogUtils.i(TAG,meizhiData.getResults().get(0).toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.i(TAG,"onComplete");
                    }
                });
    }

    public void map(View view){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return "Change to String " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.i(TAG,s);
            }
        });
    }

    public void flatmap(View view){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer o) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 1; i++){
                    list.add("new Data" + o);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.i(TAG,s);
            }
        });
    }

    public void concatMap(View view){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer o) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++){
                    list.add("new Data" + o);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.i(TAG,s);
            }
        });
    }

    public void retrofitMap(View view){
        final RetrofitService meizi = HttpUtil.getInstance().create(RetrofitService.class);
        meizi.getMeizhiData(1,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // 数据获取，调用在 onNext 之前
                .doOnNext(new Consumer<MeizhiData>() {
                    @Override
                    public void accept(MeizhiData meizhiData) throws Exception {
                        // 第一次数据请求已经拿到了
                        LogUtils.i(TAG,meizhiData.getResults().get(0).toString());
                    }
                })
                // 切换到 io 线程做其它请求
                .observeOn(Schedulers.io())
                // Function 泛型第一个上次获取的数据类型，第二个请求/处理过的类型
                .flatMap(new Function<MeizhiData, ObservableSource<GankData>>() {
                    @Override
                    public ObservableSource<GankData> apply(@NonNull MeizhiData meizhiData) throws Exception {
//                        new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA).format(System.currentTimeMillis())
                        return meizi.getGankData("2015/08/07");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GankData>() {
                    @Override
                    public void accept(GankData gankData) throws Exception {
                        LogUtils.i(TAG,gankData.getResults().getAndroid().get(0).getUrl());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.i(TAG,throwable.getMessage());
                    }
                });


    }

}
