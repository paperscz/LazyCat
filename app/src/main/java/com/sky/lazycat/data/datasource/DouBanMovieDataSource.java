package com.sky.lazycat.data.datasource;

import com.sky.lazycat.data.doubanmovie.DouBanMovie;

import java.util.List;

/**
 * Created by yuetu-develop on 2018/4/23.
 */

public interface DouBanMovieDataSource {

    interface LoadDouBanMovieCallback{
        void onMovieLoaded(DouBanMovie douBanMovie);
        void onDataNotAvailable();
    }

    void getDouBanMovieData(boolean forceUpdate,String type,int startCount,LoadDouBanMovieCallback callback);

    void saveAll(List<DouBanMovie.SubjectsBean> list);
}
