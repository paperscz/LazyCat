package com.sky.lazycat.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/6.
 */

public class NeiHanData {
    // 是否有新内容
    private boolean has_more;
    // 显示几条新内容
    private String tip;

    @SerializedName("data")
    private List<NeiHanGroup> datas;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<NeiHanGroup> getDatas() {
        return datas;
    }

    public void setDatas(List<NeiHanGroup> datas) {
        this.datas = datas;
    }
}
