package com.sky.lazycat.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuetu-develop on 2017/9/20.
 */

public abstract class BaseDialogFragment extends DialogFragment {

    private Unbinder mUnbinder;
    protected View fragmentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置无标题
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            // 用来设置Dialog的大小，宽度为屏幕的0.80
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.80), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == fragmentView) {
            fragmentView = initFragmentView(inflater, container, savedInstanceState);
        }
        if (null != fragmentView) {
            ViewGroup parent = (ViewGroup) fragmentView.getParent();
            if (null != parent)
                parent.removeAllViews();
        }else {
            fragmentView = super.onCreateView(inflater, container, savedInstanceState);
        }
        if (null != fragmentView)mUnbinder = ButterKnife.bind(this,fragmentView);
        setViews();
        return fragmentView;
    }

    public abstract View initFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void setViews();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mUnbinder)mUnbinder.unbind();
    }

}
