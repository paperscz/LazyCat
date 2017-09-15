package com.sky.lazycat.data.remote;

import com.sky.lazycat.data.datasource.NeiHanVideoDataSource;
import com.sky.lazycat.data.neihanduanzi.NeiHanVideo;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/9/15.
 */

public class NeiHanVideoRemoteDataSource implements NeiHanVideoDataSource {

    public static NeiHanVideoRemoteDataSource INSTANCE = null;

    public NeiHanVideoRemoteDataSource(){

    }

    public static NeiHanVideoRemoteDataSource geInstance(){
        if(INSTANCE == null){
            INSTANCE = new NeiHanVideoRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getNeiHanVideoDailyData(boolean forceUpdate, LoadNeiHanVideoDataCallback callback) {

    }

    @Override
    public void getItem(int itemId, GetDataItemCallback callback) {

    }

    @Override
    public void saveAll(List<NeiHanVideo.DataBeanX.DataBean.NeiHanVideoBean> list) {

    }
}
