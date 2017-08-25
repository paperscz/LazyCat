package com.sky.lazycat.timeline.zhihu;

import com.sky.lazycat.data.datasource.ZhihuDataSource;
import com.sky.lazycat.data.zhihu.Zhihu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/25.
 */

public class ZhihuPresenter implements ZhihuDataContract.Presenter {

    private final ZhihuDataContract.View mView;
    private final ZhihuDataRepository mZhihuRepository;

    public ZhihuPresenter(ZhihuDataContract.View mView,
                          ZhihuDataRepository zhihuRepository){
        this.mView = mView;
        this.mZhihuRepository = zhihuRepository;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {   }

    @Override
    public void loadZhihu(boolean isLatest,boolean forceUpdate,String date) {
        mZhihuRepository.getZhihuData(isLatest,forceUpdate,date,new ZhihuDataSource.LoadZhihuDataCallback() {
            @Override
            public void onZhihuLoaded(Zhihu zhihu) {
                if(mView.isActive()){
                    mView.showResult(zhihu.getStories(),zhihu.getTop_stories());
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

//    private List<String> getTopTitles(List<Zhihu.TopStoriesBean> listTop){
//        List<String> listTitle = new ArrayList<>();
//        for(Zhihu.TopStoriesBean topStoriesBean : listTop){
//            listTitle.add(topStoriesBean.getTitle());
//        }
//        return listTitle;
//    }

}
