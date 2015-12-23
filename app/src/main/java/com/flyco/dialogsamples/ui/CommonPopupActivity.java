package com.flyco.dialogsamples.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.flyco.dialog.widget.popup.CommonPopup;
import com.flyco.dialogsamples.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommonPopupActivity extends AppCompatActivity {
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
        setContentView(R.layout.ac_bubble_popup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_center)
    void clickCenterBtn() {
        int[] location = new int[2];
        mTvCenter.getLocationOnScreen(location);
        View inflate = View.inflate(mContext, R.layout.popup_custom_base, null);
        new CommonPopup(mContext, inflate)
                .anchorView(mTvCenter)
                .show();
    }

    @OnClick(R.id.tv_top_left)
    void clickTopLeftBtn() {
    }

    @OnClick(R.id.tv_top_right)
    void clickTopRightBtn() {
    }

    @OnClick(R.id.tv_bottom_left)
    void clickBottomLeftBtn() {
    }

    @OnClick(R.id.tv_bottom_right)
    void clickBottomRightBtn() {
    }
}
