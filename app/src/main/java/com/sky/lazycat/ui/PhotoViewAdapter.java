package com.sky.lazycat.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.sky.lazycat.util.ToastUtils;
import com.sky.lazycat.widget.BottomDialogFragment;

import java.util.List;

/**
 * Created by yuetu-develop on 2017/8/16.
 */

public class PhotoViewAdapter extends PagerAdapter{

    public static final String TAG = PhotoViewAdapter.class.getSimpleName();

    private List<String> imageUrls;
    private AppCompatActivity activity;

    public PhotoViewAdapter(List<String> imageUrls, AppCompatActivity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = imageUrls.get(position);
        PhotoView photoView = new PhotoView(activity);
        Glide.with(activity)
                .load(url)
                .into(photoView);
        container.addView(photoView);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });

        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putStringArray(BottomDialogFragment.TAG_DIALOG_ITEM,new String[]{PhotoViewActivity.TEXT_SAVE,PhotoViewActivity.TEXT_SHARE});
                bundle.putBoolean(BottomDialogFragment.TAG_DIALOG_TITLE,true);
                BottomDialogFragment.newInstance(bundle).show(activity.getSupportFragmentManager(),"BottomDialogFragment");
                return true;
            }
        });
        return photoView;
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
