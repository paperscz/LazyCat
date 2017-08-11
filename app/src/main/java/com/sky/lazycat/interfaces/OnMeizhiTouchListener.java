package com.sky.lazycat.interfaces;

import android.view.View;

import com.sky.lazycat.data.Meizhi.MeizhiData;

/**
 * Created by yuetu-develop on 2017/8/11.
 */

public interface OnMeizhiTouchListener {
    void onTouch(View v, View meizhiView, View card, MeizhiData.MeizhiBean meizhi);
   // void onTouch(View v, View meizhiView, View card);
}
