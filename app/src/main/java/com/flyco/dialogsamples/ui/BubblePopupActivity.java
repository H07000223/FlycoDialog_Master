package com.flyco.dialogsamples.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceRightEnter;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.animation.SlideExit.SlideLeftExit;
import com.flyco.animation.SlideExit.SlideTopExit;
import com.flyco.dialog.widget.BubblePopup;
import com.flyco.dialogsamples.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BubblePopupActivity extends AppCompatActivity {
    @Bind(R.id.tv_top_left)
    TextView mTvTopLeft;
    @Bind(R.id.tv_top_right)
    TextView mTvTopRight;
    @Bind(R.id.tv_bottom_left)
    TextView mTvBottomLeft;
    @Bind(R.id.tv_bottom_right)
    TextView mTvBottomRight;
    @Bind(R.id.tv_center)
    TextView mTvCenter;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_popup_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_center)
    void clickCenterBtn() {
        View inflate = View.inflate(mContext, R.layout.layout_bubble_image, null);
        BubblePopup bubblePopup = new BubblePopup(mContext, inflate);
        bubblePopup.anchorView(mTvCenter)
                .showAnim(new BounceRightEnter())
                .dismissAnim(new SlideLeftExit())
                .show();
    }

    @OnClick(R.id.tv_top_left)
    void clickTopLeftBtn() {
        View inflate = View.inflate(mContext, R.layout.layout_bubble_text, null);
        TextView tv = ButterKnife.findById(inflate, R.id.tv);
        BubblePopup bubblePopup = new BubblePopup(mContext, inflate);
        tv.setText("最美的不是下雨天,是曾与你躲过雨的屋檐~");
        bubblePopup.anchorView(mTvTopLeft)
                .gravity(Gravity.BOTTOM)
                .show();
    }

    @OnClick(R.id.tv_top_right)
    void clickTopRightBtn() {
        View inflate = View.inflate(mContext, R.layout.layout_bubble_image, null);
        new BubblePopup(mContext, inflate)
                .gravity(Gravity.BOTTOM)
                .anchorView(mTvTopRight)
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideTopExit())
                .show();
    }

    @OnClick(R.id.tv_bottom_left)
    void clickBottomLeftBtn() {
        View inflate = View.inflate(mContext, R.layout.layout_bubble_text, null);
        new BubblePopup(mContext, inflate)
                .anchorView(mTvBottomLeft)
                .show();
    }

    @OnClick(R.id.tv_bottom_right)
    void clickBottomRightBtn() {
        View inflate = View.inflate(mContext, R.layout.layout_bubble_image, null);
        new BubblePopup(mContext, inflate).anchorView(mTvBottomRight)
                .backgroundColor(Color.parseColor("#8BC34A"))
                .showAnim(new SlideBottomEnter())
                .dismissAnim(new SlideBottomExit())
                .show();
    }
}
