package com.flyco.dialog.widget.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.flyco.dialog.utils.StatusBarUtils;
import com.flyco.dialog.widget.popup.CommonPopup;

/** Base class to help create PopupWindow Style Dialog(实现PopupWindow风格对话框基类) */
public abstract class BasePopup<T extends BasePopup<T>> extends BaseDialog {
    protected View mAnchorView;
    protected int mX;
    protected int mY;
    /** BubblePopup位于给定位置上方(Gravity.Top)或者下方(Gravity.Bottom) */
    protected int mGravity;
    protected int mXOffset;
    protected int mYOffset;

    public BasePopup(Context context) {
        super(context);
        heightScale(1);
    }

    @Override
    public void onViewCreated(final View inflate) {
        mLlControlHeight.setClipChildren(false);
        if (inflate != null) {
            inflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    onLayoutObtain();
                }
            });
        }
    }

    /** At this time, we can get view size in dialog(可以获得对话框内视图大小) */
    public abstract void onLayoutObtain();

    public abstract T anchorView(View anchorView, int xOffset, int yOffset);

    public T anchorView(View anchorView) {
        return anchorView(anchorView, 0, 0);
    }

    /** Gravity.Top or Gravity.Bottom of given positon */
    public T gravity(int gravity) {
        if (gravity != Gravity.TOP && gravity != Gravity.BOTTOM) {
            throw new IllegalArgumentException("Gravity must be either Gravity.TOP or Gravity.BOTTOM");
        }
        mGravity = gravity;
        anchorView(mAnchorView);
        return (T) this;
    }

    public T location(int x, int y) {
        mX = x;
        mY = y - StatusBarUtils.getHeight(mContext);
        return (T) this;
    }
}
