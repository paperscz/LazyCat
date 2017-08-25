package com.sky.lazycat.data.remote;

import com.sky.lazycat.data.datasource.ZhihuDataSource;
import com.sky.lazycat.data.zhihu.Zhihu;
import com.sky.lazycat.retrofit.DrakeetFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuetu-develop on 2017/8/25.
 */

public class ZhihuRemoteDataSource implements ZhihuDataSource {

    private static ZhihuRemoteDataSource INSTANCE = null;

    private ZhihuRemoteDataSource(){
    }

    public static ZhihuRemoteDataSource geInstance(){
        if(INSTANCE == null){
            INSTANCE = new ZhihuRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getZhihuData(boolean isLatest,boolean forceUpdate,String date,final LoadZhihuDataCallback callback) {

        // 为了获取bannerview数据，访问latest
        if(isLatest){
            DrakeetFactory.getZhihuSingleton().getZhihuLatestNews().enqueue(new Callback<Zhihu>() {
                @Override
                public void onResponse(Call<Zhihu> call, Response<Zhihu> response) {
                    callback.onZhihuLoaded(response.body());
                }

                @Override
                public void onFailure(Call<Zhihu> call, Throwable t) {
                    callback.onDataNotAvailable();
                }
            });
        } else {
            DrakeetFactory.getZhihuSingleton().getZhihuNews(date).enqueue(new Callback<Zhihu>() {
                @Override
                public void onResponse(Call<Zhihu> call, Response<Zhihu> response) {
                    callback.onZhihuLoaded(response.body());
                }

                @Override
                public void onFailure(Call<Zhihu> call, Throwable t) {
                    callback.onDataNotAvailable();
                }
            });
        }

    }

    @Override
    public void getNew(long id, LoadZhihuNewCallback callback) {

    }
}
