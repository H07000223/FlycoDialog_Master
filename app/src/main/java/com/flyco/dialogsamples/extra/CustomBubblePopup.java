package com.flyco.dialogsamples.extra;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.dialog.widget.popup.base.BaseBubblePopup;
import com.flyco.dialogsamples.R;
import com.flyco.dialogsamples.utils.T;

public class CustomBubblePopup extends BaseBubblePopup<CustomBubblePopup> {

    private ImageView mIvBubble;
    private TextView mTvBubble;

    public CustomBubblePopup(Context context) {
        super(context);
    }

    @Override
    public View onCreateBubbleView() {
        View inflate = View.inflate(mContext, R.layout.popup_bubble_image, null);
        mTvBubble = (TextView) inflate.findViewById(R.id.tv_bubble);
        mIvBubble = (ImageView) inflate.findViewById(R.id.iv_bubble);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();

        mTvBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(mContext, "mTvBubble--->");
            }
        });
        mIvBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(mContext, "mIvBubble--->");
            }
        });
    }
}
