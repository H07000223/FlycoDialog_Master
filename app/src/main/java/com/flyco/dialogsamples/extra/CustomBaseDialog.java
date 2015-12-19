package com.flyco.dialogsamples.extra;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.flyco.dialogsamples.R;
import com.flyco.dialogsamples.utils.ViewFindUtils;
import com.flyco.animation.Attention.Swing;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BaseDialog;

public class CustomBaseDialog extends BaseDialog<CustomBaseDialog> {
    private TextView mTvCancel;
    private TextView mTvExit;

    public CustomBaseDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        showAnim(new Swing());

        // dismissAnim(this, new ZoomOutExit());
        View inflate = View.inflate(mContext, R.layout.dialog_custom_base, null);
        mTvCancel = ViewFindUtils.find(inflate, R.id.tv_cancel);
        mTvExit = ViewFindUtils.find(inflate, R.id.tv_exit);
        inflate.setBackgroundDrawable(
                CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), dp2px(5)));

        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mTvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
