package com.sky.lazycat.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sky.lazycat.R;
import com.sky.lazycat.data.meizhi.GankData;
import com.sky.lazycat.util.StringStyles;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by drakeet on 8/11/15.
 */
public class GankListAdapter extends RecyclerView.Adapter<GankListAdapter.ViewHolder> {

    private List<GankData.Gank> mGankList;

    public GankListAdapter(List<GankData.Gank> gankList) {
        mGankList = gankList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.item_gank, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GankData.Gank gank = mGankList.get(position);
        if (position == 0) {
            showCategory(holder);
        }
        else {
            boolean theCategoryOfLastEqualsToThis = mGankList.get(
                    position - 1).type.equals(mGankList.get(position).type);
            if (!theCategoryOfLastEqualsToThis) {
                showCategory(holder);
            }
            else {
                hideCategory(holder);
            }
        }
        holder.category.setText(gank.type);
        SpannableStringBuilder builder = new SpannableStringBuilder(gank.desc).append(
                StringStyles.format(holder.gank.getContext(), " (via. " +
                        gank.who +
                        ")", R.style.ViaTextAppearance));
        CharSequence gankText = builder.subSequence(0, builder.length());

        holder.gank.setText(gankText);
    }


    @Override
    public int getItemCount() {
        return mGankList.size();
    }


    private void showCategory(ViewHolder holder) {
        if (!isVisibleOf(holder.category)) holder.category.setVisibility(View.VISIBLE);
    }


    private void hideCategory(ViewHolder holder) {
        if (isVisibleOf(holder.category)) holder.category.setVisibility(View.GONE);
    }


    /**
     * view.isShown() is a kidding...
     */
    private boolean isVisibleOf(View view) {
        return view.getVisibility() == View.VISIBLE;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category)
        TextView category;
        @BindView(R.id.title)
        TextView gank;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnClick(R.id.gank_layout) void onGank(View v) {
            GankData.Gank gank = mGankList.get(getLayoutPosition());
            Intent intent = WebActivity.newIntent(v.getContext(), gank.url, gank.desc);
            v.getContext().startActivity(intent);
        }
    }
}
