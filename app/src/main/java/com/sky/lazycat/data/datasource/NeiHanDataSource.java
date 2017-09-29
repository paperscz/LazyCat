package com.sky.lazycat.data.datasource;

import com.sky.lazycat.data.neihanduanzi.NeiHanDuanZi;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public interface NeiHanDataSource {

    interface LoadNeiHanDataCallback{
        void onNewsLoaded(List<NeiHanDuanZi.DuanziX.Duanzi> list);
        void onDataNotAvailable();
    }

    interface GetDataItemCallback {
        void onItemLoaded(NeiHanDuanZi.DuanziX.Duanzi item);
        void onDataNotAvailable();
    }

    void getNeiHanDailyData(boolean forceUpdate,LoadNeiHanDataCallback callback);

    void getItem(int itemId,GetDataItemCallback callback);

    void saveAll(List<NeiHanDuanZi.DuanziX.Duanzi> list);
}
