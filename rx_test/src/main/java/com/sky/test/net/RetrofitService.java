package com.sky.test.net;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/11 0011.
 **/
public interface RetrofitService {

    @GET("data/福利/{size}/{page}")
    Observable<MeizhiData> getMeizhiData(@Path("page") int page,@Path("size") int size);

    @GET("day/{date}")
    Observable<GankData> getGankData(@Path("date") String date);

}
