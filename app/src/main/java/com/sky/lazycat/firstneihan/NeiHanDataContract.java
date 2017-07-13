package com.sky.lazycat.firstneihan;

import com.sky.lazycat.base.BasePresenter;
import com.sky.lazycat.base.BaseView;
import com.sky.lazycat.data.neihanduanzi.NeiHanAll;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public interface NeiHanDataContract {

    interface View extends BaseView<Presenter>{
        boolean isActive();
        void setLoadingIndicator(boolean active);
        void showResult(List<NeiHanAll.DataBean> list);
    }

    interface Presenter extends BasePresenter{
        void loadNeiHan(boolean forceUpdate);
    }

}
