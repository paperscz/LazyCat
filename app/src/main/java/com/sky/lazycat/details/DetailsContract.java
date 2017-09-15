package com.sky.lazycat.details;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.sky.lazycat.base.BasePresenter;
import com.sky.lazycat.base.BaseView;
import com.sky.lazycat.data.zhihu.ZhihuDailyContent;

/**
 * Created by yuetu-develop on 2017/9/4.
 */

public interface DetailsContract {

    interface View extends BaseView<Presenter> {

        void showMessage(@StringRes int stringRes);
        boolean isActive();
        void showZhihuDailyContent(@NonNull ZhihuDailyContent content);

    }

    interface Presenter extends BasePresenter {

        void loadZhihuDailyContent(int id);

    }
}
