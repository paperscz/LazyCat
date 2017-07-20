package com.sky.lazycat.firstneihan;

import android.util.Log;

import com.sky.lazycat.data.neihanduanzi.NeiHanAll;
import com.sky.lazycat.data.datasource.NeiHanDataSource;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public class NeiHanDataRepository implements NeiHanDataSource{

    private static NeiHanDataRepository INSTANCE = null;
    private final NeiHanDataSource mRemoteDataSource;

    private NeiHanDataRepository (NeiHanDataSource remoteDataSource){
        this.mRemoteDataSource = remoteDataSource;
    }

    public static NeiHanDataRepository getInstance(NeiHanDataSource remoteDataSource){

        if(INSTANCE == null){
            INSTANCE = new NeiHanDataRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getNeiHanDailyData(boolean forceUpdate, final LoadNeiHanDataCallback callback) {

        mRemoteDataSource.getNeiHanDailyData(false, new LoadNeiHanDataCallback() {
            @Override
            public void onNewsLoaded(List<NeiHanAll.DataBean> list) {
                // 从remote实现类里面回调了数据
               // Log.i("getData","DataSuccess长度:"+list.size());
                callback.onNewsLoaded(list);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
                Log.i("getData","DataFail");
            }
        });
    }

    @Override
    public void getItem(int itemId, GetDataItemCallback callback) {

    }

    @Override
    public void saveAll(List<NeiHanAll.DataBean> list) {

    }
}
