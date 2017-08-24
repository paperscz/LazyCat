package com.sky.lazycat.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuetu-develop on 2017/8/18.
 */

public class DrakeetRetrofit {

    final RetrofitService.GankDaliyService gankService;

    DrakeetRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.MEIZHI_DATA_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gankService = retrofit.create(RetrofitService.GankDaliyService.class);
    }

    public RetrofitService.GankDaliyService getGankService() {
        return gankService;
    }
}
