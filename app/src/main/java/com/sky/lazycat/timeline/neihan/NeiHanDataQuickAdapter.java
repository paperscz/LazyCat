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

//        setMultiTypeDelegate(new MultiTypeDelegate<NeiHanAll.DataBean>() {
//            @Override
//            protected int getItemType(NeiHanAll.DataBean dataBean) {
//                //根据你的实体类来判断布局类型
//                if(dataBean.getGroup().getCategory_name().equals(NeiHanAll.DataBean.GAOXIAO_VIDEO)){
//                    return NeiHanAll.DataBean.VIDEO;
//                }else if(dataBean.getGroup().getCategory_name().equals(NeiHanAll.DataBean.GAOXIAO_PIC)){
//                    return NeiHanAll.DataBean.IMG;
//                }else if(dataBean.getGroup().getCategory_name().equals(NeiHanAll.DataBean.BAOXIAO_GIF)){
//                    return NeiHanAll.DataBean.GIF;
//                }
//                return NeiHanAll.DataBean.TEXT;
//            }
//        });
//        getMultiTypeDelegate()
//                .registerItemType(NeiHanAll.DataBean.TEXT, R.layout.item_neihan_text)
//                .registerItemType(NeiHanAll.DataBean.IMG, R.layout.item_neihan_img)
//                .registerItemType(NeiHanAll.DataBean.GIF, R.layout.item_neihan_img)
//                .registerItemType(NeiHanAll.DataBean.VIDEO, R.layout.item_neihan_img);
    }

    @Override
    protected void convert(final BaseViewHolder viewHolder, NeiHanDuanZi.DuanziX.Duanzi dataBean) {

        final ImageView imageView = (ImageView)viewHolder.getView(R.id.iv_head);

        Glide.with(mContext)
                .load(dataBean.getGroup().getUser().getAvatar_url())
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .error(R.drawable.placeholder)
                .centerCrop()
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

        if(!TextUtils.isEmpty(dataBean.getGroup().getUser().getName())){
            viewHolder.setText(R.id.tv_username,dataBean.getGroup().getUser().getName());
        }
        if(!TextUtils.isEmpty(dataBean.getGroup().getContent())){
            viewHolder.setText(R.id.tv_neihan_content, dataBean.getGroup().getContent());
        }

//        viewHolder.setText(R.id.tv_likecount,dataBean.getGroup().getDigg_count()+"");
//        viewHolder.setText(R.id.tv_dislikecount,dataBean.getGroup().getRepin_count()+"");
//        viewHolder.setText(R.id.tv_comment_count,dataBean.getGroup().getComment_count()+"");

//        switch (viewHolder.getItemViewType()) {
//            case NeiHanAll.DataBean.TEXT:
//
//                break;
//            case NeiHanAll.DataBean.IMG:
//                viewHolder.setText(R.id.tv_img_text, dataBean.getGroup().getContent());
//
//                Glide.with(mContext)
//                        .load(dataBean.getGroup().getLarge_image().getUrl_list().get(0).getUrl())
//                        .centerCrop()
//                       //.override(dataBean.getGroup().getLarge_image().getWidth(),dataBean.getGroup().getLarge_image().getHeight())
//                        .placeholder(R.drawable.placeholder)
//                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                        .error(R.drawable.placeholder)
//                        .into((ImageView)viewHolder.getView(R.id.iv_neihan_img));
//
//            case NeiHanAll.DataBean.GIF:
//                String url = "http://pb3.pstatp.com/large/22e3000241b3c8b78fbf";
//                if(TextUtils.isEmpty(dataBean.getGroup().getLarge_image().getUrl_list().get(0).getUrl())){
//                    url = dataBean.getGroup().getLarge_image().getUrl_list().get(1).getUrl();
//                }else {
//                    url = dataBean.getGroup().getLarge_image().getUrl_list().get(2).getUrl();
//                }
//
//                Glide.with(mContext)
//                        .load(url)
//                        .asBitmap()
//                        .placeholder(R.drawable.placeholder)
//                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                        .error(R.drawable.placeholder)
//                        .into((ImageView)viewHolder.getView(R.id.iv_neihan_img));
//                break;
//            case NeiHanAll.DataBean.VIDEO:
//
//                break;
//        }

    }


}
