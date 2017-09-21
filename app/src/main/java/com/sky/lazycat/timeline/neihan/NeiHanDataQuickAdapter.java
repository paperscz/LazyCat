package com.sky.lazycat.timeline.neihan;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.lazycat.R;
import com.sky.lazycat.data.neihanduanzi.NeiHanDuanZi;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/7/12.
 */

public class NeiHanDataQuickAdapter extends BaseQuickAdapter<NeiHanDuanZi.DuanziX.Duanzi,BaseViewHolder> {

    public NeiHanDataQuickAdapter(List<NeiHanDuanZi.DuanziX.Duanzi> data){
        super(R.layout.item_neihan_text,data);
    }

    @Override
    protected void convert(final BaseViewHolder viewHolder, NeiHanDuanZi.DuanziX.Duanzi dataBean) {

        final ImageView imageView = (ImageView)viewHolder.getView(R.id.iv_head);
        Glide.with(mContext)
                .load(dataBean.getGroup().getUser().getAvatar_url())
                .asBitmap()
//                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.drawable.placeholder)
                .centerCrop()
                // 圆角处理，必须.asBitmap()
                .into(new BitmapImageViewTarget(imageView){
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(30); //设置圆角弧度
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
        viewHolder.addOnClickListener(R.id.iv_head);
        if(!TextUtils.isEmpty(dataBean.getGroup().getUser().getName())){
            viewHolder.setText(R.id.tv_username,dataBean.getGroup().getUser().getName());
        }
        if(!TextUtils.isEmpty(dataBean.getGroup().getContent())){
            viewHolder.setText(R.id.tv_neihan_content, dataBean.getGroup().getContent());
        }

    }
}
