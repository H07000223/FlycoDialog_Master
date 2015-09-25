package com.flyco.dialog.widget.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.ObjectAnimator;
import com.flyco.animation.BaseAnimatorSet;

public abstract class BottomBaseDialog<T extends BottomBaseDialog<T>> extends BottomTopBaseDialog {
    public BottomBaseDialog(Context context, View animateView) {
        super(context);
        this.animateView = animateView;

        innerShowAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0);

        innerDismissAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
    }

    public BottomBaseDialog(Context context) {
        this(context, null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ll_top.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        ll_top.setGravity(Gravity.BOTTOM);
        getWindow().setGravity(Gravity.BOTTOM);
        ll_top.setPadding(left, top, right, bottom);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        showWithAnim();
    }


    @Override
    public void dismiss() {
        dismissWithAnim();
    }

    private BaseAnimatorSet windowInAs;
    private BaseAnimatorSet windowOutAs;

    @Override
    protected BaseAnimatorSet getWindowInAs() {
        if (windowInAs == null) {
            windowInAs = new WindowInAs();
        }
        return windowInAs;
    }

    @Override
    protected BaseAnimatorSet getWindowOutAs() {
        if (windowOutAs == null) {
            windowOutAs = new WindowOutAs();
        }
        return windowOutAs;
    }


    private class WindowInAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator oa1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.9f);
            ObjectAnimator oa2 = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.9f);
            animatorSet.playTogether(oa1, oa2);
        }
    }

    private class WindowOutAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator oa1 = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f);
            ObjectAnimator oa2 = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f);
            animatorSet.playTogether(oa1, oa2);
        }
    }
}
