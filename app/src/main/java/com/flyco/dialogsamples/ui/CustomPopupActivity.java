package com.flyco.dialogsamples.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceBottomEnter;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideEnter.SlideTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.animation.SlideExit.SlideTopExit;
import com.flyco.dialog.widget.popup.base.BasePopup;
import com.flyco.dialogsamples.R;
import com.flyco.dialogsamples.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomPopupActivity extends AppCompatActivity {
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
    private SimpleCustomPop mQuickCustomPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_custom_popup);
        ButterKnife.bind(this);

        mQuickCustomPopup = new SimpleCustomPop(mContext);
    }

    @OnClick(R.id.tv_center)
    void clickCenterBtn() {
        mQuickCustomPopup
                .alignCenter(true)
                .anchorView(mTvCenter)
                .gravity(Gravity.BOTTOM)
                .showAnim(new SlideTopEnter())
                .dismissAnim(new SlideTopExit())
                .offset(0, 0)
                .dimEnabled(false)
                .show();
    }

    @OnClick(R.id.tv_top_left)
    void clickTopLeftBtn() {
        mQuickCustomPopup
                .anchorView(mTvTopLeft)
                .gravity(Gravity.BOTTOM)
                .offset(0, 0)
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideTopExit())
                .dimEnabled(false)
                .show();
    }

    @OnClick(R.id.tv_top_right)
    void clickTopRightBtn() {
        mQuickCustomPopup
                .anchorView(mTvTopRight)
                .offset(-10, 5)
                .gravity(Gravity.BOTTOM)
                .showAnim(new BounceTopEnter())
                .dismissAnim(new SlideTopExit())
                .dimEnabled(false)
                .show();
    }

    @OnClick(R.id.tv_bottom_left)
    void clickBottomLeftBtn() {
        mQuickCustomPopup
                .anchorView(mTvBottomLeft)
                .offset(10, -5)
                .gravity(Gravity.TOP)
                .showAnim(new BounceBottomEnter())
                .dismissAnim(new SlideBottomExit())
                .dimEnabled(true)
                .show();
    }

    @OnClick(R.id.tv_bottom_right)
    void clickBottomRightBtn() {
        mQuickCustomPopup
                .showAnim(null)
                .dismissAnim(null)
                .dimEnabled(true)
                .anchorView(mTvBottomRight)
                .offset(-10, -5)
                .dimEnabled(false)
                .gravity(Gravity.TOP)
                .show();
    }

    private class SimpleCustomPop extends BasePopup<SimpleCustomPop> {

        private TextView mTvItem1;
        private TextView mTvItem2;
        private TextView mTvItem3;
        private TextView mTvItem4;

        public SimpleCustomPop(Context context) {
            super(context);
        }

        @Override
        public View onCreatePopupView() {
            View inflate = View.inflate(mContext, R.layout.popup_custom, null);
            mTvItem1 = (TextView) inflate.findViewById(R.id.tv_item_1);
            mTvItem2 = (TextView) inflate.findViewById(R.id.tv_item_2);
            mTvItem3 = (TextView) inflate.findViewById(R.id.tv_item_3);
            mTvItem4 = (TextView) inflate.findViewById(R.id.tv_item_4);
            return inflate;
        }

        @Override
        public void setUiBeforShow() {
            mTvItem1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.showShort(mContext, mTvItem1.getText());
                }
            });
            mTvItem2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.showShort(mContext, mTvItem2.getText());
                }
            });
            mTvItem3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.showShort(mContext, mTvItem3.getText());
                }
            });
            mTvItem4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.showShort(mContext, mTvItem4.getText());
                }
            });
        }
    }
}
