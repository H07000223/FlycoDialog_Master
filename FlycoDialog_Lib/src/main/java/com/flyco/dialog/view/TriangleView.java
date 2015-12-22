package com.flyco.dialog.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

public class TriangleView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath = new Path();
    private int mColor;
    /** 三角形朝向 */
    private int mGravity;

    public TriangleView(Context context) {
        super(context);
    }

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setColor(int color) {
        mColor = color;
        invalidate();
    }

    public void setGravity(int gravity) {
        mGravity = gravity;
        invalidate();
    }

    public int getColor() {
        return mColor;
    }

    public int getGravity() {
        return mGravity;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        mPaint.setColor(mColor);

        mPath.reset();
        if (mGravity == Gravity.TOP) {//三角形朝上
            mPath.moveTo(width / 2, 0);
            mPath.lineTo(0, height);
            mPath.lineTo(width, height);
            mPath.close();
        } else if (mGravity == Gravity.BOTTOM) {//三角形朝下
            mPath.moveTo(0, 0);
            mPath.lineTo(width, 0);
            mPath.lineTo(width / 2, height);
            mPath.close();
        }


        canvas.drawPath(mPath, mPaint);
    }
}