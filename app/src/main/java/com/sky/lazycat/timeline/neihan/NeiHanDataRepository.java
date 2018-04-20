package com.sky.lazycat.timeline.neihan;

import com.sky.lazycat.data.datasource.NeiHanDataSource;
import com.sky.lazycat.data.neihanduanzi.NeiHanDuanZi;

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
    public void getNeiHanDailyData(boolean forceUpdate,int type, final LoadNeiHanDataCallback callback) {

        mRemoteDataSource.getNeiHanDailyData(forceUpdate,type, new LoadNeiHanDataCallback() {
            @Override
            public void onNewsLoaded(List<NeiHanDuanZi.DuanziX.Duanzi> list) {
                // 从remote实现类里面回调了数据
               // Log.i("getData","DataSuccess长度:"+list.size());
                callback.onNewsLoaded(list);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getItem(int itemId, GetDataItemCallback callback) {

    }

    @Override
    public void saveAll(List<NeiHanDuanZi.DuanziX.Duanzi> list) {

    }
}
