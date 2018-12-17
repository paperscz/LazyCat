package com.sky.lazycat.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.lazycat.R;
import com.sky.lazycat.app.BaseApplication;
import com.sky.lazycat.util.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 可使用 Bottom Sheets 替换
 */

public class BottomDialogFragment extends DialogFragment {

    @BindView(R.id.ll_bottom_dialog) LinearLayout dialogLayout;
    @BindView(R.id.tv_dialog_title) TextView dialogTitle;
    @BindView(R.id.view_dialog_line) View dialogLine;
    @BindView(R.id.tv_dialog_cancle) TextView dialogCancle;
    public static String TAG_DIALOG_TITLE = "noTitle";
    public static String TAG_DIALOG_ITEM = "item";
    private static final int DIALOG_PADDING = 10;
    private static final int DIALOG_TEXTSIZE = 18;
    public static BottomDialogFragment INSTANCE_BDF = null;
    private Unbinder mUnbinder;

    public static BottomDialogFragment newInstance(Bundle bundle) {
//        Bundle args = new Bundle();
        if(null == INSTANCE_BDF){
            INSTANCE_BDF = new BottomDialogFragment();
        }
        INSTANCE_BDF.setArguments(bundle);
        return INSTANCE_BDF;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.dialog_fragment_meizi);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

//        dialog.getWindow().setLayout((int) (dm.widthPixels * 0.80), ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度自适应
        dialog.getWindow().setAttributes(lp);

        mUnbinder = ButterKnife.bind(this, dialog); // Dialog即View
//        initClickTypes();
        setDialog();
        return dialog;
    }

    private void setDialog() {

        Bundle bundle = getArguments();
//        bundle.getStringArrayList("list");
        boolean noTitle = bundle.getBoolean(TAG_DIALOG_TITLE);
        dialogTitle.setVisibility(noTitle ? View.GONE : View.VISIBLE);
        dialogLine.setVisibility(noTitle ? View.GONE : View.VISIBLE);
        String[] listOption = bundle.getStringArray(TAG_DIALOG_ITEM);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        if(null != listOption){
            for (int i = 0; i < listOption.length; i++){
                final TextView optionText = new TextView(getContext());
                int padding = UIUtils.dip2px(DIALOG_PADDING);
                optionText.setPadding(padding, padding, padding, padding);
                optionText.setText(listOption[i]);
                optionText.setTextSize(DIALOG_TEXTSIZE);
                optionText.setGravity(Gravity.CENTER);
                optionText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OptionClickLisenter lisenter = (OptionClickLisenter) getActivity();
                        lisenter.onOptionClick(optionText.getText().toString());
                        INSTANCE_BDF.dismiss();
                    }
                });
                dialogLayout.addView(optionText);
                if (i != listOption.length - 1) {
                    View divider = new View(BaseApplication.getContext());
                    divider.setBackgroundResource(R.color.colordivider);
                    dialogLayout.addView(divider, params);
                }
                optionText.setBackgroundResource(R.drawable.item_selector);
            }
        }
    }

    @OnClick(R.id.tv_dialog_cancle)
    public void cancle(){
        INSTANCE_BDF.dismiss();
    }

    public interface OptionClickLisenter{
        void onOptionClick(String option);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        INSTANCE_BDF = null;
        if(null != mUnbinder){
            mUnbinder.unbind();
        }
    }

//    public static class Option{
//
//        private OptionClickLisenter optionClickLisenter;
//        private String name;
//
//        public Option() {
//        }
//
//        Option(String title,OptionClickLisenter optionClickLisenter){
//            this.name = title;
//            this.optionClickLisenter = optionClickLisenter;
//        }
//
//        public String getTitle() {
//            return name;
//        }
//
//        public void setTitle(String title) {
//            this.name = title;
//        }
//
//        public void setOptionClickLisenter(OptionClickLisenter optionClickLisenter){
//            this.optionClickLisenter = optionClickLisenter;
//        }
//
//        public OptionClickLisenter getOptionClickLisenter() {
//            return optionClickLisenter;
//        }
//    }

}
