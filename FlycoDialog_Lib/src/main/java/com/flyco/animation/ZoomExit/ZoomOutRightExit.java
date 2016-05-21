package com.flyco.animation.ZoomExit;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.View.MeasureSpec;

import com.flyco.animation.BaseAnimatorSet;

public class ZoomOutRightExit extends BaseAnimatorSet {
	public ZoomOutRightExit() {
		duration = 1000;
	}

	@Override
	public void setAnimation(View view) {
		view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		int w = view.getMeasuredWidth();

		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "scaleX", 1, 0.475f, 0.1f),//
				ObjectAnimator.ofFloat(view, "scaleY", 1, 0.475f, 0.1f),//
				ObjectAnimator.ofFloat(view, "translationX", 0, -42, w),//
				ObjectAnimator.ofFloat(view, "alpha", 1, 1, 0));
	}
}
