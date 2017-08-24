package com.sky.lazycat.ui;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sky.lazycat.R;
import com.sky.lazycat.data.meizhi.GankData;
import com.sky.lazycat.util.StringStyles;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/21.
 */

public class GankListQuickAdapter extends BaseQuickAdapter<GankData.Gank,BaseViewHolder>{

    private List<GankData.Gank> mGankList;

    public GankListQuickAdapter(List<GankData.Gank> listGank){
        super(R.layout.item_gank,listGank);
        mGankList = listGank;
    }

    @Override
    protected void convert(BaseViewHolder holder, GankData.Gank gank) {
        if(holder.getAdapterPosition() == 0){
            showCategory(holder);
        } else {
            boolean theCategoryOfLastEqualsToThis = mGankList.get(
                    holder.getAdapterPosition() - 1).type.equals(mGankList.get(holder.getAdapterPosition()).type);
            if (!theCategoryOfLastEqualsToThis) {
                showCategory(holder);
            }else {
                hideCategory(holder);
            }
        }
        holder.setText(R.id.category,gank.type);
        SpannableStringBuilder builder = new SpannableStringBuilder(gank.desc).append(
                StringStyles.format(holder.getView(R.id.title).getContext(), " (via. " +
                        gank.who +
                        ")", R.style.ViaTextAppearance));
        CharSequence gankText = builder.subSequence(0, builder.length());
        holder.setText(R.id.title,gankText);

    }

    private void showCategory(BaseViewHolder holder) {
        if (!isVisibleOf(holder.getView(R.id.category))) holder.getView(R.id.category).setVisibility(View.VISIBLE);
    }

    private void hideCategory(BaseViewHolder holder) {
        if (isVisibleOf(holder.getView(R.id.category))) holder.getView(R.id.category).setVisibility(View.GONE);
    }
    /**
     * view.isShown() is a kidding...
     */
    private boolean isVisibleOf(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

}
