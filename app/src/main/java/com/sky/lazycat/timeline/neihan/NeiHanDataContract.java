package com.sky.lazycat.timeline.neihan;

import com.sky.lazycat.base.BasePresenter;
import com.sky.lazycat.base.BaseView;
import com.sky.lazycat.data.neihanduanzi.NeiHanDuanZi;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public interface NeiHanDataContract {

    interface View extends BaseView<Presenter>{
        boolean isActive();
        void setLoadingIndicator(boolean active);
        void showResult(List<NeiHanDuanZi.DuanziX.Duanzi> list);
    }

    interface Presenter extends BasePresenter{
        void loadNeiHan(boolean forceUpdate);
    }

}
