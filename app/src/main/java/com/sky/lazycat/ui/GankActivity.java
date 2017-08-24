package com.sky.lazycat.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sky.lazycat.R;
import com.sky.lazycat.data.meizhi.GankData;
import com.sky.lazycat.retrofit.DrakeetFactory;
import com.sky.lazycat.util.DateFormatUtil;
import com.sky.lazycat.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuetu-develop on 2017/8/17.
 */

public class GankActivity extends AppCompatActivity {

    public static final String EXTRA_GANK_DATE = "gank_date";
    public static final String EXTRA_VIDEO_URL = "video_url";

    @BindView(R.id.rv_ganklist) RecyclerView rv_ganklist;
    @BindView(R.id.iv_gankhead) ImageView iv_gankhead;
    private String mVideoUrl;
    private String mGankDate;
    private List<GankData.Gank> mGankList;
    private GankListQuickAdapter mAdapter;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        unbinder = ButterKnife.bind(this);
        setRecyclerView();
        parseIntent();
        loadData();
    }

    private void setRecyclerView() {
        rv_ganklist.setHasFixedSize(true);
        rv_ganklist.setLayoutManager(new LinearLayoutManager(this));
        mGankList = new ArrayList<>();
        mAdapter = new GankListQuickAdapter(mGankList);
        rv_ganklist.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String title = mGankList.get(position).desc;
                String mUrl = mGankList.get(position).url;
                Intent intent = WebActivity.newIntent(GankActivity.this,mUrl,title);
                startActivity(intent);
            }
        });

//        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//               // String s = mGankList.get(position).desc;
//               // ToastUtils.showShort(GankActivity.this,position+"");
//            }
//        });
    }

    @OnClick(R.id.iv_gankhead) void playVideo(){
        if(!TextUtils.isEmpty(mVideoUrl)){
            ToastUtils.showShort(GankActivity.this,"播放视频"+mVideoUrl);
        } else {
            ToastUtils.showShort(GankActivity.this,getString(R.string.tip_for_no_gank));
        }
    }

    private void parseIntent() {
        mGankDate = getIntent().getStringExtra(EXTRA_GANK_DATE);
        mVideoUrl= getIntent().getStringExtra(EXTRA_VIDEO_URL);
    }

    private void loadData() {
        DrakeetFactory.getGankIOSingleton().getGankData(DateFormatUtil.formatDateStringToString(mGankDate))
                .enqueue(new Callback<GankData>() {
                    @Override
                    public void onResponse(Call<GankData> call, Response<GankData> response) {
                        //ToastUtils.showShort(GankActivity.this,"数据获取成功");
                        addAllResults(response.body().results);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<GankData> call, Throwable t) {
                        ToastUtils.showShort(GankActivity.this,"数据获取失败");
                    }
                });

    }

    private List<GankData.Gank> addAllResults(GankData.ResultsBean results) {
        if (results.androidList != null) mGankList.addAll(results.androidList);
        if (results.iOSList != null) mGankList.addAll(results.iOSList);
        if (results.appList != null) mGankList.addAll(results.appList);
        if (results.拓展资源List != null) mGankList.addAll(results.拓展资源List);
        if (results.瞎推荐List != null) mGankList.addAll(results.瞎推荐List);
        if (results.休息视频List != null) mGankList.addAll(0, results.休息视频List);
        return mGankList;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
