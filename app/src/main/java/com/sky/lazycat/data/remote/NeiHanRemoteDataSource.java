package com.sky.lazycat.data.remote;

import com.sky.lazycat.data.datasource.NeiHanDataSource;
import com.sky.lazycat.data.neihanduanzi.NeiHanDuanZi;
import com.sky.lazycat.retrofit.DrakeetFactory;
import com.sky.lazycat.retrofit.RetrofitService;

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
    public void getNeiHanDailyData(boolean forceUpdate,int type,final LoadNeiHanDataCallback callback) {

        switch (type){
            case RetrofitService.TYPE_NEIHAN_TUIJIAN:
                DrakeetFactory.getNeihanSingleton().getNeiHanTuiJianList().enqueue(new Callback<NeiHanDuanZi>() {
                    @Override
                    public void onResponse(Call<NeiHanDuanZi> call, Response<NeiHanDuanZi> response) {
                        callback.onNewsLoaded(response.body().getData().getData());
                    }
                    @Override
                    public void onFailure(Call<NeiHanDuanZi> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
                break;
            case RetrofitService.TYPE_NEIHAN_DUANZI:
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
                break;

        }
    }

    @Override
    public void getItem(int itemId, final GetDataItemCallback callback) {
//        callback.onItemLoaded(list.get(itemId));
    }

    @Override
    public void saveAll(List<NeiHanDuanZi.DuanziX.Duanzi> list) {

    }
}
