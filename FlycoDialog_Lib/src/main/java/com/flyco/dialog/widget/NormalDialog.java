package com.flyco.dialog.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.internal.BaseAlertDialog;

@SuppressWarnings("deprecation")
public class NormalDialog extends BaseAlertDialog<NormalDialog> {
    /** title underline */
    private View mVLineTitle;
    /** vertical line between btns */
    private View mVLineVertical;
    /** vertical line between btns */
    private View mVLineVertical2;
    /** horizontal line above btns */
    private View mVLineHorizontal;
    /** title underline color(标题下划线颜色) */
    private int mTitleLineColor = Color.parseColor("#61AEDC");
    /** title underline height(标题下划线高度) */
    private float mTitleLineHeight = 1f;
    /** btn divider line color(对话框之间的分割线颜色(水平+垂直)) */
    private int mDividerColor = Color.parseColor("#DCDCDC");

    public static final int STYLE_ONE = 0;
    public static final int STYLE_TWO = 1;
    private int mStyle = STYLE_ONE;

    public NormalDialog(Context context) {
        super(context);

        /** default value*/
        mTitleTextColor = Color.parseColor("#61AEDC");
        mTitleTextSize = 22f;
        mContentTextColor = Color.parseColor("#383838");
        mContentTextSize = 17f;
        mLeftBtnTextColor = Color.parseColor("#8a000000");
        mRightBtnTextColor = Color.parseColor("#8a000000");
        mMiddleBtnTextColor = Color.parseColor("#8a000000");
        /** default value*/
    }

    @Override
    public View onCreateView() {
        /** title */
        mTvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mLinearLayoutContainer.addView(mTvTitle);

        /** title underline */
        mVLineTitle = new View(mContext);
        mLinearLayoutContainer.addView(mVLineTitle);

        /** content */
        mTvContent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mLinearLayoutContainer.addView(mTvContent);

        mVLineHorizontal = new View(mContext);
        mVLineHorizontal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
        mLinearLayoutContainer.addView(mVLineHorizontal);

        /** btns */
        mTvBtnLeft.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(45), 1));
        mLinearLayoutBtns.addView(mTvBtnLeft);

        mVLineVertical = new View(mContext);
        mVLineVertical.setLayoutParams(new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT));
        mLinearLayoutBtns.addView(mVLineVertical);

        mTvBtnMiddle.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(45), 1));
        mLinearLayoutBtns.addView(mTvBtnMiddle);

        mVLineVertical2 = new View(mContext);
        mVLineVertical2.setLayoutParams(new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT));
        mLinearLayoutBtns.addView(mVLineVertical2);

        mTvBtnRight.setLayoutParams(new LinearLayout.LayoutParams(0, dp2px(45), 1));
        mLinearLayoutBtns.addView(mTvBtnRight);

        mLinearLayoutContainer.addView(mLinearLayoutBtns);

        return mLinearLayoutContainer;
    }

    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();

        /** title */
        if (mStyle == STYLE_ONE) {
            mTvTitle.setMinHeight(dp2px(48));
            mTvTitle.setGravity(Gravity.CENTER_VERTICAL);
            mTvTitle.setPadding(dp2px(15), dp2px(5), dp2px(0), dp2px(5));
            mTvTitle.setVisibility(mIsTitleShow ? View.VISIBLE : View.GONE);
        } else if (mStyle == STYLE_TWO) {
            mTvTitle.setGravity(Gravity.CENTER);
            mTvTitle.setPadding(dp2px(0), dp2px(15), dp2px(0), dp2px(0));
        }

        /** title underline */
        mVLineTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                dp2px(mTitleLineHeight)));
        mVLineTitle.setBackgroundColor(mTitleLineColor);
        mVLineTitle.setVisibility(mIsTitleShow && mStyle == STYLE_ONE ? View.VISIBLE : View.GONE);

        /** content */
        if (mStyle == STYLE_ONE) {
            mTvContent.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));
            mTvContent.setMinHeight(dp2px(68));
            mTvContent.setGravity(mContentGravity);
        } else if (mStyle == STYLE_TWO) {
            mTvContent.setPadding(dp2px(15), dp2px(7), dp2px(15), dp2px(20));
            mTvContent.setMinHeight(dp2px(56));
            mTvContent.setGravity(Gravity.CENTER);
        }

        /** btns */
        mVLineHorizontal.setBackgroundColor(mDividerColor);
        mVLineVertical.setBackgroundColor(mDividerColor);
        mVLineVertical2.setBackgroundColor(mDividerColor);

        if (mBtnNum == 1) {
            mTvBtnLeft.setVisibility(View.GONE);
            mTvBtnRight.setVisibility(View.GONE);
            mVLineVertical.setVisibility(View.GONE);
            mVLineVertical2.setVisibility(View.GONE);
        } else if (mBtnNum == 2) {
            mTvBtnMiddle.setVisibility(View.GONE);
            mVLineVertical.setVisibility(View.GONE);
        }

        /**set background color and corner radius */
        float radius = dp2px(mCornerRadius);
        mLinearLayoutContainer.setBackgroundDrawable(CornerUtils.cornerDrawable(mBgColor, radius));
        mTvBtnLeft.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, 0));
        mTvBtnRight.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, 1));
        mTvBtnMiddle.setBackgroundDrawable(CornerUtils.btnSelector(mBtnNum == 1 ? radius : 0, mBgColor, mBtnPressColor, -1));
    }

    // --->属性设置

    /** set style(设置style) */
    public NormalDialog style(int style) {
        this.mStyle = style;
        return this;
    }

    /** set title underline color(设置标题下划线颜色) */
    public NormalDialog titleLineColor(int titleLineColor) {
        this.mTitleLineColor = titleLineColor;
        return this;
    }

    /** set title underline height(设置标题下划线高度) */
    public NormalDialog titleLineHeight(float titleLineHeight_DP) {
        this.mTitleLineHeight = titleLineHeight_DP;
        return this;
    }

    /** set divider color between btns(设置btn分割线的颜色) */
    public NormalDialog dividerColor(int dividerColor) {
        this.mDividerColor = dividerColor;
        return this;
    }
}
