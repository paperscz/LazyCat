package com.sky.lazycat.meizhi;


import com.sky.lazycat.data.Meizhi.MeizhiData;
import com.sky.lazycat.data.datasource.MeizhiDataSource;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/21.
 */

public class MeizhiDataRepository implements MeizhiDataSource{

    private static MeizhiDataRepository INSTANCE = null;
    private final MeizhiDataSource mRemoteDataSource;

    private MeizhiDataRepository (MeizhiDataSource remoteDataSource){
        this.mRemoteDataSource = remoteDataSource;
    }

    public static MeizhiDataRepository getInstance(MeizhiDataSource remoteDataSource){

        if(INSTANCE == null){
            INSTANCE = new MeizhiDataRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getMeizhiData(int page, boolean forceUpdate, final LoadMeizhiDataCallback callback) {

        mRemoteDataSource.getMeizhiData(page,forceUpdate, new LoadMeizhiDataCallback() {
            @Override
            public void onNewsLoaded(List<MeizhiData.MeizhiBean> list) {
                // Log.i("getData","DataSuccess长度:"+list.size());
                callback.onNewsLoaded(list);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }


}
