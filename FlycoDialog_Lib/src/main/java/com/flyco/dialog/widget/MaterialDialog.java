package com.flyco.dialog.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnLeftClickL;
import com.flyco.dialog.listener.OnBtnRightClickL;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BaseDialog;

/**
 * Dialog like Material Design Dialog
 */
public class MaterialDialog extends BaseDialog {
    /**
     * container
     */
    private LinearLayout ll_container;
    /***
     * title
     */
    private TextView tv_title;
    /**
     * content
     */
    private TextView tv_content;
    /**
     * btn container
     */
    private LinearLayout ll_btns;
    /**
     * left btn
     */
    private TextView tv_btn_left;
    /**
     * right btn
     */
    private TextView tv_btn_right;
    /**
     * title content(标题)
     */
    private String title = "温馨提示";
    /**
     * title textcolor(标题颜色)
     */
    private int titleTextColor = Color.parseColor("#DE000000");
    /**
     * title textsize(标题字体大小,单位sp)
     */
    private float titleTextSize_SP = 22f;
    /**
     * enable title show(是否显示标题)
     */
    private boolean isTitleShow = true;
    /**
     * content text
     */
    private String content;
    /**
     * show gravity of content(正文内容显示位置)
     */
    private int contentGravity = Gravity.CENTER_VERTICAL;
    /**
     * content textcolor(正文字体颜色)
     */
    private int contentTextColor = Color.parseColor("#8a000000");
    /**
     * content textsize(正文字体大小)
     */
    private float contentTextSize_SP = 16f;
    /**
     * btn textcolor(按钮字体颜色)
     */
    private int leftBtnTextColor = Color.parseColor("#383838");
    private int rightBtnTextColor = Color.parseColor("#468ED0");
    /**
     * btn textsize(按钮字体大小)
     */
    private float leftBtnTextSize_SP = 15f;
    private float rightBtnTextSize_SP = 15f;
    /**
     * btn press color(按钮点击颜色)
     */
    private int btnPressColor = Color.parseColor("#E3E3E3");// #85D3EF,#ffcccccc,#E3E3E3
    /**
     * left btn text(左按钮内容)
     */
    private String btnLeftText = "取消";
    /**
     * right btn text(右按钮内容)
     */
    private String btnRightText = "确定";
    /**
     * corner radius,dp(圆角程度,单位dp)
     */
    private float cornerRadius_DP = 3;
    /**
     * background color(背景颜色)
     */
    private int bgColor = Color.parseColor("#ffffff");
    /**
     * left btn click listener(左按钮接口)
     */
    private OnBtnLeftClickL onBtnLeftClickL;
    /**
     * right btn click listener(右按钮接口)
     */
    private OnBtnRightClickL onBtnRightClickL;

    public MaterialDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        widthScale(0.88f);

        ll_container = new LinearLayout(context);
        ll_container.setOrientation(LinearLayout.VERTICAL);

        /** title */
        tv_title = new TextView(context);
        tv_title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_container.addView(tv_title);

        /** content */
        tv_content = new TextView(context);
        tv_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ll_container.addView(tv_content);

        /** btns */
        ll_btns = new LinearLayout(context);
        ll_btns.setOrientation(LinearLayout.HORIZONTAL);

        ll_btns.setGravity(Gravity.RIGHT);
        tv_btn_left = new TextView(context);
        tv_btn_left.setGravity(Gravity.CENTER);
        tv_btn_left.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));
        ll_btns.addView(tv_btn_left);

        tv_btn_right = new TextView(context);
        tv_btn_right.setGravity(Gravity.CENTER);
        tv_btn_right.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));
        ll_btns.addView(tv_btn_right);

        ll_container.addView(ll_btns);

        return ll_container;
    }

    @Override
    public boolean setUiBeforShow() {
        float radius = dp2px(cornerRadius_DP);

        /** title */
        tv_title.setGravity(Gravity.CENTER_VERTICAL);
        tv_title.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(0));
        tv_title.setVisibility(isTitleShow ? View.VISIBLE : View.GONE);

        tv_title.setText(TextUtils.isEmpty(title) ? "温馨提示" : title);
        tv_title.setTextColor(titleTextColor);
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTextSize_SP);

        /** content */
        tv_content.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(20));
        tv_content.setGravity(contentGravity);
        tv_content.setText(content);
        tv_content.setTextColor(contentTextColor);
        tv_content.setTextSize(TypedValue.COMPLEX_UNIT_SP, contentTextSize_SP);
        tv_content.setLineSpacing(0, 1.3f);

        /** btns */
        ll_btns.setPadding(dp2px(20), dp2px(0), dp2px(10), isTitleShow ? dp2px(10) : dp2px(0));

        tv_btn_left.setText(btnLeftText);
        tv_btn_right.setText(btnRightText);
        tv_btn_left.setTextColor(leftBtnTextColor);
        tv_btn_right.setTextColor(rightBtnTextColor);
        tv_btn_left.setTextSize(TypedValue.COMPLEX_UNIT_SP, leftBtnTextSize_SP);
        tv_btn_right.setTextSize(TypedValue.COMPLEX_UNIT_SP, rightBtnTextSize_SP);

        /**set background color and corner radius */
        ll_container.setBackgroundDrawable(CornerUtils.cornerDrawable(bgColor, radius));
        tv_btn_left.setBackgroundDrawable(CornerUtils.btnSelector(0, bgColor, btnPressColor, 0));
        tv_btn_right.setBackgroundDrawable(CornerUtils.btnSelector(0, bgColor, btnPressColor, 1));

        tv_btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBtnLeftClickL != null) {
                    onBtnLeftClickL.onBtnLeftClick();
                }
            }
        });

        tv_btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBtnRightClickL != null) {
                    onBtnRightClickL.onBtnRightClick();
                }
            }
        });

        return false;
    }

    // --->属性设置

    /**
     * set title text(设置标题内容)
     *
     * @param title
     * @return MaterialDialog
     */
    public MaterialDialog title(String title) {
        this.title = title;
        return this;
    }

    /**
     * set title textcolor(设置标题字体颜色)
     *
     * @param titleTextColor
     * @return MaterialDialog
     */
    public MaterialDialog titleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    /**
     * set title textsize(设置标题字体大小)
     *
     * @param titleTextSize_SP
     * @return MaterialDialog
     */
    public MaterialDialog titleTextSize(float titleTextSize_SP) {
        this.titleTextSize_SP = titleTextSize_SP;
        return this;
    }

    /**
     * enable title show(设置标题是否显示)
     *
     * @param isTitleShow
     * @return MaterialDialog
     */
    public MaterialDialog isTitleShow(boolean isTitleShow) {
        this.isTitleShow = isTitleShow;
        return this;
    }

    /**
     * set content text(设置正文内容)
     *
     * @param content
     * @return MaterialDialog
     */
    public MaterialDialog content(String content) {
        this.content = content;
        return this;
    }

    /**
     * set content gravity(设置正文内容,显示位置)
     *
     * @param contentGravity
     * @return MaterialDialog
     */
    public MaterialDialog contentGravity(int contentGravity) {
        this.contentGravity = contentGravity;
        return this;
    }

    /**
     * set content textcolor(设置正文字体颜色)
     *
     * @param contentTextColor
     * @return MaterialDialog
     */
    public MaterialDialog contentTextColor(int contentTextColor) {
        this.contentTextColor = contentTextColor;
        return this;
    }

    /**
     * set content textsize(设置正文字体大小,单位sp)
     *
     * @param contentTextSize_SP
     * @return MaterialDialog
     */
    public MaterialDialog contentTextSize(float contentTextSize_SP) {
        this.contentTextSize_SP = contentTextSize_SP;
        return this;
    }

    /**
     * set btn text(设置按钮文字内容)
     *
     * @param btnLeftText
     * @param btnRightText
     * @return MaterialDialog
     */
    public MaterialDialog btnText(String btnLeftText, String btnRightText) {
        this.btnLeftText = btnLeftText;
        this.btnRightText = btnRightText;
        return this;
    }

    /**
     * set btn textcolor(设置按钮字体颜色)
     *
     * @param leftBtnTextColor
     * @param rightBtnTextColor
     * @return MaterialDialog
     */
    public MaterialDialog btnTextColor(int leftBtnTextColor, int rightBtnTextColor) {
        this.leftBtnTextColor = leftBtnTextColor;
        this.rightBtnTextColor = rightBtnTextColor;
        return this;
    }

    /**
     * set btn textsize(设置字体大小,单位sp)
     *
     * @param leftBtnTextSize_SP
     * @param rightBtnTextSize_SP
     * @return MaterialDialog
     */
    public MaterialDialog btnTextSize(float leftBtnTextSize_SP, float rightBtnTextSize_SP) {
        this.leftBtnTextSize_SP = leftBtnTextSize_SP;
        this.rightBtnTextSize_SP = rightBtnTextSize_SP;
        return this;
    }

    /**
     * set btn press color(设置按钮点击颜色)
     *
     * @param btnPressColor
     * @return MaterialDialog
     */
    public MaterialDialog btnPressColor(int btnPressColor) {
        this.btnPressColor = btnPressColor;
        return this;
    }

    /**
     * set leftbtn click listener(设置左侧按钮监听事件)
     *
     * @param onBtnLeftClickL
     */
    public void setOnBtnLeftClickL(OnBtnLeftClickL onBtnLeftClickL) {
        this.onBtnLeftClickL = onBtnLeftClickL;
    }

    /**
     * set rightbtn click listener(设置右侧按钮监听事件)
     *
     * @param onBtnRightClickL
     */
    public void setOnBtnRightClickL(OnBtnRightClickL onBtnRightClickL) {
        this.onBtnRightClickL = onBtnRightClickL;
    }

    /**
     * set corner radius (设置圆角程度)
     *
     * @param cornerRadius_DP
     * @return MaterialDialog
     */
    public MaterialDialog cornerRadius(float cornerRadius_DP) {
        this.cornerRadius_DP = cornerRadius_DP;
        return this;
    }

    /**
     * set backgroud color(设置背景色)
     *
     * @param bgColor
     * @return MaterialDialog
     */
    public MaterialDialog bgColor(int bgColor) {
        this.bgColor = bgColor;
        return this;
    }

}
