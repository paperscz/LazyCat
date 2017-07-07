package com.sky.lazycat.retrofit;

import com.sky.lazycat.data.NeiHanData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public interface RetrofitService {

    String NEIHAN_DATA_BASE = "http://iu.snssdk.com/neihan/stream/mix/v1/";

    interface NeiHanService{
        @GET()
        Call<NeiHanData> getNeiHanList();

    }

}
