package com.flyco.dialog.widget.popup.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.dialog.R;
import com.flyco.dialog.utils.StatusBarUtils;
import com.flyco.dialog.widget.internal.InternalBasePopup;
import com.nineoldandroids.view.ViewHelper;

public abstract class BasePopup<T extends BasePopup<T>> extends InternalBasePopup<T> {
    protected View mWrappedView;
    protected LinearLayout mLlContent;
    protected boolean mAlignCenter;

    public BasePopup(Context context) {
        super(context);
        mWrappedView = onCreatePopupView();
        gravity(Gravity.BOTTOM);
    }

    public abstract View onCreatePopupView();

    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.popup_base, null);
        mLlContent = (LinearLayout) inflate.findViewById(R.id.ll_content);
        mLlContent.addView(mWrappedView);

        //让mOnCreateView充满父控件,防止ViewHelper.setXY导致点击事件无效
        inflate.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        return inflate;
    }

    public T offset(float xOffset, float yOffset) {
        mXOffset = xOffset;
        mYOffset = yOffset;
        return (T) this;
    }

    /** align center of pop and anchorView(弹窗与anchorView中心对齐) */
    public T alignCenter(boolean alignCenter) {
        mAlignCenter = alignCenter;
        return (T) this;
    }

    @Override
    public T anchorView(View anchorView) {
        if (anchorView != null) {
            mAnchorView = anchorView;
            int[] location = new int[2];
            mAnchorView.getLocationOnScreen(location);

            mX = location[0];
            if (mGravity == Gravity.TOP) {
                mY = location[1] - StatusBarUtils.getHeight(mContext);
            } else {
                mY = location[1] - StatusBarUtils.getHeight(mContext)
                        + anchorView.getHeight();
            }
        }
        return (T) this;
    }

    /** At this time, we can get view size in dialog(可以获得对话框内视图大小) */
    @Override
    public void onLayoutObtain() {
        int x = mX;
        int y = mY;
        if (mGravity == Gravity.TOP) {
            y = mY - mLlContent.getHeight();
        }

        if (mAlignCenter) {
            x = mX + mAnchorView.getWidth() / 2 - mLlContent.getWidth() / 2;
        }

        x = getX(x);
        y = getY(y);

        x = getX(x + dp2px(mXOffset));
        y = getY(y + dp2px(mYOffset));

        ViewHelper.setX(mLlContent, x);
        ViewHelper.setY(mLlContent, y);
    }

    private int getX(int x) {
        if (x < 0) {
            x = 0;
        }
        if (x + mLlContent.getWidth() > mDisplayMetrics.widthPixels) {
            x = mDisplayMetrics.widthPixels - mLlContent.getWidth();
        }

        return x;
    }

    private int getY(int y) {
        if (y < 0) {
            y = 0;
        }
        if (y + mLlContent.getHeight() > mMaxHeight) {
            y = (int) (mMaxHeight - mLlContent.getHeight());
        }

        return y;
    }
}
