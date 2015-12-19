package com.flyco.dialogsamples.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.flyco.dialogsamples.R;
import com.flyco.dialogsamples.ui.DialogHomeActivity;
import com.flyco.dialogsamples.utils.ViewFindUtils;


public class HomeAdapter extends BaseExpandableListAdapter {
    private Context mContext;

    public HomeAdapter(Context context) {
        this.mContext = context;
    }

    // --->group
    @Override
    public int getGroupCount() {
        return DialogHomeActivity.mGroups.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return DialogHomeActivity.mGroups[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.ad_dialog_home, null);
        }

        TextView tv = ViewFindUtils.find(convertView, R.id.tv);
        tv.setText(DialogHomeActivity.mGroups[groupPosition]);
        return convertView;
    }

    // --->child
    @Override
    public int getChildrenCount(int groupPosition) {
        return DialogHomeActivity.mChilds[groupPosition].length;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return DialogHomeActivity.mChilds[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.ad_dialog_home, null);
        }

        TextView tv = ViewFindUtils.find(convertView, R.id.tv);
        View v_line = ViewFindUtils.find(convertView, R.id.v_line);

        v_line.setVisibility(View.INVISIBLE);
        tv.setTextColor(Color.parseColor("#383838"));
        tv.setText(DialogHomeActivity.mChilds[groupPosition][childPosition]);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
