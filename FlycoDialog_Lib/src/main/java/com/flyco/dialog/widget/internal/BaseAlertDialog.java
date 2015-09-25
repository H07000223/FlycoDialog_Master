package com.flyco.dialog.widget.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.base.BaseDialog;

public abstract class BaseAlertDialog<T extends BaseAlertDialog> extends BaseDialog {
    /** container */
    protected LinearLayout ll_container;

    //title
    /** title */
    protected TextView tv_title;
    /** title content(标题) */
    protected String title;
    /** title textcolor(标题颜色) */
    protected int titleTextColor;
    /** title textsize(标题字体大小,单位sp) */
    protected float titleTextSize_SP;
    /** enable title show(是否显示标题) */
    protected boolean isTitleShow = true;

    //content
    /** content */
    protected TextView tv_content;
    /** content text */
    protected String content;
    /** show gravity of content(正文内容显示位置) */
    protected int contentGravity = Gravity.CENTER_VERTICAL;
    /** content textcolor(正文字体颜色) */
    protected int contentTextColor;
    /** content textsize(正文字体大小) */
    protected float contentTextSize_SP;

    //btns
    /** num of btns, [1,3] */
    protected int btnNum = 2;
    /** btn container */
    protected LinearLayout ll_btns;
    /** btns */
    protected TextView tv_btn_left;
    protected TextView tv_btn_right;
    protected TextView tv_btn_middle;
    /** btn text(按钮内容) */
    protected String btnLeftText = "取消";
    protected String btnRightText = "确定";
    protected String btnMiddleText = "继续";
    /** btn textcolor(按钮字体颜色) */
    protected int leftBtnTextColor;
    protected int rightBtnTextColor;
    protected int middleBtnTextColor;
    /** btn textsize(按钮字体大小) */
    protected float leftBtnTextSize_SP = 15f;
    protected float rightBtnTextSize_SP = 15f;
    protected float middleBtnTextSize_SP = 15f;
    /** btn press color(按钮点击颜色) */
    protected int btnPressColor = Color.parseColor("#E3E3E3");// #85D3EF,#ffcccccc,#E3E3E3
    /** left btn click listener(左按钮接口) */
    protected OnBtnClickL onBtnLeftClickL;
    /** right btn click listener(右按钮接口) */
    protected OnBtnClickL onBtnRightClickL;
    /** middle btn click listener(右按钮接口) */
    protected OnBtnClickL onBtnMiddleClickL;

    /** corner radius,dp(圆角程度,单位dp) */
    protected float cornerRadius_DP = 3;
    /** background color(背景颜色) */
    protected int bgColor = Color.parseColor("#ffffff");

    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     *
     * @param context
     */
    public BaseAlertDialog(Context context) {
        super(context);
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

        /**btns*/
        ll_btns = new LinearLayout(context);
        ll_btns.setOrientation(LinearLayout.HORIZONTAL);
        ll_btns.setGravity(Gravity.RIGHT);

        tv_btn_left = new TextView(context);
        tv_btn_left.setGravity(Gravity.CENTER);
        ll_btns.addView(tv_btn_left);

        tv_btn_middle = new TextView(context);
        tv_btn_middle.setGravity(Gravity.CENTER);
        ll_btns.addView(tv_btn_middle);

        tv_btn_right = new TextView(context);
        tv_btn_right.setGravity(Gravity.CENTER);
        ll_btns.addView(tv_btn_right);

        ll_container.addView(ll_btns);
    }

    @Override
    public void setUiBeforShow() {
        /** title */
        tv_title.setVisibility(isTitleShow ? View.VISIBLE : View.GONE);

        tv_title.setText(TextUtils.isEmpty(title) ? "温馨提示" : title);
        tv_title.setTextColor(titleTextColor);
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTextSize_SP);

        /** content */
        tv_content.setGravity(contentGravity);
        tv_content.setText(content);
        tv_content.setTextColor(contentTextColor);
        tv_content.setTextSize(TypedValue.COMPLEX_UNIT_SP, contentTextSize_SP);
        tv_content.setLineSpacing(0, 1.3f);

        /**btns*/
        tv_btn_left.setText(btnLeftText);
        tv_btn_right.setText(btnRightText);
        tv_btn_middle.setText(btnMiddleText);

        tv_btn_left.setTextColor(leftBtnTextColor);
        tv_btn_right.setTextColor(rightBtnTextColor);
        tv_btn_middle.setTextColor(middleBtnTextColor);

        tv_btn_left.setTextSize(TypedValue.COMPLEX_UNIT_SP, leftBtnTextSize_SP);
        tv_btn_right.setTextSize(TypedValue.COMPLEX_UNIT_SP, rightBtnTextSize_SP);
        tv_btn_middle.setTextSize(TypedValue.COMPLEX_UNIT_SP, middleBtnTextSize_SP);

        if (btnNum == 1) {
            tv_btn_left.setVisibility(View.GONE);
            tv_btn_right.setVisibility(View.GONE);
        } else if (btnNum == 2) {
            tv_btn_middle.setVisibility(View.GONE);
        }

        tv_btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBtnLeftClickL != null) {
                    onBtnLeftClickL.onBtnClick();
                } else {
                    dismiss();
                }
            }
        });

        tv_btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBtnRightClickL != null) {
                    onBtnRightClickL.onBtnClick();
                } else {
                    dismiss();
                }
            }
        });

        tv_btn_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBtnMiddleClickL != null) {
                    onBtnMiddleClickL.onBtnClick();
                } else {
                    dismiss();
                }
            }
        });
    }

    /** set title text(设置标题内容) @return MaterialDialog */
    public T title(String title) {
        this.title = title;
        return (T) this;
    }

    /** set title textcolor(设置标题字体颜色) */
    public T titleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return (T) this;
    }

    /** set title textsize(设置标题字体大小) */
    public T titleTextSize(float titleTextSize_SP) {
        this.titleTextSize_SP = titleTextSize_SP;
        return (T) this;
    }

    /** enable title show(设置标题是否显示) */
    public T isTitleShow(boolean isTitleShow) {
        this.isTitleShow = isTitleShow;
        return (T) this;
    }

    /** set content text(设置正文内容) */
    public T content(String content) {
        this.content = content;
        return (T) this;
    }

    /** set content gravity(设置正文内容,显示位置) */
    public T contentGravity(int contentGravity) {
        this.contentGravity = contentGravity;
        return (T) this;
    }

    /** set content textcolor(设置正文字体颜色) */
    public T contentTextColor(int contentTextColor) {
        this.contentTextColor = contentTextColor;
        return (T) this;
    }

    /** set content textsize(设置正文字体大小,单位sp) */
    public T contentTextSize(float contentTextSize_SP) {
        this.contentTextSize_SP = contentTextSize_SP;
        return (T) this;
    }

    /**
     * set btn text(设置按钮文字内容)
     * btnTexts size 1, middle
     * btnTexts size 2, left right
     * btnTexts size 3, left right middle
     */
    public T btnNum(int btnNum) {
        if (btnNum < 1 || btnNum > 3) {
            throw new IllegalStateException("btnNum is [1,3]!");
        }
        this.btnNum = btnNum;

        return (T) this;
    }

    /**
     * set btn text(设置按钮文字内容)
     * btnTexts size 1, middle
     * btnTexts size 2, left right
     * btnTexts size 3, left right middle
     */
    public T btnText(String... btnTexts) {
        if (btnTexts.length < 1 || btnTexts.length > 3) {
            throw new IllegalStateException(" range of param btnTexts length is [1,3]!");
        }

        if (btnTexts.length == 1) {
            this.btnMiddleText = btnTexts[0];
        } else if (btnTexts.length == 2) {
            this.btnLeftText = btnTexts[0];
            this.btnRightText = btnTexts[1];
        } else if (btnTexts.length == 3) {
            this.btnLeftText = btnTexts[0];
            this.btnRightText = btnTexts[1];
            this.btnMiddleText = btnTexts[2];
        }

        return (T) this;
    }

    /**
     * set btn textcolor(设置按钮字体颜色)
     * btnTextColors size 1, middle
     * btnTextColors size 2, left right
     * btnTextColors size 3, left right middle
     */
    public T btnTextColor(int... btnTextColors) {
        if (btnTextColors.length < 1 || btnTextColors.length > 3) {
            throw new IllegalStateException(" range of param textColors length is [1,3]!");
        }

        if (btnTextColors.length == 1) {
            this.middleBtnTextColor = btnTextColors[0];
        } else if (btnTextColors.length == 2) {
            this.leftBtnTextColor = btnTextColors[0];
            this.rightBtnTextColor = btnTextColors[1];
        } else if (btnTextColors.length == 3) {
            this.leftBtnTextColor = btnTextColors[0];
            this.rightBtnTextColor = btnTextColors[1];
            this.middleBtnTextColor = btnTextColors[2];
        }

        return (T) this;
    }

    /**
     * set btn textsize(设置字体大小,单位sp)
     * btnTextSizes size 1, middle
     * btnTextSizes size 2, left right
     * btnTextSizes size 3, left right middle
     */
    public T btnTextSize(float... btnTextSizes) {
        if (btnTextSizes.length < 1 || btnTextSizes.length > 3) {
            throw new IllegalStateException(" range of param btnTextSizes length is [1,3]!");
        }

        if (btnTextSizes.length == 1) {
            this.middleBtnTextSize_SP = btnTextSizes[0];
        } else if (btnTextSizes.length == 2) {
            this.leftBtnTextSize_SP = btnTextSizes[0];
            this.rightBtnTextSize_SP = btnTextSizes[1];
        } else if (btnTextSizes.length == 3) {
            this.leftBtnTextSize_SP = btnTextSizes[0];
            this.rightBtnTextSize_SP = btnTextSizes[1];
            this.middleBtnTextSize_SP = btnTextSizes[2];
        }

        return (T) this;
    }

    /** set btn press color(设置按钮点击颜色) */
    public T btnPressColor(int btnPressColor) {
        this.btnPressColor = btnPressColor;
        return (T) this;
    }

    /** set corner radius (设置圆角程度) */
    public T cornerRadius(float cornerRadius_DP) {
        this.cornerRadius_DP = cornerRadius_DP;
        return (T) this;
    }

    /** set backgroud color(设置背景色) */
    public T bgColor(int bgColor) {
        this.bgColor = bgColor;
        return (T) this;
    }

    /**
     * set btn click listener(设置按钮监听事件)
     * onBtnClickLs size 1, middle
     * onBtnClickLs size 2, left right
     * onBtnClickLs size 3, left right middle
     */
    public void setOnBtnClickL(OnBtnClickL... onBtnClickLs) {
        if (onBtnClickLs.length < 1 || onBtnClickLs.length > 3) {
            throw new IllegalStateException(" range of param onBtnClickLs length is [1,3]!");
        }

        if (onBtnClickLs.length == 1) {
            this.onBtnMiddleClickL = onBtnClickLs[0];
        } else if (onBtnClickLs.length == 2) {
            this.onBtnLeftClickL = onBtnClickLs[0];
            this.onBtnRightClickL = onBtnClickLs[1];
        } else if (onBtnClickLs.length == 3) {
            this.onBtnLeftClickL = onBtnClickLs[0];
            this.onBtnRightClickL = onBtnClickLs[1];
            this.onBtnMiddleClickL = onBtnClickLs[2];
        }
    }
}
