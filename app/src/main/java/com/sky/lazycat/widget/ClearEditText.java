package com.sky.lazycat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AutoCompleteTextView;

import com.sky.lazycat.R;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/17 0017.
 **/
public class ClearEditText extends AutoCompleteTextView{

    private Drawable mImgClear;
    private OnTextChangedListener mOnTextChangedListener = null;

    public ClearEditText(Context context) {
        this(context,null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mImgClear = context.getResources().getDrawable(R.drawable.ic_text_clear);
        init();
    }

    private void init() {
        addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            //在内容改变完之后
            @Override
            public void afterTextChanged(Editable editable) {
                setDrawable();
                if(null != mOnTextChangedListener){
                    mOnTextChangedListener.afterTextChanged();
                }
            }
        });
    }

    //绘制删除图片
    private void setDrawable(){
        if (length() < 1){
            // 分别设置左、上、右、下的图片
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, mImgClear, null);
        }
    }

    //当触摸范围在右侧时，触发删除方法，隐藏叉叉
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mImgClear != null && event.getAction() == MotionEvent.ACTION_UP){
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Rect rect = new Rect();
            // 获取相对屏幕的可视区域
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 100;
            // 如果触摸的点在可视区域右侧 100 以内，视为点击了清除图标
            if (rect.contains(eventX, eventY)){
                getText().clear();
            }
        }
        return super.onTouchEvent(event);
    }

    public void setmOnTextChangedListener(OnTextChangedListener mOnTextChangedListener) {
        this.mOnTextChangedListener = mOnTextChangedListener;
    }

    public interface OnTextChangedListener{
        void afterTextChanged();
    }

}
