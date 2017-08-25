package com.sky.lazycat.timeline.zhihu;

import com.sky.lazycat.data.datasource.ZhihuDataSource;
import com.sky.lazycat.data.zhihu.Zhihu;

/**
 * Created by yuetu-develop on 2017/8/25.
 */

public class ZhihuDataRepository implements ZhihuDataSource{

    private static ZhihuDataRepository INSTANCE = null;
    private final ZhihuDataSource mRemoteDataSource;

    private ZhihuDataRepository(ZhihuDataSource remoteDataSource){
        this.mRemoteDataSource = remoteDataSource;
    }

    public static ZhihuDataRepository getInstance(ZhihuDataSource remoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new ZhihuDataRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getZhihuData(boolean isLatest,boolean forceUpdate,String date,final LoadZhihuDataCallback callback) {

        mRemoteDataSource.getZhihuData(isLatest,forceUpdate,date,new LoadZhihuDataCallback() {
            @Override
            public void onZhihuLoaded(Zhihu zhihu) {
                callback.onZhihuLoaded(zhihu);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }

    @Override
    public void getNew(long id, LoadZhihuNewCallback callback) {

    }
}
