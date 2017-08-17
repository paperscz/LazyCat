package com.sky.lazycat.data.datasource;

import com.sky.lazycat.data.gankvideo.GankVideoData;
import com.sky.lazycat.data.meizhi.MeizhiData;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/14.
 */

public interface GankVideoDataSource {

    interface LoadGankVideoDataCallback{
        void onNewsLoaded(List<GankVideoData.GankVideoBean> list);
        void onDataNotAvailable();
    }

    void getGankVideoData(int page,boolean forceUpdate,LoadGankVideoDataCallback callback);
}
