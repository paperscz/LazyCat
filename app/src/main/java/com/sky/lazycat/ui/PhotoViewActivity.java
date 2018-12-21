package com.sky.lazycat.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;

import com.sky.lazycat.R;
import com.sky.lazycat.util.SDFileHelper;
import com.sky.lazycat.util.ToastUtils;
import com.sky.lazycat.widget.BottomDialogFragment;
import com.sky.lazycat.widget.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuetu-develop on 2017/8/16.
 */
public class PhotoViewActivity extends AppCompatActivity implements BottomDialogFragment.OptionClickLisenter{

    @BindView(R.id.pvp_viewpager) PhotoViewPager mPhotoViewPager;
    @BindView(R.id.tv_number) AppCompatTextView tv_number;
    private PhotoViewAdapter mPhotoViewAdapter;
    private List<String> mUrls;
    private int currentPosition;
    private Unbinder mUnbinder;
    public static String TEXT_SAVE = "保  存";
    public static String TEXT_SHARE = "分  享";
    private boolean isShare = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏，已设置全屏stype
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photo_view);
        mUnbinder = ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("currentPosition",0);
        mUrls = intent.getStringArrayListExtra("mUrls");
        mPhotoViewAdapter = new PhotoViewAdapter(mUrls,this);
        mPhotoViewPager.setAdapter(mPhotoViewAdapter);
        mPhotoViewPager.setCurrentItem(currentPosition);
        tv_number.setText(currentPosition+1 + "/" +mUrls.size());
        if(mUrls.size() == 1){
            tv_number.setVisibility(View.GONE);
        }
        mPhotoViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                tv_number.setText(currentPosition + 1 + "/" + mUrls.size());
            }
        });
    }

    public static void newIntent(Context context, View view, List<String> urls, int index) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra("currentPosition",index);
        intent.putStringArrayListExtra("mUrls", (ArrayList<String>) urls);
        if(null != view){
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(
                    view,(int)view.getWidth()/2, (int)view.getHeight()/2, //拉伸开始的坐标
                    0, 0);//拉伸开始的区域大小，这里用（0，0）表示从无到全屏
            ActivityCompat.startActivity(context,intent,optionsCompat.toBundle());
        } else {
            context.startActivity(intent);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void onOptionClick(String option) {
//        ToastUtils.showShort(this,option);
        if(!TextUtils.isEmpty(option) && option.equals(TEXT_SAVE)){
            isShare = false;
        }else if(!TextUtils.isEmpty(option) && option.equals(TEXT_SHARE)){
            isShare = true;
        }
    }

    void showSavePermission(){
        SDFileHelper helper = new SDFileHelper(this);
        helper.savePicture("Cat_"+System.currentTimeMillis(),mUrls.get(currentPosition),isShare);
    }
     // 显示自定义权限申请
//    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//    void showRationaleForRecord(final PermissionRequest request) {
//        new AlertDialog.Builder(this)
//                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.proceed();
//                    }
//                })
//                .setNegativeButton("不给", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.cancel();
//                    }
//                })
//                .setCancelable(false)
//                .setMessage("保存图片需要申请权限")
//                .show();
//    }

    // 用户拒绝权限
    void showRecordDenied(){
        ToastUtils.showShort(this,"用户拒绝，无法保存图片");
    }
    // 用户勾选不再提示后再次申请
    void onRecordNeverAskAgain() {
        new AlertDialog.Builder(this)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("您已经禁止了保存权限,请到权限管理页面打开权限")
                .show();
    }

    // 权限申请结果回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
