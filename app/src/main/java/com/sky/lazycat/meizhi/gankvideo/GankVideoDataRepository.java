package com.sky.lazycat.meizhi.gankvideo;

import com.sky.lazycat.data.datasource.GankVideoDataSource;
import com.sky.lazycat.data.gankvideo.GankVideoData;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/14.
 */

public class GankVideoDataRepository implements GankVideoDataSource {

    private static GankVideoDataRepository INSTANCE = null;
    private final GankVideoDataSource mRemoteDataSource;

    private GankVideoDataRepository (GankVideoDataSource remoteDataSource){
        this.mRemoteDataSource = remoteDataSource;
    }

    public static GankVideoDataRepository getInstance(GankVideoDataSource remoteDataSource){

        if(INSTANCE == null){
            INSTANCE = new GankVideoDataRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getGankVideoData(int page, boolean forceUpdate, final LoadGankVideoDataCallback callback) {
        mRemoteDataSource.getGankVideoData(page, forceUpdate, new LoadGankVideoDataCallback() {
            @Override
            public void onNewsLoaded(List<GankVideoData.GankVideoBean> list) {
                callback.onNewsLoaded(list);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
