package com.flyco.dialogsamples.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.entity.DialogMenuItem;

import java.util.ArrayList;

public class TestAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<DialogMenuItem> mMenuItems;
	private DisplayMetrics dm;

	public TestAdapter(Context context, ArrayList<DialogMenuItem> customBaseItems) {
		this.mContext = context;
		this.mMenuItems = customBaseItems;

		dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
	}

	@Override
	public int getCount() {
		return mMenuItems.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final DialogMenuItem item = mMenuItems.get(position);

		LinearLayout ll_item = new LinearLayout(mContext);
		ll_item.setOrientation(LinearLayout.HORIZONTAL);
		ll_item.setGravity(Gravity.CENTER_VERTICAL);

		ImageView iv_item = new ImageView(mContext);
		iv_item.setPadding(0, 0, (int) (15 * dm.density), 0);
		ll_item.addView(iv_item);

		TextView tv_item = new TextView(mContext);
		tv_item.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		tv_item.setSingleLine(true);
		tv_item.setTextColor(Color.parseColor("#303030"));
		tv_item.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

		ll_item.addView(tv_item);
		ll_item.setPadding(item.mResId == 0 ? (int) (18 * dm.density) : (int) (16 * dm.density), (int) (10 * dm.density), 0,
				(int) (10 * dm.density));

		iv_item.setImageResource(item.mResId);
		tv_item.setText(item.mOperName);
		iv_item.setVisibility(item.mResId == 0 ? View.GONE : View.VISIBLE);

		return ll_item;
	}
}