package com.sky.lazycat.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sky.lazycat.R;
import com.sky.lazycat.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuetu-develop on 2017/9/19.
 */

public class NeiHanDialogFragment extends BaseDialogFragment {

    @BindView(R.id.iv_head) ImageView headImg;
    @BindView(R.id.tv_username) TextView userName;
    @BindView(R.id.tv_neihan_content) TextView content;
    private String mHeadUrl;
    private String mUserName;
    private String mContent;

    public static NeiHanDialogFragment newInstance(String headUrl, String userName, String content){
        NeiHanDialogFragment neiHanDialogFragment = new NeiHanDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("headUrl",headUrl);
        bundle.putString("userName",userName);
        bundle.putString("content",content);
        neiHanDialogFragment.setArguments(bundle);
        return neiHanDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mHeadUrl = bundle.getString("headUrl");
        mUserName = bundle.getString("userName");
        mContent = bundle.getString("content");
    }

    @Override
    public View initFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_duanzi, container);
        return view;
    }

    @Override
    public void setViews() {
        Glide.with(getActivity()).load(mHeadUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(headImg);
        userName.setText(mUserName);
        content.setText(mContent);
    }

}
