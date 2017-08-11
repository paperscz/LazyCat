package com.sky.lazycat.data.datasource;

import com.sky.lazycat.data.Meizhi.MeizhiData;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/21.
 */

public interface MeizhiDataSource {

    interface LoadMeizhiDataCallback{
        void onNewsLoaded(List<MeizhiData.MeizhiBean> list);
        void onDataNotAvailable();
    }

    void getMeizhiData(int page,boolean forceUpdate,LoadMeizhiDataCallback callback);

}
