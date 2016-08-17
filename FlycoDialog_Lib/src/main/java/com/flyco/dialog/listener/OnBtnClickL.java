package com.flyco.dialog.listener;


import com.flyco.dialog.widget.internal.BaseAlertDialog;

public interface OnBtnClickL<T extends BaseAlertDialog> {
	void onBtnClick(T t);
}
