package com.sky.lazycat.retrofit;

import com.sky.lazycat.data.gankvideo.GankVideoData;
import com.sky.lazycat.data.meizhi.GankData;
import com.sky.lazycat.data.meizhi.MeizhiData;
import com.sky.lazycat.data.neihanduanzi.NeiHanDuanZi;
import com.sky.lazycat.data.zhihu.Zhihu;
import com.sky.lazycat.data.zhihu.ZhihuNew;
import com.sky.lazycat.data.zhihu.ZhihuDailyContent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yuetu-develop on 2017/7/7.
 */

public interface RetrofitService {
    // 妹纸获取数量
    public static final int meizhiSize = 10;
    public static final int duanziSize = 10;
    // 内涵段子
    String NEIHAN_DATA_BASE = "http://iu.snssdk.com/neihan/stream/mix/v1/";
    // gankAPI
    String MEIZHI_DATA_BASE = "http://gank.io/api/";
    // 豆瓣电影
    String API_DOUBAN_MOVIE = "https://api.douban.com/";

    // 知乎日报url
    public static final String API_VERSION = "4";
    public static final String ZHIHU_DATA_BASE = "http://news-at.zhihu.com/api/4/news/";
    public static final String ZHIHU_SHARE_URL = "http://daily.zhihu.com/story/";
    public static final String ZHIHULATEST_DATA_BASE = "http://news-at.zhihu.com/api/4/";
    //获取启动页面图片
    String URL_GET_START_IMAGE = "start-image/1080*1776";

    public static final int TYPE_NEIHAN_TUIJIAN = -101;
    public static final int TYPE_NEIHAN_DUANZI = -102;
    public static final int TYPE_NEIHAN_IMAGE = -103;
    public static final int TYPE_NEIHAN_VIDEO = -104;

    interface NeiHanService{
        // 没有数据填 . 或者 /
        @GET(".")
        Call<NeiHanDuanZi> getNeiHanTuiJianList();
        // 获取内涵段子文字 content_type=-102
        @GET("?content_type="+TYPE_NEIHAN_DUANZI+"&count="+duanziSize)
        Call<NeiHanDuanZi> getNeiHanList();
        // 获取段子视频
        @GET("?content_type="+TYPE_NEIHAN_VIDEO+"&count="+duanziSize)
        Call<NeiHanDuanZi> getNeiHanVideoList();
    }

    interface MeizhiService{
        @GET("data/福利/" + meizhiSize + "/{page}")
        Call<MeizhiData> getMeizhiData(@Path("page") int page);
    }

    interface GankVideoService{

        @GET("data/休息视频/" + meizhiSize + "/{page}")
        Call<GankVideoData> getGankVideoData(@Path("page") int page);
        @GET("day/{date}")
        Call<GankData> getGankData(@Path("date") String date);
    }


    interface  ZhihuDaliyService{

        //获取最新日报新闻列表
        @GET("latest")
        Call<Zhihu> getZhihuLatestNews();

        @GET("before/{date}")
        Call<Zhihu> getZhihuNews(@Path("date") String date);

        //获取新闻,根据id获取新闻
        @GET("news/{newsId}")
        Call<ZhihuNew> getNews(@Path("newsId") long newsId);

        @GET("{id}")
        Call<ZhihuDailyContent> getZhihuContent(@Path("id") int id);
    }

}
