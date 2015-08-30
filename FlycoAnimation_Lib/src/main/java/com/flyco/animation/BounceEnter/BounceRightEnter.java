package com.flyco.animation.BounceEnter;

import android.util.DisplayMetrics;
import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;
import com.flyco.animation.BaseAnimatorSet;

public class BounceRightEnter extends BaseAnimatorSet {
	public BounceRightEnter() {
		duration = 1000;
	}

	@Override
	public void setAnimation(View view) {

		DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
		animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1, 1),//
				ObjectAnimator.ofFloat(view, "translationX", 250 * dm.density, -30, 10, 0));
	}
}
