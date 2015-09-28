package com.flyco.dialog.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.internal.BaseAlertDialog;

/**
 * Dialog like Material Design Dialog
 */
public class MaterialDialog extends BaseAlertDialog<MaterialDialog> {

    public MaterialDialog(Context context) {
        super(context);

        /** default value*/
        titleTextColor = Color.parseColor("#DE000000");
        titleTextSize_SP = 22f;
        contentTextColor = Color.parseColor("#8a000000");
        contentTextSize_SP = 16f;
        leftBtnTextColor = Color.parseColor("#383838");
        rightBtnTextColor = Color.parseColor("#468ED0");
        middleBtnTextColor = Color.parseColor("#00796B");
        /** default value*/
    }

    @Override
    public View onCreateView() {

        /** title */
        tv_title.setGravity(Gravity.CENTER_VERTICAL);
        tv_title.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(0));
        tv_title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_container.addView(tv_title);

        /** content */
        tv_content.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(20));
        tv_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_container.addView(tv_content);

        /**btns*/
        ll_btns.setGravity(Gravity.RIGHT);
        ll_btns.addView(tv_btn_left);
        ll_btns.addView(tv_btn_middle);
        ll_btns.addView(tv_btn_right);

        tv_btn_left.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
        tv_btn_right.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
        tv_btn_middle.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
        ll_btns.setPadding(dp2px(20), dp2px(0), dp2px(10), dp2px(10));

        ll_container.addView(ll_btns);

        return ll_container;
    }

    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();
        /**set background color and corner radius */
        float radius = dp2px(cornerRadius_DP);
        ll_container.setBackgroundDrawable(CornerUtils.cornerDrawable(bgColor, radius));
        tv_btn_left.setBackgroundDrawable(CornerUtils.btnSelector(radius, bgColor, btnPressColor, -2));
        tv_btn_right.setBackgroundDrawable(CornerUtils.btnSelector(radius, bgColor, btnPressColor, -2));
        tv_btn_middle.setBackgroundDrawable(CornerUtils.btnSelector(radius, bgColor, btnPressColor, -2));
    }
}
