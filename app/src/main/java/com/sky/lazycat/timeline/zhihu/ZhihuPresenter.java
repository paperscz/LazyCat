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
    public void start() {
    }

    @Override
    public void loadZhihu(final boolean isLatest, boolean forceUpdate, String date) {
        mZhihuRepository.getZhihuData(isLatest,forceUpdate,date,new ZhihuDataSource.LoadZhihuDataCallback() {
            @Override
            public void onZhihuLoaded(Zhihu zhihu) {
                if(mView.isActive()){
                    if(isLatest){
                        mView.showResult(zhihu.getStories(),zhihu.getTop_stories(),getTopImgs(zhihu.getTop_stories(),0),getTopImgs(zhihu.getTop_stories(),1));
                    } else {
                        mView.showResult(zhihu.getStories(),null,null,null);
                    }
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

    private List<String> getTopImgs(List<Zhihu.TopStoriesBean> listTop,int type){
        List<String> listTitle = new ArrayList<>();
        // 0是获取banner Images
        if(type == 0){
            for(Zhihu.TopStoriesBean topStoriesBean : listTop){
                listTitle.add(topStoriesBean.getImage());
            }
        }else {
            for(Zhihu.TopStoriesBean topStoriesBean : listTop){
                listTitle.add(topStoriesBean.getTitle());
            }
        }
        return listTitle;
    }


}
