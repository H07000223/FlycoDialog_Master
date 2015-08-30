package com.flyco.animation.Attention;

import android.view.View;
import android.view.animation.CycleInterpolator;

import com.nineoldandroids.animation.ObjectAnimator;
import com.flyco.animation.BaseAnimatorSet;

public class ShakeHorizontal extends BaseAnimatorSet {
	public ShakeHorizontal() {
		duration = 1000;
	}

	@Override
	public void setAnimation(View view) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", -10, 10);
		animator.setInterpolator(new CycleInterpolator(5));
		animatorSet.playTogether(animator);

		/**
		 * <pre>
		 *  另一种shake实现
		 * ObjectAnimator.ofFloat(view, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0);
		 * </pre>
		 */
	}
}
