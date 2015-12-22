package com.flyco.dialog.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flyco.animation.BounceEnter.BounceLeftEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.R;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.utils.StatusBarUtils;
import com.flyco.dialog.view.TriangleView;
import com.flyco.dialog.widget.base.BaseDialog;
import com.nineoldandroids.view.ViewHelper;

/**
 * Use dialog to realize bubble style popup(利用Dialog实现泡泡样式的弹窗)
 * https://github.com/michaelye/EasyDialog
 */
public class BubblePopup extends BaseDialog<BubblePopup> {
    private View mWrappedView;
    private LinearLayout mLlContent;
    private TriangleView mTriangleView;
    private RelativeLayout.LayoutParams mLayoutParams;
    private int mX;
    private int mY;
    private View mAnchorView;
    /** BubblePopup位于给定位置上方(Gravity.Top)或者下方(Gravity.Bottom) */
    private int mGravity;
    private int mBackgroundColor;
    private int mCornerRadius;
    private int mMarginLeft;
    private int mMarginRight;

    public BubblePopup(Context context, View wrappedView) {
        super(context);
        mWrappedView = wrappedView;
        showAnim(new BounceLeftEnter());
        dismissAnim(new FadeExit());
        dimEnabled(false);
        heightScale(1);

        backgroundColor(Color.parseColor("#BB000000"));
        cornerRadius(5);
        margin(8, 8);
        gravity(Gravity.TOP);
    }

    @Override
    public View onCreateView() {
        mLlControlHeight.setClipChildren(false);

        View inflate = View.inflate(mContext, R.layout.popup_bubble, null);
        mLlContent = (LinearLayout) inflate.findViewById(R.id.ll_content);
        mTriangleView = (TriangleView) inflate.findViewById(R.id.triangle_view);
        mLlContent.addView(mWrappedView);

        mLayoutParams = (RelativeLayout.LayoutParams) mLlContent.getLayoutParams();

        inflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                setViewLocation();
            }
        });

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mLlContent.setBackgroundDrawable(
                CornerUtils.cornerDrawable(mBackgroundColor, mCornerRadius));
        mLayoutParams.setMargins(mMarginLeft, 0, mMarginRight, 0);
        mLlContent.setLayoutParams(mLayoutParams);

        mTriangleView.setColor(mBackgroundColor);
        mTriangleView.setGravity(mGravity == Gravity.TOP ? Gravity.BOTTOM : Gravity.TOP);
    }

    public BubblePopup backgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        return this;
    }

    public BubblePopup cornerRadius(float cornerRadius) {
        mCornerRadius = dp2px(cornerRadius);
        return this;
    }

    /** Gravity.Top or Gravity.Bottom */
    public BubblePopup gravity(int gravity) {
        if (gravity != Gravity.TOP && gravity != Gravity.BOTTOM) {
            throw new IllegalArgumentException("Gravity must be either Gravity.TOP or Gravity.BOTTOM");
        }
        mGravity = gravity;
        anchorView(mAnchorView);
        return this;
    }

    public BubblePopup margin(float marginLeft, float marginRight) {
        mMarginLeft = dp2px(marginLeft);
        mMarginRight = dp2px(marginRight);
        return this;
    }

    public BubblePopup location(int x, int y) {
        this.mX = x;
        this.mY = y;
        return this;
    }

    public BubblePopup anchorView(View anchorView) {
        if (anchorView != null) {
            mAnchorView = anchorView;
            int[] location = new int[2];
            mAnchorView.getLocationOnScreen(location);

            mX = location[0] + anchorView.getWidth() / 2;
            if (mGravity == Gravity.TOP) {
                mY = location[1] - dp2px(1);
            } else {
                mY = location[1] + anchorView.getHeight() + dp2px(1);
            }
        }
        return this;
    }

    private void setViewLocation() {
        ViewHelper.setX(mTriangleView, mX - mTriangleView.getWidth() / 2);

        if (mGravity == Gravity.TOP) {
            int y = mY - mTriangleView.getHeight() - StatusBarUtils.getHeight(mContext);
            ViewHelper.setY(mTriangleView, y);
            ViewHelper.setY(mLlContent, y - mLlContent.getHeight());
        } else {
            int y = mY - StatusBarUtils.getHeight(mContext);
            ViewHelper.setY(mTriangleView, y);
            ViewHelper.setY(mLlContent, y + mTriangleView.getHeight());
        }

        /**
         * mX--->三角形中心距离屏幕左边距离
         * mDisplayMetrics.widthPixels - mX--->三角形中心距离屏幕右边距离
         */
        int availableLeft = mX - mLayoutParams.leftMargin;//左侧最大可用距离
        int availableRight = mDisplayMetrics.widthPixels - mX - mLayoutParams.rightMargin;//右侧最大可用距离

        int x = 0;
        int contentWidth = mLlContent.getWidth();
        if (contentWidth / 2 <= availableLeft && contentWidth / 2 <= availableRight) {
            x = mX - contentWidth / 2;
        } else {
            if (availableLeft <= availableRight) {//三角形在屏幕中心的左边
                x = mLayoutParams.leftMargin;
            } else {//三角形在屏幕中心的右边
                x = mDisplayMetrics.widthPixels - (contentWidth + mLayoutParams.rightMargin);
            }
        }
        ViewHelper.setX(mLlContent, x);
    }
}
