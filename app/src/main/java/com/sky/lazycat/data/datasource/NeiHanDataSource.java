package com.sky.lazycat.data.datasource;

import com.sky.lazycat.data.neihanduanzi.NeiHanAll;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public interface NeiHanDataSource {

    interface LoadNeiHanDataCallback{
        void onNewsLoaded(List<NeiHanAll.DataBean> list);
        void onDataNotAvailable();
    }
    interface GetDataItemCallback {
        void onItemLoaded(NeiHanAll.DataBean item);
        void onDataNotAvailable();
    }

    void getNeiHanDailyData(boolean forceUpdate,LoadNeiHanDataCallback callback);

    void getItem(int itemId,GetDataItemCallback callback);

    void saveAll(List<NeiHanAll.DataBean> list);
}
