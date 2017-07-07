package com.sky.lazycat.data.remote;

import com.sky.lazycat.data.NeiHanData;
import com.sky.lazycat.data.NeiHanGroup;
import com.sky.lazycat.data.datasource.NeiHanDataSource;
import com.sky.lazycat.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.NEIHAN_DATA_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService.NeiHanService service = retrofit.create(RetrofitService.NeiHanService.class);
        service.getNeiHanList().enqueue(new Callback<NeiHanData>() {
            @Override
            public void onResponse(Call<NeiHanData> call, Response<NeiHanData> response) {
                callback.onNewsLoaded(response.body().getDatas());
            }

            @Override
            public void onFailure(Call<NeiHanData> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getItem(int itemId, final GetDataItemCallback callback) {




    }

    @Override
    public void saveAll(List<NeiHanGroup> list) {

    }
}
