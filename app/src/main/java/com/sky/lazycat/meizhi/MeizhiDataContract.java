package com.sky.lazycat.meizhi;

import android.support.annotation.NonNull;

import com.sky.lazycat.base.BasePresenter;
import com.sky.lazycat.base.BaseView;
import com.sky.lazycat.data.meizhi.MeizhiData;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/21.
 */

public interface MeizhiDataContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        boolean isActive();

        void showResult(@NonNull List<MeizhiData.MeizhiBean> list);

    }

    interface Presenter extends BasePresenter {

        void loadMeizhi(int page, boolean forceUpdate);

    }

}
