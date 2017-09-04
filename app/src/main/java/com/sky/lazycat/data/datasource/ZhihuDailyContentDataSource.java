package com.sky.lazycat.data.datasource;

import android.support.annotation.NonNull;

import com.sky.lazycat.data.zhihucontent.ZhihuDailyContent;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/9/4.
 */

public interface ZhihuDailyContentDataSource {

    interface LoadZhihuDailyContentCallback {

        void onContentLoaded(@NonNull ZhihuDailyContent content);

        void onDataNotAvailable();

    }

    void getZhihuDailyContent(int id, @NonNull LoadZhihuDailyContentCallback callback);

    void saveContent(@NonNull ZhihuDailyContent content);
}
