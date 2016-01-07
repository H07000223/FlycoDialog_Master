package com.flyco.dialog.widget.popup;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.flyco.animation.BounceEnter.BounceLeftEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.dialog.R;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.utils.StatusBarUtils;
import com.flyco.dialog.view.TriangleView;
import com.flyco.dialog.widget.internal.InternalBasePopup;
import com.flyco.dialog.widget.popup.base.BaseBubblePopup;
import com.flyco.dialog.widget.popup.base.BasePopup;
import com.nineoldandroids.view.ViewHelper;

/**
 * Use dialog to realize bubble style popup(利用Dialog实现泡泡样式的弹窗)
 * thanks https://github.com/michaelye/EasyDialog
 */
public class BubblePopup extends BaseBubblePopup<BubblePopup> {

    public BubblePopup(Context context, View wrappedView) {
        super(context, wrappedView);
    }

    @Override
    public View onCreateBubbleView() {
        return null;
    }
}
