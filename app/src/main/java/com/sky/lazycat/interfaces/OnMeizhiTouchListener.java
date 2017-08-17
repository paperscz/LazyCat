package com.sky.lazycat.interfaces;

import android.view.View;

import com.sky.lazycat.data.meizhi.MeizhiData;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/11.
 */

public interface OnMeizhiTouchListener {
    void onTouch(View v, View meizhiView, View card, List<MeizhiData.MeizhiBean> meizhi,MeizhiData.MeizhiBean meizhiBean);
   // void onTouch(View v, View meizhiView, View card);
}
