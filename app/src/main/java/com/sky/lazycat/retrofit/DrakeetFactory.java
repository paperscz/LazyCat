package com.sky.lazycat.retrofit;

/**
 * Created by yuetu-develop on 2017/8/18.
 */

public class DrakeetFactory {

    protected static final Object monitor = new Object();
    static RetrofitService.GankDaliyService sGankIOSingleton = null;

    public static RetrofitService.GankDaliyService getGankIOSingleton() {
        synchronized (monitor) {
            if (sGankIOSingleton == null) {
                sGankIOSingleton = new DrakeetRetrofit().getGankService();
            }
            return sGankIOSingleton;
        }
    }
}
