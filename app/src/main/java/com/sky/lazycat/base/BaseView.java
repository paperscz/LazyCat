package com.sky.lazycat.base;

import android.view.View;

/**
 * Created by yuetu-develop on 2017/7/5.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void initViews(View view);
}
