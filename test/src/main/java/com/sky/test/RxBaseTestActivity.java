package com.sky.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sky.test.util.LogUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxBaseTestActivity extends AppCompatActivity {

    private static final String TAG_DEBUG = RxBaseTestActivity.class.getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 基本使用
     * @param view
     */
    public void start1(View view){
        //被观察者在做事情
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.d(TAG_DEBUG,"Observable线程："+ Thread.currentThread().getName());
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onError(new Throwable("测试Error"));
                emitter.onComplete();
            }
        })      //线程切换
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //绑定观察者，等待被观察者发送事件
                .subscribe(new Observer<Integer>() {
                    private Disposable mDisposable;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        this.mDisposable = d;
                        LogUtils.d(TAG_DEBUG,"是否解除订阅："+ d.isDisposed());
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        LogUtils.d(TAG_DEBUG,integer+"");
                        if(integer == 2){
                            //用来解除订阅
                            mDisposable.dispose();
                            LogUtils.d(TAG_DEBUG,"是否解除订阅："+mDisposable.isDisposed());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d(TAG_DEBUG,e.getMessage()+"");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG_DEBUG,"onComplete");
                    }
                });
    }

    /**
     * Consumer 用来处理单独任务的接口功能（回调）
     * @param view
     */
    public void start2(View view){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                LogUtils.d(TAG_DEBUG,"Observer线程："+ Thread.currentThread().getName());
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onError(new Throwable("ErrorTest"));
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.d(TAG_DEBUG, "accept1：" + s + Thread.currentThread().getName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d(TAG_DEBUG, "accept2：" +throwable.getMessage()+ Thread.currentThread().getName());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d(TAG_DEBUG, "accept3：" + Thread.currentThread().getName());
                    }
                });
    }

    /**
     * doOnNext/doOnError... 执行在 onNext/onError... 之前
     * doAfterNext/doAfterTerminate... 执行在 onNext/onComplete 之后
     * 线程切换
     * @param view
     */
    public void start3(View view){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG_DEBUG, "doOnNext：" + Thread.currentThread().getName());
                    }
                })
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d(TAG_DEBUG, "doOnDispose：" + Thread.currentThread().getName());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d(TAG_DEBUG, "doOnError：" + throwable.getMessage() +"-"+ Thread.currentThread().getName());
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d(TAG_DEBUG, "doOnComplete：" + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d(TAG_DEBUG, "doAfterNext：" + Thread.currentThread().getName());
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d(TAG_DEBUG, "doAfterTerminate：" + Thread.currentThread().getName());
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtils.d(TAG_DEBUG, "onSubscribe：" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        LogUtils.d(TAG_DEBUG, "onNext：" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d(TAG_DEBUG, "onError：" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG_DEBUG, "onComplete：" + Thread.currentThread().getName());
                    }
                });
    }


}
