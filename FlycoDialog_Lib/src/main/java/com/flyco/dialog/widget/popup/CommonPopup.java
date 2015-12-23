package com.flyco.dialog.widget.popup;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.flyco.dialog.R;
import com.flyco.dialog.utils.StatusBarUtils;
import com.flyco.dialog.widget.base.BasePopup;
import com.nineoldandroids.view.ViewHelper;

public class CommonPopup extends BasePopup<CommonPopup> {
    private View mWrappedView;
    private LinearLayout mLlContent;

    public CommonPopup(Context context, View wrappedView) {
        super(context);
        mWrappedView = wrappedView;
        gravity(Gravity.TOP);
    }

    @Override
    public View onCreateView() {
        View inflate = View.inflate(mContext, R.layout.popup_base, null);
        mLlContent = (LinearLayout) inflate.findViewById(R.id.ll_content);
        mLlContent.addView(mWrappedView);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
    }

    @Override
    public CommonPopup anchorView(View anchorView, int xOffset, int yOffset) {
        if (anchorView != null) {
            mAnchorView = anchorView;
            mXOffset = xOffset;
            mYOffset = yOffset;
            int[] location = new int[2];
            mAnchorView.getLocationOnScreen(location);

            mX = location[0] + xOffset;
            if (mGravity == Gravity.TOP) {
                mY = location[1] - StatusBarUtils.getHeight(mContext)
                        + yOffset;
            } else {
                mY = location[1] - StatusBarUtils.getHeight(mContext)
                        + anchorView.getHeight() + yOffset;
            }
        }
        return this;
    }

    /** At this time, we can get view size in dialog(可以获得对话框内视图大小) */
    @Override
    public void onLayoutObtain() {
        if (mX < 0) {
            mX = 0;
        }
        if (mX + mLlContent.getWidth() > mDisplayMetrics.widthPixels) {
            mX = mDisplayMetrics.widthPixels - mLlContent.getWidth();
        }
        if (mY < 0) {
            mY = 0;
        }
        if (mY + mLlContent.getHeight() > mMaxHeight) {
            mY = (int) (mMaxHeight - mLlContent.getHeight());
        }

        ViewHelper.setX(mLlContent, mX);
        ViewHelper.setY(mLlContent, mY);
    }
}
