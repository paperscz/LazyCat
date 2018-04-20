package com.sky.lazycat.data.remote;

import android.app.Application;

import com.sky.lazycat.data.meizhi.MeizhiData;
import com.sky.lazycat.data.datasource.MeizhiDataSource;
import com.sky.lazycat.retrofit.DrakeetFactory;
import com.sky.lazycat.retrofit.RetrofitService;
import com.sky.lazycat.util.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yuetu-develop on 2017/8/1.
 */

public class MeizhiRemoteDataSource implements MeizhiDataSource {

    private static MeizhiRemoteDataSource INSTANCE = null;

    private MeizhiRemoteDataSource(){
    }

    public static MeizhiRemoteDataSource geInstance(){
        if(INSTANCE == null){
            INSTANCE = new MeizhiRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getMeizhiData(int page, boolean forceUpdate, final LoadMeizhiDataCallback callback) {

        DrakeetFactory.getMeiZhiSingleton().getMeizhiData(page)
                .enqueue(new Callback<MeizhiData>() {
                    @Override
                    public void onResponse(Call<MeizhiData> call, Response<MeizhiData> response) {
                        callback.onNewsLoaded(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<MeizhiData> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });

    }
}
