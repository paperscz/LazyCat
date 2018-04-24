package com.sky.lazycat.timeline.doubanmovie;

import com.sky.lazycat.data.datasource.DouBanMovieDataSource;
import com.sky.lazycat.data.doubanmovie.DouBanMovie;

import java.util.List;

/**
 * Created by yuetu-develop on 2018/4/23.
 */

public class DouBanMovieDataRepository implements DouBanMovieDataSource{

    private static DouBanMovieDataRepository INSTANCE = null;
    private final DouBanMovieDataSource mRemoteDataSource;

    private DouBanMovieDataRepository (DouBanMovieDataSource remoteDataSource){
        this.mRemoteDataSource = remoteDataSource;
    }

    public static DouBanMovieDataRepository getInstance(DouBanMovieDataSource remoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new DouBanMovieDataRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getDouBanMovieData(boolean forceUpdate, String type,int startCount, final LoadDouBanMovieCallback callback) {
        mRemoteDataSource.getDouBanMovieData(forceUpdate, type, startCount, new LoadDouBanMovieCallback() {
            @Override
            public void onMovieLoaded(DouBanMovie douBanMovie) {
                callback.onMovieLoaded(douBanMovie);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveAll(List<DouBanMovie.SubjectsBean> list) {

    }
}
