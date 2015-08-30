package com.flyco.dialog.widget.base;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.flyco.animation.BaseAnimatorSet;

public abstract class BottomTopBaseDialog extends BaseDialog {
    protected View animateView;
    private BaseAnimatorSet windowInAs;
    private BaseAnimatorSet windowOutAs;
    protected Animation innerShowAnim;
    protected Animation innerDismissAnim;
    protected long innerAnimDuration = 350;
    protected boolean isInnerShowAnim;
    protected boolean isInnerDismissAnim;
    protected int left, top, right, bottom;

    public BottomTopBaseDialog(Context context) {
        super(context);
    }

    /**
     * set duration for inner animation of animateView(设置animateView内置动画时长)
     * @param innerAnimDuration
     */
    public void innerAnimDuration(long innerAnimDuration) {
        this.innerAnimDuration = innerAnimDuration;
    }

    public void padding(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    /**
     * show dialog and animateView with inner show animation(设置dialog和animateView显示动画)
     */
    protected void showWithAnim() {
        if (innerShowAnim != null) {
            innerShowAnim.setDuration(innerAnimDuration);
            innerShowAnim.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isInnerShowAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isInnerShowAnim = false;
                }
            });
            ll_control_height.startAnimation(innerShowAnim);
        }

        if (animateView != null) {
            if (getWindowInAs() != null) {
                windowInAs = getWindowInAs();
            }
            windowInAs.duration(innerAnimDuration).playOn(animateView);
        }
    }

    /**
     * dimiss dialog and animateView with inner dismiss animation(设置dialog和animateView消失动画)
     */
    protected void dismissWithAnim() {
        if (innerDismissAnim != null) {
            innerDismissAnim.setDuration(innerAnimDuration);
            innerDismissAnim.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isInnerDismissAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isInnerDismissAnim = false;
                    superDismiss();
                }
            });

            ll_control_height.startAnimation(innerDismissAnim);
        } else {
            superDismiss();
        }

        if (animateView != null) {
            if (getWindowOutAs() != null) {
                windowOutAs = getWindowOutAs();
            }
            windowOutAs.duration(innerAnimDuration).playOn(animateView);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isInnerDismissAnim || isInnerShowAnim) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (isInnerDismissAnim || isInnerShowAnim) {
            return;
        }
        super.onBackPressed();
    }

    protected abstract BaseAnimatorSet getWindowInAs();

    protected abstract BaseAnimatorSet getWindowOutAs();
}
