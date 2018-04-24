package com.sky.lazycat.timeline.doubanmovie;

import com.sky.lazycat.base.BasePresenter;
import com.sky.lazycat.base.BaseView;
import com.sky.lazycat.data.doubanmovie.DouBanMovie;

import java.util.List;

/**
 * Created by yuetu-develop on 2018/4/23.
 */

public interface DouBanMovieDataContract {

    interface View extends BaseView<Presenter> {
        boolean isActive();
        void setLoadingIndicator(boolean active);
        void showResult(DouBanMovie douBanMovie);
    }

    interface Presenter extends BasePresenter {
        void loadDouBanMovie(boolean forceUpdate,String type,int startCount);
    }

}
