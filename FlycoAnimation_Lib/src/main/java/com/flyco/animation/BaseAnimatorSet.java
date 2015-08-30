package com.flyco.animation;

import android.view.View;
import android.view.animation.Interpolator;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.view.ViewHelper;

public abstract class BaseAnimatorSet {
	/** 动画时长,系统默认250 */
	protected long duration = 500;
	protected AnimatorSet animatorSet = new AnimatorSet();
	private Interpolator interpolator;
	private long delay;
	private AnimatorListener listener;

	public abstract void setAnimation(View view);

	protected void start(final View view) {
		/** 设置动画中心点:pivotX--->X轴方向动画中心点,pivotY--->Y轴方向动画中心点 */
		// ViewHelper.setPivotX(view, view.getMeasuredWidth() / 2.0f);
		// ViewHelper.setPivotY(view, view.getMeasuredHeight() / 2.0f);
		reset(view);
		setAnimation(view);

		animatorSet.setDuration(duration);
		if (interpolator != null) {
			animatorSet.setInterpolator(interpolator);
		}

		if (delay > 0) {
			animatorSet.setStartDelay(delay);
		}

		if (listener != null) {
			animatorSet.addListener(new Animator.AnimatorListener() {
				@Override
				public void onAnimationStart(Animator animator) {
					listener.onAnimationStart(animator);
				}

				@Override
				public void onAnimationRepeat(Animator animator) {
					listener.onAnimationRepeat(animator);
				}

				@Override
				public void onAnimationEnd(Animator animator) {
					listener.onAnimationEnd(animator);
				}

				@Override
				public void onAnimationCancel(Animator animator) {
					listener.onAnimationCancel(animator);
				}
			});
		}

		animatorSet.start();
	}

	public static void reset(View view) {
		ViewHelper.setAlpha(view, 1);
		ViewHelper.setScaleX(view, 1);
		ViewHelper.setScaleY(view, 1);
		ViewHelper.setTranslationX(view, 0);
		ViewHelper.setTranslationY(view, 0);
		ViewHelper.setRotation(view, 0);
		ViewHelper.setRotationY(view, 0);
		ViewHelper.setRotationX(view, 0);
	}

	/** 设置动画时长 */
	public BaseAnimatorSet duration(long duration) {
		this.duration = duration;
		return this;
	}

	/** 设置动画时长 */
	public BaseAnimatorSet delay(long delay) {
		this.delay = delay;
		return this;
	}

	/** 设置动画插补器 */
	public BaseAnimatorSet interpolator(Interpolator interpolator) {
		this.interpolator = interpolator;
		return this;
	}

	/** 动画监听 */
	public BaseAnimatorSet listener(AnimatorListener listener) {
		this.listener = listener;
		return this;
	}

	/** 在View上执行动画 */
	public void playOn(View view) {
		start(view);
	}

	public interface AnimatorListener {
		void onAnimationStart(Animator animator);

		void onAnimationRepeat(Animator animator);

		void onAnimationEnd(Animator animator);

		void onAnimationCancel(Animator animator);
	}
}
