package com.sky.lazycat.data.remote;

import android.util.Log;

import com.sky.lazycat.data.neihanduanzi.NeiHanAll;
import com.sky.lazycat.data.datasource.NeiHanDataSource;
import com.sky.lazycat.data.neihanduanzi.NeiHanFirst;
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
        service.getNeiHanList().enqueue(new Callback<NeiHanFirst>() {
            @Override
            public void onResponse(Call<NeiHanFirst> call, Response<NeiHanFirst> response) {

                callback.onNewsLoaded(response.body().getData().getData());
            }

            @Override
            public void onFailure(Call<NeiHanFirst> call, Throwable t) {
                callback.onDataNotAvailable();
                Log.i("getData","DataFail:"+t.toString());
                t.toString();
            }
        });
    }

    @Override
    public void getItem(int itemId, final GetDataItemCallback callback) {




    }

    @Override
    public void saveAll(List<NeiHanAll.DataBean> list) {

    }
}
