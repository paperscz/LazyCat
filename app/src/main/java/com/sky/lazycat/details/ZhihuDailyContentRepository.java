package com.sky.lazycat.details;

import android.support.annotation.NonNull;

import com.sky.lazycat.data.datasource.ZhihuDailyContentDataSource;
import com.sky.lazycat.data.zhihucontent.ZhihuDailyContent;

/**
 * Created by yuetu-develop on 2017/9/4.
 */

public class ZhihuDailyContentRepository implements ZhihuDailyContentDataSource{

    public static ZhihuDailyContentRepository INSTANCE = null;
    private final ZhihuDailyContentDataSource mRemoteDataSource;
    private ZhihuDailyContent mContent;

    public static ZhihuDailyContentRepository getInstance(ZhihuDailyContentDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ZhihuDailyContentRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    private ZhihuDailyContentRepository(ZhihuDailyContentDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getZhihuDailyContent(int id, final LoadZhihuDailyContentCallback callback) {
        if (mContent != null) {
            callback.onContentLoaded(mContent);
            return;
        }
        mRemoteDataSource.getZhihuDailyContent(id, new LoadZhihuDailyContentCallback() {
            @Override
            public void onContentLoaded(@NonNull ZhihuDailyContent content) {
                if (mContent == null) {
                    mContent = content;
                    saveContent(content);
                }
                callback.onContentLoaded(content);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });

    }

    @Override
    public void saveContent( ZhihuDailyContent content) {
        // 后期添加保存信息功能
    }
}
