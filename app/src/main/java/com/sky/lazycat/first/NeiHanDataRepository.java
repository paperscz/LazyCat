package com.sky.lazycat.first;

import com.sky.lazycat.data.NeiHanGroup;
import com.sky.lazycat.data.datasource.NeiHanDataSource;

import java.util.ArrayList;
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
            public void onNewsLoaded(List<NeiHanGroup> list) {
                callback.onNewsLoaded(new ArrayList<NeiHanGroup>());

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
    public void saveAll(List<NeiHanGroup> list) {

    }
}
