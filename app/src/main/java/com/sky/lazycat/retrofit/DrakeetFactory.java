package com.sky.lazycat.retrofit;

/**
 * Created by yuetu-develop on 2017/8/18.
 */

public class DrakeetFactory {

    protected static final Object monitor = new Object();
    static RetrofitService.GankVideoService sGankIOSingleton = null;
    static RetrofitService.ZhihuDaliyService sZhihuSingleton = null;
    static RetrofitService.NeiHanService sNeihanSingleton = null;

    public static RetrofitService.ZhihuDaliyService getZhihuSingleton() {
        synchronized (monitor) {
            if (sZhihuSingleton == null) {
                sZhihuSingleton = new DrakeetRetrofit().getZhihuDaliyService();
            }
            return sZhihuSingleton;
        }
    }

    public static RetrofitService.NeiHanService getNeihanSingleton() {
        synchronized (monitor) {
            if (sNeihanSingleton == null) {
                sNeihanSingleton = new DrakeetRetrofit().getNeihanService();
            }
            return sNeihanSingleton;
        }
    }
    public static RetrofitService.GankVideoService getGankIOSingleton() {
        synchronized (monitor) {
            if (sGankIOSingleton == null) {
                sGankIOSingleton = new DrakeetRetrofit().getGankService();
            }
            return sGankIOSingleton;
        }
    }


}
