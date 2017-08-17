package com.sky.lazycat.data.remote;

import com.sky.lazycat.data.datasource.GankVideoDataSource;
import com.sky.lazycat.data.gankvideo.GankVideoData;
import com.sky.lazycat.data.meizhi.MeizhiData;
import com.sky.lazycat.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuetu-develop on 2017/8/14.
 */

public class GankVideoRemoteDataSource implements GankVideoDataSource{

    private static GankVideoRemoteDataSource INSTANCE = null;

    public GankVideoRemoteDataSource(){
    }

    public static GankVideoRemoteDataSource geInstance(){
        if(INSTANCE == null){
            INSTANCE = new GankVideoRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getGankVideoData(int page, boolean forceUpdate, final LoadGankVideoDataCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.MEIZHI_DATA_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService.GankVideoService service = retrofit.create(RetrofitService.GankVideoService.class);

        service.getGankVideoData(page)
                .enqueue(new Callback<GankVideoData>() {
                    @Override
                    public void onResponse(Call<GankVideoData> call, Response<GankVideoData> response) {
                        callback.onNewsLoaded(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<GankVideoData> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }
}
