package com.sky.lazycat.meizhi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.lazycat.R;
import com.sky.lazycat.data.Meizhi.MeizhiData;
import com.sky.lazycat.widget.RatioImageView;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/10.
 */

public class MeizhiQuickAdapter extends BaseQuickAdapter<MeizhiData.MeizhiBean,MeizhiQuickAdapter.MeiZhiViewHolder> {

    public MeizhiQuickAdapter(List<MeizhiData.MeizhiBean> data){
        super(R.layout.item_meizhi,data);
    }

    @Override
    protected void convert(MeiZhiViewHolder viewHolder, MeizhiData.MeizhiBean dataBean) {
        int limit = 48;
        String text = dataBean.desc.length() > limit ? dataBean.desc.substring(0, limit) +
                "..." : dataBean.desc;
        viewHolder.setText(R.id.tv_meizhitext,text);
        Glide.with(mContext)
                .load(dataBean.getUrl())
                .centerCrop()
                .into(viewHolder.iv_meizhi)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {

                    }
                });

    }
    // 不为public 某些机型报错ClassCastException
    public class MeiZhiViewHolder extends BaseViewHolder{

        public RatioImageView iv_meizhi;

        View card;
        MeizhiData.MeizhiBean meizhiBean;

        public MeiZhiViewHolder(View view) {
            super(view);
            card = view;
            iv_meizhi = view.findViewById(R.id.iv_meizhi);
            iv_meizhi.setOriginalSize(50,50);
        }
    }
}
