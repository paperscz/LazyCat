package com.sky.lazycat.data.remote;

import com.sky.lazycat.data.datasource.NeiHanDataSource;
import com.sky.lazycat.data.neihanduanzi.NeiHanDuanZi;
import com.sky.lazycat.retrofit.DrakeetFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public class NeiHanRemoteDataSource implements NeiHanDataSource{

    private static NeiHanRemoteDataSource INSTANCE = null;

    private NeiHanRemoteDataSource(){
    }

    public static NeiHanRemoteDataSource geInstance(){
        if(INSTANCE == null){
            INSTANCE = new NeiHanRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getNeiHanDailyData(boolean forceUpdate,final LoadNeiHanDataCallback callback) {

        DrakeetFactory.getNeihanSingleton().getNeiHanList().enqueue(new Callback<NeiHanDuanZi>() {
            @Override
            public void onResponse(Call<NeiHanDuanZi> call, Response<NeiHanDuanZi> response) {
                callback.onNewsLoaded(response.body().getData().getData());
            }

            @Override
            public void onFailure(Call<NeiHanDuanZi> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getItem(int itemId, final GetDataItemCallback callback) {

    }

    @Override
    public void saveAll(List<NeiHanDuanZi.DuanziX.Duanzi> list) {

    }
}
