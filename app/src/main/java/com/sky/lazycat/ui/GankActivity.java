package com.sky.lazycat.ui;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
    public static final String EXTRA_MEIZHI_URL = "meizhi_url";
    @BindView(R.id.rv_ganklist) RecyclerView rv_ganklist;
    @BindView(R.id.iv_gankhead) ImageView iv_gankhead;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout mToolbarLayout;

    private String mVideoUrl;
    private String mGankDate;
    private String mMeiZhiUrl;
    private List<GankData.Gank> mGankList;
    private GankListQuickAdapter mAdapter;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        unbinder = ButterKnife.bind(this);
        setToolBar();
        setRecyclerView();
        parseIntent();
        loadData();
    }

    private void setToolBar() {
        mToolbarLayout.setTitle("");
        mToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        mToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    private void setRecyclerView() {
     //   rv_ganklist.setHasFixedSize(true);
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

    private void parseIntent() {
        mGankDate = getIntent().getStringExtra(EXTRA_GANK_DATE);
        mVideoUrl= getIntent().getStringExtra(EXTRA_VIDEO_URL);
        mMeiZhiUrl = getIntent().getStringExtra(EXTRA_MEIZHI_URL);
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

        Glide.with(this)
                .load(mMeiZhiUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(iv_gankhead);

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

    public static void newIntent(Context context,View imageView,String createAt,String videoUrl,String meizhiUrl){
        Intent intent = new Intent(context,GankActivity.class);
        intent.putExtra(GankActivity.EXTRA_GANK_DATE,createAt);
        intent.putExtra(GankActivity.EXTRA_VIDEO_URL,videoUrl);
        intent.putExtra(GankActivity.EXTRA_MEIZHI_URL,meizhiUrl);
        context.startActivity(intent,ActivityOptions.makeSceneTransitionAnimation((Activity) context,imageView,"meizhi").toBundle());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
