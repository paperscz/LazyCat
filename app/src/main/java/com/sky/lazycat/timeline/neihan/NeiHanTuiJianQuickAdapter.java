package com.sky.lazycat.timeline.neihan;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.sky.lazycat.R;
import com.sky.lazycat.data.neihanduanzi.NeiHanDuanZi;
import com.sky.lazycat.ui.PhotoViewActivity;
import com.sky.lazycat.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuetu-develop on 2017/10/9.
 */

public class NeiHanTuiJianQuickAdapter extends BaseQuickAdapter<NeiHanDuanZi.DuanziX.Duanzi,BaseViewHolder>{

    public NeiHanTuiJianQuickAdapter(final List<NeiHanDuanZi.DuanziX.Duanzi> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<NeiHanDuanZi.DuanziX.Duanzi>() {
            @Override
            protected int getItemType(NeiHanDuanZi.DuanziX.Duanzi duanzi) {
                int type = 0;
                if(null != duanzi){
                    type = duanzi.getGroup().getType();
                    if(type == 3){
                        if(null == duanzi.getGroup().getLarge_image()){
                            type = 4;
                        }
                    }
                }
                return type;
            }
        });
        //2://视频 3://图片 4://段子
        getMultiTypeDelegate()
                .registerItemType(1, R.layout.item_neihan_text)
                .registerItemType(2, R.layout.item_neihan_text)
                .registerItemType(3, R.layout.item_neihan_text)
                .registerItemType(4, R.layout.item_neihan_text);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, NeiHanDuanZi.DuanziX.Duanzi dataBean) {
        // 加载头像
        Glide.with(mContext)
                .load(dataBean.getGroup().getUser().getAvatar_url())
                .asBitmap()
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into((ImageView)viewHolder.getView(R.id.iv_head));

        // 加载昵称以及内容
        if(!TextUtils.isEmpty(dataBean.getGroup().getUser().getName())){
            viewHolder.setText(R.id.tv_username,dataBean.getGroup().getUser().getName());
        }
        if(!TextUtils.isEmpty(dataBean.getGroup().getContent())){
            viewHolder.getView(R.id.tv_neihan_content).setVisibility(View.VISIBLE);
            viewHolder.setText(R.id.tv_neihan_content, dataBean.getGroup().getContent());
        } else {
            viewHolder.getView(R.id.tv_neihan_content).setVisibility(View.GONE);
        }
        // 设置头像和内容图可以点击
        viewHolder.addOnClickListener(R.id.iv_head)
                .addOnClickListener(R.id.iv_neihan_img);

        switch (viewHolder.getItemViewType()) {
            case 1: // 段子
                viewHolder.getView(R.id.iv_neihan_img).setVisibility(View.GONE);
                break;
            case 2: // 视频
                viewHolder.setText(R.id.tv_neihan_content, "视频显示");
                break;
            case 3: // 图片
                String imgUrl = dataBean.getGroup().getLarge_image().getUrl_list().get(0).getUrl();
                if(!TextUtils.isEmpty(imgUrl)){
                    Glide.with(mContext)
                            .load(imgUrl)
                            .asBitmap()
                            .placeholder(R.drawable.placeholder)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into((ImageView) viewHolder.getView(R.id.iv_neihan_img));
//                    viewHolder.getView(R.id.iv_isgif).setVisibility(dataBean.getGroup().getIs_gif()==1?View.VISIBLE:View.INVISIBLE);
                }
                break;
            case 4: // 图片和段子
//                String imgUrl2 = dataBean.getGroup().getLarge_image().getUrl_list().get(0).getUrl();
//                if(!TextUtils.isEmpty(imgUrl2)){
//                    Glide.with(mContext)
//                            .load(imgUrl2)
//                            .asBitmap()
//                            .placeholder(R.drawable.placeholder)
//                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                            .into((ImageView) viewHolder.getView(R.id.iv_neihan_img));
////                    viewHolder.getView(R.id.iv_isgif).setVisibility(dataBean.getGroup().getIs_gif()==1?View.VISIBLE:View.INVISIBLE);
//                }
                break;
            default:

                break;
        }
    }

}
