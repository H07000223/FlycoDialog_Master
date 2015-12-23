package com.flyco.dialog.widget.internal;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.flyco.dialog.utils.StatusBarUtils;
import com.flyco.dialog.widget.base.BaseDialog;

/** Base class to help create PopupWindow Style Dialog(实现PopupWindow风格对话框基类) */
public abstract class InternalBasePopup<T extends InternalBasePopup<T>> extends BaseDialog<T> {
    protected View mAnchorView;
    protected int mX;
    protected int mY;
    /** BubblePopup位于给定位置上方(Gravity.Top)或者下方(Gravity.Bottom) */
    protected int mGravity;
    protected float mXOffset;
    protected float mYOffset;
    protected boolean isLayoutObtain;

    public InternalBasePopup(Context context) {
        super(context);
        heightScale(1);
        dimEnabled(false);
    }

    @Override
    public void onViewCreated(final View inflate) {
        mLlControlHeight.setClipChildren(false);
        if (inflate != null) {
            inflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    isLayoutObtain = true;
                    onLayoutObtain();
                }
            });
        }
    }

    @Override
    public void setUiBeforShow() {
        if (isLayoutObtain) {
            onLayoutObtain();
        }
    }

    /** At this time, we can get view size in dialog(可以获得对话框内视图大小) */
    public abstract void onLayoutObtain();

    public abstract T anchorView(View anchorView);

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
