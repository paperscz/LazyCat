package com.sky.lazycat.timeline.doubanmovie;

import com.sky.lazycat.data.datasource.DouBanMovieDataSource;
import com.sky.lazycat.data.doubanmovie.DouBanMovie;

/**
 * Created by yuetu-develop on 2018/4/23.
 */

public class DouBanMovieDataPresenter implements DouBanMovieDataContract.Presenter{

    private final DouBanMovieDataContract.View mView;
    private final DouBanMovieDataRepository mZhihuRepository;

    public DouBanMovieDataPresenter(DouBanMovieDataContract.View mView, DouBanMovieDataRepository mZhihuRepository) {
        this.mView = mView;
        this.mZhihuRepository = mZhihuRepository;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void loadDouBanMovie(boolean forceUpdate, String type,int startCount) {
        mZhihuRepository.getDouBanMovieData(forceUpdate, type,startCount, new DouBanMovieDataSource.LoadDouBanMovieCallback() {
            @Override
            public void onMovieLoaded(DouBanMovie douBanMovie) {
                if(mView.isActive()){
                    mView.showResult(douBanMovie);
                    mView.setLoadingIndicator(false);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (mView.isActive()) {
                    // 数据获取失败，可以设置显示 Toast、SnakeBar 等
                    mView.setLoadingIndicator(false);
                }
            }
        });
    }
}
