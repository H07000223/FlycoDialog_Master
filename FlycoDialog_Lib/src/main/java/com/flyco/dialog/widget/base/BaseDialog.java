package com.flyco.dialog.widget.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
    /** mTag(日志) */
    protected String mTag;
    /** mContext(上下文) */
    protected Context mContext;
    /** (DisplayMetrics)设备密度 */
    protected DisplayMetrics mDisplayMetrics;
    /** enable dismiss outside dialog(设置点击对话框以外区域,是否dismiss) */
    protected boolean mCancel;
    /** dialog width scale(宽度比例) */
    protected float mWidthScale = 1;
    /** dialog height scale(高度比例) */
    protected float mHeightScale;
    /** mShowAnim(对话框显示动画) */
    private BaseAnimatorSet mShowAnim;
    /** mDismissAnim(对话框消失动画) */
    private BaseAnimatorSet mDismissAnim;
    /** top container(最上层容器) */
    protected LinearLayout mLlTop;
    /** container to control dialog height(用于控制对话框高度) */
    protected LinearLayout mLlControlHeight;
    /** the child of mLlControlHeight you create.(创建出来的mLlControlHeight的直接子View) */
    protected View mOnCreateView;
    /** is mShowAnim running(显示动画是否正在执行) */
    private boolean mIsShowAnim;
    /** is DismissAnim running(消失动画是否正在执行) */
    private boolean mIsDismissAnim;
    /** max height(最大高度) */
    protected float mMaxHeight;
    /** show Dialog as PopupWindow(像PopupWindow一样展示Dialog) */
    private boolean mIsPopupStyle;
    /** automatic dimiss dialog after given delay(在给定时间后,自动消失) */
    private boolean mAutoDismiss;
    /** delay (milliseconds) to dimiss dialog(对话框消失延时时间,毫秒值) */
    private long mAutoDismissDelay = 1500;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    /**
     * method execute order:
     * show:constrouctor---show---oncreate---onStart---onAttachToWindow
     * dismiss:dismiss---onDetachedFromWindow---onStop
     */
    public BaseDialog(Context context) {
        super(context);
        setDialogTheme();
        mContext = context;
        mTag = getClass().getSimpleName();
        setCanceledOnTouchOutside(true);
        Log.d(mTag, "constructor");
    }

    public BaseDialog(Context context, boolean isPopupStyle) {
        this(context);
        mIsPopupStyle = isPopupStyle;
    }

    /** set dialog theme(设置对话框主题) */
    private void setDialogTheme() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// android:windowNoTitle
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// android:windowBackground
        getWindow().addFlags(LayoutParams.FLAG_DIM_BEHIND);// android:backgroundDimEnabled默认是true的
    }

    /**
     * inflate layout for dialog ui and return (填充对话框所需要的布局并返回)
     * <pre>
     * public View onCreateView() {
     *      View inflate = View.inflate(mContext, R.layout.dialog_share, null);
     *      return inflate;
     * }
     * </pre>
     */
    public abstract View onCreateView();

    public void onViewCreated(View inflate) {
    }

    /** set Ui data or logic opreation before attatched window(在对话框显示之前,设置界面数据或者逻辑) */
    public abstract void setUiBeforShow();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(mTag, "onCreate");
        mDisplayMetrics = mContext.getResources().getDisplayMetrics();
        mMaxHeight = mDisplayMetrics.heightPixels - StatusBarUtils.getHeight(mContext);
        // mMaxHeight = mDisplayMetrics.heightPixels;

        mLlTop = new LinearLayout(mContext);
        mLlTop.setGravity(Gravity.CENTER);

        mLlControlHeight = new LinearLayout(mContext);
        mLlControlHeight.setOrientation(LinearLayout.VERTICAL);

        mOnCreateView = onCreateView();
        mLlControlHeight.addView(mOnCreateView);
        mLlTop.addView(mLlControlHeight);
        onViewCreated(mOnCreateView);

        if (mIsPopupStyle) {
            setContentView(mLlTop, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
            setContentView(mLlTop, new ViewGroup.LayoutParams(mDisplayMetrics.widthPixels, (int) mMaxHeight));
        }

        mLlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCancel) {
                    dismiss();
                }
            }
        });

        mOnCreateView.setClickable(true);
    }

    /** get actual created view(获取实际创建的View) */
    public View getCreateView() {
        return mOnCreateView;
    }

    /**
     * when dailog attached to window,set dialog width and height and show anim
     * (当dailog依附在window上,设置对话框宽高以及显示动画)
     */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(mTag, "onAttachedToWindow");

        setUiBeforShow();

        int width;
        if (mWidthScale == 0) {
            width = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            width = (int) (mDisplayMetrics.widthPixels * mWidthScale);
        }

        int height;
        if (mHeightScale == 0) {
            height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else if (mHeightScale == 1) {
//            height = ViewGroup.LayoutParams.MATCH_PARENT;
            height = (int) mMaxHeight;
        } else {
            height = (int) (mMaxHeight * mHeightScale);
        }

        mLlControlHeight.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        if (mShowAnim != null) {
            mShowAnim.listener(new BaseAnimatorSet.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    mIsShowAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mIsShowAnim = false;
                    delayDismiss();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    mIsShowAnim = false;
                }
            }).playOn(mLlControlHeight);
        } else {
            BaseAnimatorSet.reset(mLlControlHeight);
            delayDismiss();
        }
    }


    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        this.mCancel = cancel;
        super.setCanceledOnTouchOutside(cancel);
    }

    @Override
    public void show() {
        Log.d(mTag, "show");
        super.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(mTag, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(mTag, "onStop");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(mTag, "onDetachedFromWindow");
    }

    @Override
    public void dismiss() {
        Log.d(mTag, "dismiss");
        if (mDismissAnim != null) {
            mDismissAnim.listener(new BaseAnimatorSet.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    mIsDismissAnim = true;
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    mIsDismissAnim = false;
                    superDismiss();
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                    mIsDismissAnim = false;
                    superDismiss();
                }
            }).playOn(mLlControlHeight);
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

    /** show at location only valid for mIsPopupStyle true(指定位置显示,只对isPopupStyle为true有效) */
    public void showAtLocation(int gravity, int x, int y) {
        if (mIsPopupStyle) {
            Window window = getWindow();
            LayoutParams params = window.getAttributes();
            window.setGravity(gravity);
            params.x = x;
            params.y = y;
        }

        show();
    }

    /** show at location only valid for mIsPopupStyle true(指定位置显示,只对isPopupStyle为true有效) */
    public void showAtLocation(int x, int y) {
        int gravity = Gravity.LEFT | Gravity.TOP;//Left Top (坐标原点为右上角)
        showAtLocation(gravity, x, y);
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
        this.mWidthScale = widthScale;
        return (T) this;
    }

    /** set dialog height scale:0-1(设置对话框高度,占屏幕宽的比例0-1) */
    public T heightScale(float heightScale) {
        mHeightScale = heightScale;
        return (T) this;
    }

    /** set show anim(设置显示的动画) */
    public T showAnim(BaseAnimatorSet showAnim) {
        mShowAnim = showAnim;
        return (T) this;
    }

    /** set dismiss anim(设置隐藏的动画) */
    public T dismissAnim(BaseAnimatorSet dismissAnim) {
        mDismissAnim = dismissAnim;
        return (T) this;
    }

    /** automatic dimiss dialog after given delay(在给定时间后,自动消失) */
    public T autoDismiss(boolean autoDismiss) {
        mAutoDismiss = autoDismiss;
        return (T) this;
    }

    /** set dealy (milliseconds) to dimiss dialog(对话框消失延时时间,毫秒值) */
    public T autoDismissDelay(long autoDismissDelay) {
        mAutoDismissDelay = autoDismissDelay;
        return (T) this;
    }

    private void delayDismiss() {
        if (mAutoDismiss && mAutoDismissDelay > 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, mAutoDismissDelay);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsDismissAnim || mIsShowAnim || mAutoDismiss) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (mIsDismissAnim || mIsShowAnim || mAutoDismiss) {
            return;
        }
        super.onBackPressed();
    }

    /** dp to px */
    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
