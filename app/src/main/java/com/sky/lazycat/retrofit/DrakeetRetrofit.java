package com.sky.lazycat.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuetu-develop on 2017/8/18.
 */

public class DrakeetRetrofit {

    final RetrofitService.ZhihuDaliyService zhihuDaliyService;
    final RetrofitService.GankVideoService gankService;
    final RetrofitService.NeiHanService neihanService;

    DrakeetRetrofit(){
        //创建Builder，可添加更多设置
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.addConverterFactory(GsonConverterFactory.create());

        //配置Builder，然后创建请求
        builder.baseUrl(RetrofitService.NEIHAN_DATA_BASE);
        Retrofit neihanRest = builder.build();
        builder.baseUrl(RetrofitService.MEIZHI_DATA_BASE);
        Retrofit gankRest = builder.build();
        builder.baseUrl(RetrofitService.ZHIHU_DATA_BASE);
        Retrofit zhihuRest = builder.build();

        neihanService = neihanRest.create(RetrofitService.NeiHanService.class);
        gankService = gankRest.create(RetrofitService.GankVideoService.class);
        zhihuDaliyService = zhihuRest.create(RetrofitService.ZhihuDaliyService.class);
    }

    public RetrofitService.ZhihuDaliyService getZhihuDaliyService(){
        return zhihuDaliyService;
    }

    public RetrofitService.GankVideoService getGankService() {
        return gankService;
    }

    public RetrofitService.NeiHanService getNeihanService(){
        return neihanService;
    }

}
