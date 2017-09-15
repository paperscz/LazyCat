package com.sky.lazycat.data.datasource;

import com.sky.lazycat.data.neihanduanzi.NeiHanVideo;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/9/15.
 */

public interface NeiHanVideoDataSource {

    interface LoadNeiHanVideoDataCallback{
        void onNewsLoaded(List<NeiHanVideo.DataBeanX.DataBean.NeiHanVideoBean> list);
        void onDataNotAvailable();
    }
    interface GetDataItemCallback {
        void onItemLoaded(NeiHanVideo.DataBeanX.DataBean.NeiHanVideoBean item);
        void onDataNotAvailable();
    }

    void getNeiHanVideoDailyData(boolean forceUpdate,LoadNeiHanVideoDataCallback callback);

    void getItem(int itemId,GetDataItemCallback callback);

    void saveAll(List<NeiHanVideo.DataBeanX.DataBean.NeiHanVideoBean> list);

}
