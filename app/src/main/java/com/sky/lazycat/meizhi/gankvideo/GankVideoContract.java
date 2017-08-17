package com.sky.lazycat.meizhi.gankvideo;

import android.support.annotation.NonNull;

import com.sky.lazycat.base.BasePresenter;
import com.sky.lazycat.base.BaseView;
import com.sky.lazycat.data.gankvideo.GankVideoData;
import com.sky.lazycat.meizhi.MeizhiDataContract;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/14.
 */

public interface GankVideoContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        boolean isActive();

        void showResult(@NonNull List<GankVideoData.GankVideoBean> list);

    }
    interface Presenter extends BasePresenter {

        void loadGankVideo(int page, boolean forceUpdate);

    }
}
