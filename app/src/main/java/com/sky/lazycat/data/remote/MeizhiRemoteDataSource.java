package com.sky.lazycat.data.remote;

import com.sky.lazycat.data.meizhi.MeizhiData;
import com.sky.lazycat.data.datasource.MeizhiDataSource;
import com.sky.lazycat.retrofit.RetrofitService;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.MEIZHI_DATA_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService.MeizhiService service = retrofit.create(RetrofitService.MeizhiService.class);

        service.getMeizhiData(page)
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
