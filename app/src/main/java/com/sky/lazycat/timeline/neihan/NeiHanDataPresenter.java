package com.sky.lazycat.timeline.neihan;

import com.sky.lazycat.data.datasource.NeiHanDataSource;
import com.sky.lazycat.data.neihanduanzi.NeiHanDuanZi;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public class NeiHanDataPresenter implements NeiHanDataContract.Presenter{

    private final NeiHanDataContract.View mView;
    private final NeiHanDataRepository mRepository;

    public NeiHanDataPresenter(NeiHanDataContract.View mView,NeiHanDataRepository repository){
        this.mView = mView;
        this.mRepository = repository;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadNeiHan(boolean forceUpdate,int type) {
        mRepository.getNeiHanDailyData(forceUpdate, type,new NeiHanDataSource.LoadNeiHanDataCallback() {
            @Override
            public void onNewsLoaded(List<NeiHanDuanZi.DuanziX.Duanzi> list) {
                if(mView.isActive()){
                    mView.showResult(list);
                    mView.setLoadingIndicator(false);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (mView.isActive()) {
                    mView.setLoadingIndicator(false);
                }
            }
        });
    }
}
