package com.sky.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sky.test.R;
import com.sky.test.util.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * 类名称：
 * 类功能：
 * 类作者：Administrator
 * 类日期：2018/12/13 0013.
 **/
public class FlowableActivity extends AppCompatActivity{

    private static final String TAG = FlowableActivity.class.getSimpleName();
    private Subscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowable);
    }

    public void flowableTest(View view){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();
            }
            //当downstream无法处理事件时，抛出一个MissingBackpressureException
        }, BackpressureStrategy.ERROR)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                //Subscription 也可以取消订阅 s.cancel();
                //也可以设置downstream能接收的事件数量
                mSubscription = s;
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.i(TAG,integer+"");
            }

            @Override
            public void onError(Throwable t) {
                LogUtils.e(TAG,t);
            }

            @Override
            public void onComplete() {
                LogUtils.i(TAG,"onComplete");
            }
        });
    }

    /**
     * 点击一次增加一些容量
     */
    public void flowableAdd(View view){
        if(null != mSubscription){
            mSubscription.request(100);
        }
    }

    public void flowableBackTest(View view){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 10000;i++){
                    emitter.onNext(i);
                }
            }
            //当downstream无法处理事件时，抛出一个MissingBackpressureException
        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        //当上游发送的事件超过下游的承受能力，根据 BackpressureStrategy 的类型进行处理
                        //MISSING --? ERROR -- 抛出异常 BUFFER -- 保存等待下游处理（数据量过大下游不处理依旧会OOM）
                        //DROP -- 下游来不及处理，舍弃掉这些事件 LATEST -- 下游来不及处理，保留并覆盖最新的能处理的事件
                        //默认数量128，超过此容量的事件才会丢失，再根据背压模式保留相关事件
                        mSubscription = s;
                        s.request(128);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.i(TAG,integer+"");
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.e(TAG,t);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.i(TAG,"onComplete");
                    }
                });
    }

    public void flowableCount(View view){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> emitter) throws Exception {
                //同线程下，暂存区缓存数量默认0
                //这里的数量 emitter.requested() 每当下游接收/处理一个事件，便减少一个
                LogUtils.i(TAG,"缓存区可接受事件数量："+ emitter.requested());
                emitter.onNext(1);
                LogUtils.i(TAG,"缓存区可接受事件数量："+ emitter.requested());
                emitter.onNext(2);
                LogUtils.i(TAG,"缓存区可接受事件数量："+ emitter.requested());
            }
        },BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(10);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.i(TAG,integer+"");
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void flowableCountSync(View view){
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Integer> emitter) throws Exception {
                //不同线程
                //暂存区缓存数量事件默认 128，下游处理事件能力默认为0
                //当下游设置数量超过96时，重新设置缓存区数量，就又能继续往缓存区放事件
                //下游调用 s.request() 时才拥有接收/处理事件的能力，执行onNext
                LogUtils.i(TAG,"暂存区缓存事件数量"+emitter.requested());
                boolean flag;
                for (int i = 0;;i++){
                    flag = false;
                    //当缓存区数量为0时，进入这个死循环，不执行发送操作
                    while (emitter.requested() == 0){
                        if(!flag){
                            flag = true;
                            LogUtils.i(TAG,"缓存区满了，不能再存更多了");
                        }
                    }
                    emitter.onNext(i);
                    LogUtils.i(TAG,"发送事件"+i);
                }

            }
        },BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.i(TAG,"下游接收"+integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.w(TAG,t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
