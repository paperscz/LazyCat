package com.sky.lazycat.details;

import android.support.annotation.NonNull;

import com.sky.lazycat.R;
import com.sky.lazycat.data.datasource.ZhihuDailyContentDataSource;
import com.sky.lazycat.data.zhihu.ZhihuDailyContent;

/**
 * Created by yuetu-develop on 2017/9/4.
 */

public class DetailsPresenter implements DetailsContract.Presenter{

    private final DetailsContract.View mView;

    private ZhihuDailyContentRepository mZhihuContentRepository;

    public DetailsPresenter(@NonNull DetailsContract.View view,
                            @NonNull ZhihuDailyContentRepository zhihuContentRepository) {
        this.mView = view;
        mView.setPresenter(this);
        this.mZhihuContentRepository = zhihuContentRepository;
    }
    @Override
    public void start() {

    }

    @Override
    public void loadZhihuDailyContent(int id) {
        mZhihuContentRepository.getZhihuDailyContent(id, new ZhihuDailyContentDataSource.LoadZhihuDailyContentCallback() {
            @Override
            public void onContentLoaded(ZhihuDailyContent content) {
                if (mView.isActive()) {
                    mView.showZhihuDailyContent(content);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mView.showMessage(R.string.something_wrong);
            }
        });
    }
}
