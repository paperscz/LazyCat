package com.sky.lazycat.timeline.zhihu;

import com.sky.lazycat.base.BasePresenter;
import com.sky.lazycat.base.BaseView;
import com.sky.lazycat.data.neihanduanzi.NeiHanAll;
import com.sky.lazycat.data.zhihu.Zhihu;
import com.sky.lazycat.timeline.neihan.NeiHanDataContract;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/25.
 */

public interface ZhihuDataContract {

    interface View extends BaseView<ZhihuDataContract.Presenter> {
        boolean isActive();
        void setLoadingIndicator(boolean active);
        void showResult(List<Zhihu.StoriesBean> listStories,List<Zhihu.TopStoriesBean> listTopStories);
    }

    interface Presenter extends BasePresenter {
        void loadZhihu(boolean isLatest,boolean forceUpdate,String date);
    }
}
