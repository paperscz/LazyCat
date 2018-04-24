package com.sky.lazycat.data.remote;

import com.sky.lazycat.data.datasource.DouBanMovieDataSource;
import com.sky.lazycat.data.doubanmovie.DouBanMovie;
import com.sky.lazycat.retrofit.DrakeetFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuetu-develop on 2018/4/23.
 */

public class DouBanMoiveRemoteDataSource implements DouBanMovieDataSource {

    private static DouBanMoiveRemoteDataSource INSTANCE = null;

    public DouBanMoiveRemoteDataSource(){
    }

    public static DouBanMoiveRemoteDataSource getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DouBanMoiveRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getDouBanMovieData(boolean forceUpdate, String type, int startCount,final LoadDouBanMovieCallback callback) {
        DrakeetFactory.getDouBanMovieSingleton().getDouBanMovies(type,startCount)
                .enqueue(new Callback<DouBanMovie>() {
                    @Override
                    public void onResponse(Call<DouBanMovie> call, Response<DouBanMovie> response) {
                        callback.onMovieLoaded(response.body());
                    }

                    @Override
                    public void onFailure(Call<DouBanMovie> call, Throwable t) {
                        callback.onDataNotAvailable();
                    }
                });
    }

    @Override
    public void saveAll(List<DouBanMovie.SubjectsBean> list) {

    }
}
