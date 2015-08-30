package com.flyco.dialog.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BaseDialog;

@SuppressLint("RtlHardcoded")
@SuppressWarnings("deprecation")
public class MaterialTipDialog extends BaseDialog {
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
     * btn
     */
    private TextView tv_btn;
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
    private int btnTextColor = Color.parseColor("#468ED0");
    /**
     * btn textsize(按钮字体大小)
     */
    private float btnTextSize_SP = 15f;
    /**
     * btn press color(按钮点击颜色)
     */
    private int btnColorPress = Color.parseColor("#E3E3E3");// #85D3EF,#ffcccccc,#E3E3E3
    /**
     * btn text(按钮内容)
     */
    private String btnText = "确定";
    /**
     * corner radius,dp(圆角程度,单位dp)
     */
    private float cornerRadius_DP = 3;
    /**
     * background color(背景颜色)
     */
    private int bgColor = Color.parseColor("#ffffff");
    /**
     * btn click listener(按钮接口)
     */
    private OnBtnClickL onBtnClickL;

    public MaterialTipDialog(Context context) {
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

        tv_btn = new TextView(context);
        tv_btn.setGravity(Gravity.CENTER);
        tv_btn.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));
        ll_btns.addView(tv_btn);

        ll_container.addView(ll_btns);

        return ll_container;
    }

    @Override
    public boolean setUiBeforShow() {
        float radius = dp2px(cornerRadius_DP);

        /** title */
        tv_title.setGravity(Gravity.CENTER_VERTICAL);
        tv_title.setPadding(dp2px(20), dp2px(20), dp2px(20),
                dp2px(0));
        tv_title.setVisibility(isTitleShow ? View.VISIBLE : View.GONE);

        tv_title.setText(TextUtils.isEmpty(title) ? "温馨提示" : title);
        tv_title.setTextColor(titleTextColor);
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTextSize_SP);

        /** content */
        tv_content.setPadding(dp2px(20), dp2px(20), dp2px(20),
                dp2px(20));
        tv_content.setGravity(contentGravity);
        tv_content.setText(content);
        tv_content.setTextColor(contentTextColor);
        tv_content.setTextSize(TypedValue.COMPLEX_UNIT_SP, contentTextSize_SP);
        tv_content.setLineSpacing(0, 1.3f);

        /** btns */
        ll_btns.setPadding(dp2px(20), dp2px(0), dp2px(10), isTitleShow ? dp2px(10) : dp2px(0));

        tv_btn.setText(btnText);
        tv_btn.setTextColor(btnTextColor);
        tv_btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, btnTextSize_SP);

        /**set background color and corner radius */
        ll_container.setBackgroundDrawable(CornerUtils.cornerDrawable(bgColor, radius));
        tv_btn.setBackgroundDrawable(CornerUtils.listItemSelector(0, bgColor, btnColorPress, true));

        tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBtnClickL != null) {
                    onBtnClickL.onBtnClick();
                } else {
                    dismiss();
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
     * @return MaterialTipDialog
     */
    public MaterialTipDialog title(String title) {
        this.title = title;
        return this;
    }

    /**
     * set title textcolor(设置标题字体颜色)
     *
     * @param titleTextColor
     * @return MaterialTipDialog
     */
    public MaterialTipDialog titleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    /**
     * set title textsize(设置标题字体大小)
     *
     * @param titleTextSize_SP
     * @return MaterialTipDialog
     */
    public MaterialTipDialog titleTextSize(float titleTextSize_SP) {
        this.titleTextSize_SP = titleTextSize_SP;
        return this;
    }

    /**
     * enable title show(设置标题是否显示)
     *
     * @param isTitleShow
     * @return MaterialTipDialog
     */
    public MaterialTipDialog isTitleShow(boolean isTitleShow) {
        this.isTitleShow = isTitleShow;
        return this;
    }

    /**
     * set content text(设置正文内容)
     *
     * @param content
     * @return MaterialTipDialog
     */
    public MaterialTipDialog content(String content) {
        this.content = content;
        return this;
    }

    /**
     * set content gravity(设置正文内容,显示位置)
     *
     * @param contentGravity
     * @return MaterialTipDialog
     */
    public MaterialTipDialog contentGravity(int contentGravity) {
        this.contentGravity = contentGravity;
        return this;
    }

    /**
     * set content textcolor(设置正文字体颜色)
     *
     * @param contentTextColor
     * @return MaterialTipDialog
     */
    public MaterialTipDialog contentTextColor(int contentTextColor) {
        this.contentTextColor = contentTextColor;
        return this;
    }

    /**
     * set content textsize(设置正文字体大小,单位sp)
     *
     * @param contentTextSize_SP
     * @return MaterialTipDialog
     */
    public MaterialTipDialog contentTextSize(float contentTextSize_SP) {
        this.contentTextSize_SP = contentTextSize_SP;
        return this;
    }

    /**
     * set btn text(设置按钮文字内容)
     *
     * @param btnText
     * @return MaterialTipDialog
     */
    public MaterialTipDialog btnText(String btnText) {
        this.btnText = btnText;
        return this;
    }

    /**
     * set btn textcolor(设置按钮字体颜色)
     *
     * @param btnTextColor
     * @return MaterialTipDialog
     */
    public MaterialTipDialog btnTextColor(int btnTextColor) {
        this.btnTextColor = btnTextColor;
        return this;
    }

    /**
     * set btn textsize(设置字体大小,单位sp)
     *
     * @param btnTextSize_SP
     * @return MaterialTipDialog
     */
    public MaterialTipDialog btnTextSize(float btnTextSize_SP) {
        this.btnTextSize_SP = btnTextSize_SP;
        return this;
    }

    /**
     * set btn press color(设置按钮点击颜色)
     *
     * @param btnColorPress
     * @return MaterialTipDialog
     */
    public MaterialTipDialog btnColorPress(int btnColorPress) {
        this.btnColorPress = btnColorPress;
        return this;
    }

    /**
     * set btn click listener(设置按钮监听事件)
     *
     * @param onBtnClickL
     */
    public void setOnBtnClickL(OnBtnClickL onBtnClickL) {
        this.onBtnClickL = onBtnClickL;
    }

    /**
     * set corner radius (设置圆角程度)
     *
     * @param cornerRadius_DP
     * @return MaterialTipDialog
     */
    public MaterialTipDialog cornerRadius(float cornerRadius_DP) {
        this.cornerRadius_DP = cornerRadius_DP;
        return this;
    }

    /**
     * set backgroud color(设置背景色)
     *
     * @param bgColor
     * @return MaterialTipDialog
     */
    public MaterialTipDialog bgColor(int bgColor) {
        this.bgColor = bgColor;
        return this;
    }
}
