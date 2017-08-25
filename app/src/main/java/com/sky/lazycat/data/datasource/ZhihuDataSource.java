package com.sky.lazycat.data.datasource;

import com.sky.lazycat.data.neihanduanzi.NeiHanAll;
import com.sky.lazycat.data.zhihu.Zhihu;
import com.sky.lazycat.data.zhihu.ZhihuNew;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/25.
 */

public interface ZhihuDataSource {

    interface LoadZhihuDataCallback{
        void onZhihuLoaded(Zhihu zhihu);
        void onDataNotAvailable();
    }

    interface LoadZhihuNewCallback{
        void onZhihuNewLoaded(ZhihuNew zhihuNew);
    }

    void getZhihuData(boolean isLatest,boolean forceUpdate,String date,ZhihuDataSource.LoadZhihuDataCallback callback);

    void getNew(long id,ZhihuDataSource.LoadZhihuNewCallback callback);

}
