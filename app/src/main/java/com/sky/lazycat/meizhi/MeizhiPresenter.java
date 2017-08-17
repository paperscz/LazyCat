package com.sky.lazycat.meizhi;

import android.text.TextUtils;

import com.sky.lazycat.data.datasource.GankVideoDataSource;
import com.sky.lazycat.data.gankvideo.GankVideoData;
import com.sky.lazycat.data.meizhi.MeizhiData;
import com.sky.lazycat.data.datasource.MeizhiDataSource;
import com.sky.lazycat.meizhi.gankvideo.GankVideoDataRepository;
import com.sky.lazycat.util.DateFormatUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/21.
 */

public class MeizhiPresenter implements MeizhiDataContract.Presenter{

    private final MeizhiDataContract.View mView;
    private final MeizhiDataRepository mMeizhiRepository;
    private final GankVideoDataRepository mGankVideoRepository;

    public MeizhiPresenter(MeizhiDataContract.View mView,
                           MeizhiDataRepository meizhiRepository,
                           GankVideoDataRepository gankVideoDataRepository){
        this.mView = mView;
        this.mMeizhiRepository = meizhiRepository;
        this.mGankVideoRepository = gankVideoDataRepository;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadMeizhi(final int page, final boolean forceUpdate) {

        mMeizhiRepository.getMeizhiData(page,forceUpdate, new MeizhiDataSource.LoadMeizhiDataCallback() {
            @Override
            public void onNewsLoaded(final List<MeizhiData.MeizhiBean> meizhiBeanList) {

                mGankVideoRepository.getGankVideoData(page, forceUpdate, new GankVideoDataSource.LoadGankVideoDataCallback() {
                    @Override
                    public void onNewsLoaded(List<GankVideoData.GankVideoBean> gankVideoBeanList) {

                        if(mView.isActive()){
                            mView.showResult(createMeizhiDataWithVideoDesc(meizhiBeanList,gankVideoBeanList));
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

            @Override
            public void onDataNotAvailable() {}
        });
    }
    // 把视频list中描述文字赋值添加到妹纸list的文字描述中
    private List<MeizhiData.MeizhiBean> createMeizhiDataWithVideoDesc(List<MeizhiData.MeizhiBean> mMeizhiList, List<GankVideoData.GankVideoBean> mVideoList) {
        List<MeizhiData.MeizhiBean> mNewMeizhiList = new ArrayList<>();
        for (MeizhiData.MeizhiBean meizhi : mMeizhiList) {
            meizhi.desc = meizhi.desc + " " +
                    getFirstVideoDesc(meizhi.getPublishedAt(), mVideoList);
            mNewMeizhiList.add(meizhi);
        }
        mMeizhiList.clear();
        mLastVideoIndex = 0;
        return mNewMeizhiList;
    }

    private int mLastVideoIndex = 0;

    private String getFirstVideoDesc(String publishedAt, List<GankVideoData.GankVideoBean> mVideoList) {
        String videoDesc = "";
        for (int i = mLastVideoIndex; i < mVideoList.size(); i++) {
            GankVideoData.GankVideoBean video = mVideoList.get(i);
            if (TextUtils.isEmpty(video.getPublishedAt())) {
                video.setPublishedAt(video.getCreatedAt());
            }
            if (DateFormatUtil.isTheSameDay(publishedAt, video.getPublishedAt())) {
                videoDesc = video.getDesc();
                mLastVideoIndex = i;
                break;
            }
        }

        return videoDesc;
    }

}
