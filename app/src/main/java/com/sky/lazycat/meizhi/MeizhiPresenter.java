package com.sky.lazycat.meizhi;

import com.sky.lazycat.data.Meizhi.MeizhiData;
import com.sky.lazycat.data.datasource.MeizhiDataSource;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/21.
 */

public class MeizhiPresenter implements MeizhiDataContract.Presenter{

    private final MeizhiDataContract.View mView;
    private final MeizhiDataRepository mRepository;


    public MeizhiPresenter(MeizhiDataContract.View mView,MeizhiDataRepository mRepository){
        this.mView = mView;
        this.mRepository = mRepository;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadMeizhi(int page, boolean forceUpdate) {
        mRepository.getMeizhiData(page,forceUpdate, new MeizhiDataSource.LoadMeizhiDataCallback() {
            @Override
            public void onNewsLoaded(List<MeizhiData.MeizhiBean> list) {
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
