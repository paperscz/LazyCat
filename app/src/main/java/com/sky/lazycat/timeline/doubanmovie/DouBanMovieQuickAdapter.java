package com.sky.lazycat.timeline.doubanmovie;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.lazycat.R;
import com.sky.lazycat.data.doubanmovie.DouBanMovie;

import java.util.List;

/**
 * Created by yuetu-develop on 2018/4/23.
 */

public class DouBanMovieQuickAdapter extends BaseQuickAdapter<DouBanMovie.SubjectsBean,BaseViewHolder> {

    public DouBanMovieQuickAdapter(@Nullable List<DouBanMovie.SubjectsBean> data) {
        super(R.layout.item_douban_movie, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DouBanMovie.SubjectsBean item) {
        // 电影名称和年份
        helper.setText(R.id.tv_movie_name,item.getTitle()+" ("+item.getYear()+")");
        // 海报
        Glide.with(mContext)
                .load(item.getImages().getMedium())
                .asBitmap()
                .placeholder(R.drawable.placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(((ImageView) helper.getView(R.id.iv_poster)));
        // 评分文字和Bar
        helper.setText(R.id.tv_rating,item.getRating().getAverage() == 0?"暂无":item.getRating().getAverage()+"");
        helper.setRating(R.id.rb_rating,(float) item.getRating().getAverage()/2);
        // 电影类型 因为涉及到遍历，信息过多可提前处理
        StringBuilder stringBuilder = new StringBuilder();
        if(null != item.getGenres() && item.getGenres().size() != 0){
            for(int i = 0;i < item.getGenres().size();i++){
                stringBuilder.append(item.getGenres().get(i)+"/");
            }
            helper.setText(R.id.tv_genres,stringBuilder.substring(0,stringBuilder.length()-1));
        }
        // 看过人数
        helper.setText(R.id.tv_collect_count,"已有 "+item.getCollect_count()+" 人看过");
        helper.addOnClickListener(R.id.rl_item_douban_movie);
        helper.addOnClickListener(R.id.iv_poster);
    }
}
