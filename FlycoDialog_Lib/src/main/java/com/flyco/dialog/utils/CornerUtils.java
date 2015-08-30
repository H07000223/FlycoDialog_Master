package com.flyco.dialog.utils;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Utils to get corner drawable
 */
public class CornerUtils {
    public static Drawable cornerDrawable(final int bgColor, float cornerradius) {
        final GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadius(cornerradius);
        bg.setColor(bgColor);

        return bg;
    }

    public static Drawable cornerDrawable(final int bgColor, float[] cornerradius) {
        final GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadii(cornerradius);
        bg.setColor(bgColor);

        return bg;
    }

    public static Drawable cornerDrawable(final int bgColor, float[] cornerradius, int borderwidth, int bordercolor) {
        final GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadii(cornerradius);
        bg.setStroke(borderwidth, bordercolor);
        bg.setColor(bgColor);

        return bg;
    }

    /**
     * set btn selector with corner drawable for special position
     */
    public static StateListDrawable btnSelector(float radius, int normalColor, int pressColor, int postion) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = null;
        Drawable pressed = null;

        if (postion == 0) {// left btn
            normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, 0, 0, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, 0, 0, radius, radius});
        } else if (postion == 1) {// right btn
            normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, radius, radius, 0, 0});
            pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, radius, radius, 0, 0});
        } else if (postion == -1) {// only one btn
            normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
        }

        bg.addState(new int[]{-android.R.attr.state_pressed}, normal);
        bg.addState(new int[]{android.R.attr.state_pressed}, pressed);
        return bg;
    }

    /**
     * set ListView item selector with corner drawable for the last position
     * (ListView的item点击效果,只处理最后一项圆角处理)
     */
    public static StateListDrawable listItemSelector(float radius, int normalColor, int pressColor, boolean isLastPostion) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = null;
        Drawable pressed = null;

        if (!isLastPostion) {
            normal = new ColorDrawable(normalColor);
            pressed = new ColorDrawable(pressColor);
        } else {
            normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
        }

        bg.addState(new int[]{-android.R.attr.state_pressed}, normal);
        bg.addState(new int[]{android.R.attr.state_pressed}, pressed);
        return bg;
    }

    /**
     * set ListView item selector with corner drawable for the first and the last position
     * (ListView的item点击效果,第一项和最后一项圆角处理)
     */
    public static StateListDrawable listItemSelector(float radius, int normalColor, int pressColor, int itemTotalSize,
                                                     int itemPosition) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = null;
        Drawable pressed = null;

        if (itemPosition == 0 && itemPosition == itemTotalSize - 1) {// 只有一项
            normal = cornerDrawable(normalColor, new float[]{radius, radius, radius, radius, radius, radius, radius,
                    radius});
            pressed = cornerDrawable(pressColor, new float[]{radius, radius, radius, radius, radius, radius, radius,
                    radius});
        } else if (itemPosition == 0) {
            normal = cornerDrawable(normalColor, new float[]{radius, radius, radius, radius, 0, 0, 0, 0,});
            pressed = cornerDrawable(pressColor, new float[]{radius, radius, radius, radius, 0, 0, 0, 0});
        } else if (itemPosition == itemTotalSize - 1) {
            normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
        } else {
            normal = new ColorDrawable(normalColor);
            pressed = new ColorDrawable(pressColor);
        }

        bg.addState(new int[]{-android.R.attr.state_pressed}, normal);
        bg.addState(new int[]{android.R.attr.state_pressed}, pressed);
        return bg;
    }

}
