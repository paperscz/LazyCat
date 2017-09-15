package com.sky.lazycat.timeline.neihanvideo;

import com.sky.lazycat.base.BasePresenter;
import com.sky.lazycat.base.BaseView;
import com.sky.lazycat.data.neihanduanzi.NeiHanVideo;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/9/15.
 */

public interface NeiHanVideoContract {

    interface View extends BaseView<Presenter> {
        boolean isActive();
        void setLoadingIndicator(boolean active);
        void showResult(List<NeiHanVideo.DataBeanX.DataBean.NeiHanVideoBean> list);
    }

    interface Presenter extends BasePresenter {
        void loadNeiHanVideo(boolean forceUpdate);
    }

}
