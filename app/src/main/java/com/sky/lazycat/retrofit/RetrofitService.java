package com.sky.lazycat.retrofit;

import com.sky.lazycat.data.neihanduanzi.NeiHanAll;
import com.sky.lazycat.data.neihanduanzi.NeiHanFirst;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public interface RetrofitService {

    String NEIHAN_DATA_BASE = "http://iu.snssdk.com/neihan/stream/mix/v1/?count=20";

    interface NeiHanService{
        // 没有数据填 . 或者 /
        @GET(".")
        Call<NeiHanFirst> getNeiHanList();

    }

}
