package com.sky.lazycat.timeline.zhihu;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.lazycat.R;
import com.sky.lazycat.data.zhihu.Zhihu;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/28.
 */

public class ZhihuQuickAdapter extends BaseQuickAdapter<Zhihu.StoriesBean,BaseViewHolder> {

    public ZhihuQuickAdapter(List<Zhihu.StoriesBean> data){
        super(R.layout.item_zhihu,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Zhihu.StoriesBean item) {
        Glide.with(mContext)
                .load(item.getImages().get(0))
                .placeholder(R.drawable.placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into((ImageView) helper.getView(R.id.thumbnail_image));
        // 日报标题
        helper.setText(R.id.question_title,item.getTitle());
        // 日报内容
        helper.setText(R.id.daily_title,item.getTitle());
        // 分享监听
        helper.addOnClickListener(R.id.card_share_overflow);
    }
}
