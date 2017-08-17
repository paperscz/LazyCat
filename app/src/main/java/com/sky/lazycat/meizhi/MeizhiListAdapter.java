package com.sky.lazycat.meizhi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.sky.lazycat.R;
import com.sky.lazycat.data.meizhi.MeizhiData;
import com.sky.lazycat.interfaces.OnMeizhiTouchListener;
import com.sky.lazycat.widget.RatioImageView;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/11.
 */

public class MeizhiListAdapter  extends RecyclerView.Adapter<MeizhiListAdapter.ViewHolder>{

    private OnMeizhiTouchListener mOnMeizhiTouchListener;
    private List<MeizhiData.MeizhiBean> mList;
    private Context mContext;

    public MeizhiListAdapter(Context context, List<MeizhiData.MeizhiBean> meizhiList) {
        mList = meizhiList;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meizhi, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        MeizhiData.MeizhiBean meizhi = mList.get(position);
        int limit = 48;
        String text = meizhi.desc.length() > limit ? meizhi.desc.substring(0, limit) +
                "..." : meizhi.desc;
        viewHolder.meizhiBean = meizhi;
        viewHolder.tv_title.setText(text);
        viewHolder.card.setTag(meizhi.desc);
        Glide.with(mContext)
                .load(meizhi.getUrl())
                .centerCrop()
                .into(viewHolder.iv_meizhi)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        if (!viewHolder.card.isShown()) {
                            viewHolder.card.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateData(@NonNull List<MeizhiData.MeizhiBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
     //   notifyItemRemoved(list.size());
    }

    public void addData(@NonNull List<MeizhiData.MeizhiBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnMeizhiTouchListener(OnMeizhiTouchListener onMeizhiTouchListener) {
        this.mOnMeizhiTouchListener = onMeizhiTouchListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RatioImageView iv_meizhi;
        TextView tv_title;
        View card;
        MeizhiData.MeizhiBean meizhiBean;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView;
            iv_meizhi = itemView.findViewById(R.id.iv_meizhi);
            tv_title = itemView.findViewById(R.id.tv_meizhitext);
            iv_meizhi.setOnClickListener(this);
            card.setOnClickListener(this);
            iv_meizhi.setOriginalSize(50, 50);
        }


        @Override public void onClick(View v) {
            mOnMeizhiTouchListener.onTouch(v, iv_meizhi, card,mList, meizhiBean);
        }
    }

}
