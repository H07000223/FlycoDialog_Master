package com.flyco.dialog.widget.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.Animator;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.utils.StatusBarUtils;

public abstract class BaseDialog<T extends BaseDialog<T>> extends Dialog {
    /** TAG(日志) */
    protected String TAG;
    /** context(上下文) */
    protected Context context;
    /** (DisplayMetrics)设备密度 */
    protected DisplayMetrics dm;
    /** enable dismiss outside dialog(设置点击对话框以外区域,是否dismiss) */
    protected boolean cancel;
    /** dialog width scale(宽度比例) */
    protected float widthScale = 1;
    /** dialog height scale(高度比例) */
    protected float heightScale;
    /** showAnim(对话框显示动画) */
    private BaseAnimatorSet showAnim;
    /** dismissAnim(对话框消失动画) */
    private BaseAnimatorSet dismissAnim;
    /** top container(最上层容器) */
    protected LinearLayout ll_top;
    /** container to control dialog height(用于控制对话框高度) */
    protected LinearLayout ll_control_height;
    /** is showAnim running(显示动画是否正在执行) */
    private boolean isShowAnim;
    /** is DismissAnim running(消失动画是否正在执行) */
    private boolean isDismissAnim;
    /** max height(最大高度) */
    protected float maxHeight;

    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     */
    public BaseDialog(Context context) {
        super(context);
        setDialogTheme();
        this.context = context;
        this.TAG = this.getClass().getSimpleName();
        Log.d(TAG, "constructor");
    }

    /**
     * set dialog theme(设置对话框主题)
     */
    private void setDialogTheme() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// android:windowNoTitle
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// android:windowBackground
        getWindow().addFlags(LayoutParams.FLAG_DIM_BEHIND);// android:backgroundDimEnabled默认是true的
    }

    /**
     * inflate layout for dialog ui and return (填充对话框所需要的布局并返回)
     * <pre>
     *
     * public View onCreateView() {
     * View inflate = View.inflate(context, R.layout.dialog_share, null);
     * return inflate;
     * }
     * </pre>
     */
    public abstract View onCreateView();

    /**
     * set Ui data or logic opreation before attatched window(在对话框显示之前,设置界面数据或者逻辑)
     */
    public abstract void setUiBeforShow();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        dm = context.getResources().getDisplayMetrics();
        ll_top = new LinearLayout(context);
        ll_top.setGravity(Gravity.CENTER);

        ll_control_height = new LinearLayout(context);
        ll_control_height.setOrientation(LinearLayout.VERTICAL);

        ll_control_height.addView(onCreateView());
        ll_top.addView(ll_control_height);

        maxHeight = dm.heightPixels - StatusBarUtils.getHeight(context);
        // maxHeight = dm.heightPixels;
        setContentView(ll_top, new ViewGroup.LayoutParams(dm.widthPixels, (int) maxHeight));
        setCanceledOnTouchOutside(true);

        ll_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancel) {
                    dismiss();
                }
            }
        });
    }

    /**
     * when dailog attached to window,set dialog width and height and show anim
     * (当dailog依附在window上,设置对话框宽高以及显示动画)
     */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow");

        setUiBeforShow();

        int width;
        if (widthScale == 0) {
            width = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            width = (int) (dm.widthPixels * widthScale);
        }

        int height;
        if (heightScale == 0) {
            height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else if (heightScale == 1) {
            height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            height = (int) (maxHeight * heightScale);
        }

        ll_control_height.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        if (showAnim != null) {
            showAnim.listener(new BaseAnimatorSet.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    isShowAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    isShowAnim = false;
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    isShowAnim = false;
                }
            }).playOn(ll_control_height);
        } else {
            BaseAnimatorSet.reset(ll_control_height);
        }
    }


    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        this.cancel = cancel;
        super.setCanceledOnTouchOutside(cancel);
    }

    @Override
    public void show() {
        Log.d(TAG, "show");
        super.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
    }

    @Override
    public void dismiss() {
        Log.d(TAG, "dismiss");
        if (dismissAnim != null) {
            dismissAnim.listener(new BaseAnimatorSet.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    isDismissAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    isDismissAnim = false;
                    superDismiss();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    isDismissAnim = false;
                    superDismiss();
                }
            }).playOn(ll_control_height);
        } else {
            superDismiss();
        }
    }

    /** dismiss without anim(无动画dismiss) */
    public void superDismiss() {
        super.dismiss();
    }

    /** dialog anim by styles(动画弹出对话框,style动画资源) */
    public void show(int animStyle) {
        Window window = getWindow();
        window.setWindowAnimations(animStyle);
        show();
    }

    /** set window dim or not(设置背景是否昏暗) */
    public T dimEnabled(boolean isDimEnabled) {
        if (isDimEnabled) {
            getWindow().addFlags(LayoutParams.FLAG_DIM_BEHIND);
        } else {
            getWindow().clearFlags(LayoutParams.FLAG_DIM_BEHIND);
        }
        return (T) this;
    }

    /** set dialog width scale:0-1(设置对话框宽度,占屏幕宽的比例0-1) */
    public T widthScale(float widthScale) {
        this.widthScale = widthScale;
        return (T) this;
    }

    /** set dialog height scale:0-1(设置对话框高度,占屏幕宽的比例0-1) */
    public T heightScale(float heightScale) {
        this.heightScale = heightScale;
        return (T) this;
    }

    /** set show anim(设置显示的动画) */
    public T showAnim(BaseAnimatorSet showAnim) {
        this.showAnim = showAnim;
        return (T) this;
    }

    /** set dismiss anim(设置隐藏的动画) */
    public T dismissAnim(BaseAnimatorSet dismissAnim) {
        this.dismissAnim = dismissAnim;
        return (T) this;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isDismissAnim || isShowAnim) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (isDismissAnim || isShowAnim) {
            return;
        }
        super.onBackPressed();
    }

    /** dp to px */
    protected int dp2px(float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
