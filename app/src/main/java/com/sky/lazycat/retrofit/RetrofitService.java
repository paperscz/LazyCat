package com.sky.lazycat.retrofit;

import android.database.Observable;

import com.sky.lazycat.data.gankvideo.GankVideoData;
import com.sky.lazycat.data.meizhi.GankData;
import com.sky.lazycat.data.meizhi.MeizhiData;
import com.sky.lazycat.data.neihanduanzi.NeiHanFirst;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public interface RetrofitService {
    public static final int meizhiSize = 10;
    String NEIHAN_DATA_BASE = "http://iu.snssdk.com/neihan/stream/mix/v1/?count=20";
    String MEIZHI_DATA_BASE = "http://gank.io/api/";

    interface NeiHanService{
        // 没有数据填 . 或者 /
        @GET(".")
        Call<NeiHanFirst> getNeiHanList();
    }

    interface MeizhiService{
        @GET("data/福利/" + meizhiSize + "/{page}")
        Call<MeizhiData> getMeizhiData(@Path("page") int page);
    }

    interface GankVideoService{
        @GET("data/休息视频/" + meizhiSize + "/{page}")
        Call<GankVideoData> getGankVideoData(@Path("page") int page);
    }

    interface GankDaliyService{
        @GET("day/{date}")
        Call<GankData> getGankData(@Path("date") String date);
    }

}
