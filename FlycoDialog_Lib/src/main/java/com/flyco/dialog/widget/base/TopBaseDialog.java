package com.flyco.dialog.widget.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.ObjectAnimator;
import com.flyco.animation.BaseAnimatorSet;

public abstract class TopBaseDialog<T extends TopBaseDialog<T>> extends BottomTopBaseDialog {
    public TopBaseDialog(Context context, View animateView) {
        super(context);
        this.mAnimateView = animateView;

        mInnerShowAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_SELF, 0);

        mInnerDismissAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1);
    }

    public TopBaseDialog(Context context) {
        this(context, null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLinearLayoutTop.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        mLinearLayoutTop.setGravity(Gravity.TOP);
        getWindow().setGravity(Gravity.TOP);
        mLinearLayoutTop.setPadding(mLeft, mTop, mRight, mBottom);
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

    private BaseAnimatorSet mWindowInAs;
    private BaseAnimatorSet mWindowOutAs;

    @Override
    protected BaseAnimatorSet getWindowInAs() {
        if (mWindowInAs == null) {
            mWindowInAs = new WindowInAs();
        }
        return mWindowInAs;
    }

    @Override
    protected BaseAnimatorSet getWindowOutAs() {
        if (mWindowOutAs == null) {
            mWindowOutAs = new WindowOutAs();
        }
        return mWindowOutAs;
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
