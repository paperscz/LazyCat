package com.sky.lazycat.data.remote;

import android.support.annotation.NonNull;

import com.sky.lazycat.data.datasource.ZhihuDailyContentDataSource;
import com.sky.lazycat.data.zhihu.ZhihuDailyContent;
import com.sky.lazycat.retrofit.DrakeetFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuetu-develop on 2017/9/4.
 */

public class ZhihuDailyContentRemoteDataSource implements ZhihuDailyContentDataSource {

    private static ZhihuDailyContentRemoteDataSource INSTANCE = null;

    private ZhihuDailyContentRemoteDataSource() {}

    public static ZhihuDailyContentRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ZhihuDailyContentRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getZhihuDailyContent(int id, @NonNull final LoadZhihuDailyContentCallback callback) {
        DrakeetFactory.getZhihuSingleton().getZhihuContent(id)
                .enqueue(new Callback<ZhihuDailyContent>() {
                    @Override
                    public void onResponse(Call<ZhihuDailyContent> call, Response<ZhihuDailyContent> response) {
                        callback.onContentLoaded(response.body());
                    }

                    @Override
                    public void onFailure(Call<ZhihuDailyContent> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void saveContent(@NonNull ZhihuDailyContent content) {
        // Not required for the local data source because the {@link TasksRepository} handles
        // converting from a {@code taskId} to a {@link task} using its cached data.
    }
}
