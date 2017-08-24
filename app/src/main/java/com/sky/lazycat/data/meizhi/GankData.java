package com.sky.lazycat.data.meizhi;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/18.
 */

public class GankData {

    public boolean error;
    public ResultsBean results;
    public List<String> category;

    public static class ResultsBean {
        @SerializedName("Android") public List<Gank> androidList;
        @SerializedName("休息视频") public List<Gank> 休息视频List;
        @SerializedName("iOS") public List<Gank> iOSList;
        @SerializedName("福利") public List<Gank> 妹纸List;
        @SerializedName("拓展资源") public List<Gank> 拓展资源List;
        @SerializedName("瞎推荐") public List<Gank> 瞎推荐List;
        @SerializedName("App") public List<Gank> appList;
    }
    public static class Gank{
        public String url;
        public String type;
        public String desc;
        public String who;
        public boolean used;
        public Date createdAt;
        public Date updatedAt;
        public Date publishedAt;
    }
}
