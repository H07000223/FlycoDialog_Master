package com.flyco.dialog.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BottomBaseDialog;

import java.util.ArrayList;

/***
 * Dialog like iOS ActionSheet(iOS风格对话框)
 */
public class ActionSheetDialog extends BottomBaseDialog {
    /**
     * ListView
     */
    private ListView lv;
    /**
     * title
     */
    private TextView tv_title;
    /**
     * title underline(标题下划线)
     */
    private View v_line_title;
    /**
     * cancel button(取消按钮)
     */
    private TextView tv_cancel;
    /**
     * corner radius,dp(圆角程度,单位dp)
     */
    private float cornerRadius_DP = 5;
    /**
     * title background color(标题背景颜色)
     */
    private int titleBgColor = Color.parseColor("#ddffffff");
    /**
     * title text(标题)
     */
    private String title = "提示";
    /**
     * title height(标题栏高度)
     */
    private float titleHeight = 48;
    /**
     * title textcolor(标题颜色)
     */
    private int titleTextColor = Color.parseColor("#8F8F8F");
    /**
     * title textsize(标题字体大小,单位sp)
     */
    private float titleTextSize_SP = 17.5f;
    /**
     * ListView background color(ListView背景色)
     */
    private int lvBgColor = Color.parseColor("#ddffffff");
    /**
     * divider color(ListView divider颜色)
     */
    private int dividerColor = Color.parseColor("#D7D7D9");
    /**
     * divider height(ListView divider高度)
     */
    private float dividerHeight_DP = 0.8f;
    /**
     * item press color(ListView item按住颜色)
     */
    private int itemPressColor = Color.parseColor("#ffcccccc");
    /**
     * item textcolor(ListView item文字颜色)
     */
    private int itemTextColor = Color.parseColor("#44A2FF");
    /**
     * item textsize(ListView item文字大小)
     */
    private float itemTextSize_SP = 17.5f;
    /**
     * item height(ListView item高度)
     */
    private float itemHeight_DP = 48;
    /**
     * enable title show(是否显示标题)
     */
    private boolean isTitleShow = true;
    /**
     * adapter(自定义适配器)
     */
    private BaseAdapter adapter;
    /**
     * operation items(操作items)
     */
    private ArrayList<DialogMenuItem> contents = new ArrayList<>();
    private OnOperItemClickL onOperItemClickL;
    private LayoutAnimationController lac;

    public void setOnOperItemClickL(OnOperItemClickL onOperItemClickL) {
        this.onOperItemClickL = onOperItemClickL;
    }

    public ActionSheetDialog(Context context, ArrayList<DialogMenuItem> baseItems, View animateView) {
        super(context, animateView);
        this.contents.addAll(baseItems);
        init();
    }

    public ActionSheetDialog(Context context, String[] items, View animateView) {
        super(context, animateView);
        this.contents = new ArrayList<>();
        for (String item : items) {
            DialogMenuItem customBaseItem = new DialogMenuItem(item, 0);
            contents.add(customBaseItem);
        }
        init();
    }

    public ActionSheetDialog(Context context, BaseAdapter adapter, View animateView) {
        super(context, animateView);
        this.adapter = adapter;
        init();
    }

    private void init() {
        widthScale(0.95f);
        /** LayoutAnimation */
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                0f, Animation.RELATIVE_TO_SELF, 6f, Animation.RELATIVE_TO_SELF, 0);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(350);
        animation.setStartOffset(150);

        lac = new LayoutAnimationController(animation, 0.12f);
        lac.setInterpolator(new DecelerateInterpolator());
    }

    @Override
    public View onCreateView() {


        LinearLayout ll_container = new LinearLayout(context);
        ll_container.setOrientation(LinearLayout.VERTICAL);
        ll_container.setBackgroundColor(Color.TRANSPARENT);

        /** title */
        tv_title = new TextView(context);
        tv_title.setGravity(Gravity.CENTER);
        tv_title.setPadding(dp2px(10), dp2px(5), dp2px(10), dp2px(5));

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.topMargin = dp2px(20);

        ll_container.addView(tv_title, params);

        /** title underline */
        v_line_title = new View(context);
        ll_container.addView(v_line_title);

        /** listview */
        lv = new ListView(context);
        lv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1));
        lv.setCacheColorHint(Color.TRANSPARENT);
        lv.setFadingEdgeLength(0);
        lv.setVerticalScrollBarEnabled(false);
        lv.setSelector(new ColorDrawable(Color.TRANSPARENT));

        ll_container.addView(lv);

        /** cancel btn */
        tv_cancel = new TextView(context);
        tv_cancel.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.topMargin = dp2px(7);
        lp.bottomMargin = dp2px(7);
        tv_cancel.setLayoutParams(lp);

        ll_container.addView(tv_cancel);

        return ll_container;
    }

    @Override
    public void setUiBeforShow() {
        /** title */
        float radius = dp2px(cornerRadius_DP);
        tv_title.setHeight(dp2px(titleHeight));
        tv_title.setBackgroundDrawable(CornerUtils.cornerDrawable(titleBgColor, new float[]{radius, radius, radius,
                radius, 0, 0, 0, 0}));
        tv_title.setText(title);
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTextSize_SP);
        tv_title.setTextColor(titleTextColor);
        tv_title.setVisibility(isTitleShow ? View.VISIBLE : View.GONE);

        /** title underline */
        v_line_title.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, dp2px(dividerHeight_DP)));
        v_line_title.setBackgroundColor(dividerColor);
        v_line_title.setVisibility(isTitleShow ? View.VISIBLE : View.GONE);

        /** cancel btn */
        tv_cancel.setHeight(dp2px(itemHeight_DP));
        tv_cancel.setText("取消");
        tv_cancel.setTextSize(TypedValue.COMPLEX_UNIT_SP, itemTextSize_SP);
        tv_cancel.setTextColor(itemTextColor);
        tv_cancel.setBackgroundDrawable(CornerUtils.listItemSelector(radius, lvBgColor, itemPressColor, 1, 0));

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        /** listview */
        lv.setDivider(new ColorDrawable(dividerColor));
        lv.setDividerHeight(dp2px(dividerHeight_DP));

        if (isTitleShow) {
            lv.setBackgroundDrawable(CornerUtils.cornerDrawable(lvBgColor, new float[]{0, 0, 0, 0, radius, radius, radius,
                    radius}));
        } else {
            lv.setBackgroundDrawable(CornerUtils.cornerDrawable(lvBgColor, radius));
        }

        if (adapter == null) {
            adapter = new ListDialogAdapter();
        }

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onOperItemClickL != null) {
                    onOperItemClickL.onOperItemClick(parent, view, position, id);
                }
            }
        });

        lv.setLayoutAnimation(lac);
    }

    /**
     * set title background color(设置标题栏背景色)
     *
     * @param titleBgColor
     * @return ActionSheetDialog
     */
    public ActionSheetDialog titleBgColor(int titleBgColor) {
        this.titleBgColor = titleBgColor;
        return this;
    }

    /**
     * set title text(设置标题内容)
     *
     * @param title
     * @return ActionSheetDialog
     */
    public ActionSheetDialog title(String title) {
        this.title = title;
        return this;
    }

    /**
     * set titleHeight(设置标题高度)
     *
     * @param titleHeight
     * @return ActionSheetDialog
     */
    public ActionSheetDialog titleHeight(float titleHeight) {
        this.titleHeight = titleHeight;
        return this;
    }

    /**
     * set title textsize(设置标题字体大小)
     *
     * @param titleTextSize_SP
     * @return ActionSheetDialog
     */
    public ActionSheetDialog titleTextSize_SP(float titleTextSize_SP) {
        this.titleTextSize_SP = titleTextSize_SP;
        return this;
    }

    /**
     * set title textcolor(设置标题字体颜色)
     *
     * @param titleTextColor
     * @return ActionSheetDialog
     */
    public ActionSheetDialog titleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    /**
     * enable title show(设置标题是否显示)
     *
     * @param isTitleShow
     * @return ActionSheetDialog
     */
    public ActionSheetDialog isTitleShow(boolean isTitleShow) {
        this.isTitleShow = isTitleShow;
        return this;
    }

    /**
     * set ListView background color(设置ListView背景)
     *
     * @param lvBgColor
     * @return ActionSheetDialog
     */
    public ActionSheetDialog lvBgColor(int lvBgColor) {
        this.lvBgColor = lvBgColor;
        return this;
    }

    /**
     * set corner radius(设置圆角程度,单位dp)
     *
     * @param cornerRadius_DP
     * @return ActionSheetDialog
     */
    public ActionSheetDialog cornerRadius(float cornerRadius_DP) {
        this.cornerRadius_DP = cornerRadius_DP;
        return this;
    }

    /**
     * set divider color(ListView divider颜色)
     *
     * @param dividerColor
     * @return ActionSheetDialog
     */
    public ActionSheetDialog dividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        return this;
    }

    /**
     * set divider height(ListView divider高度)
     *
     * @return ActionSheetDialog
     */
    public ActionSheetDialog dividerHeight(float dividerHeight_DP) {
        this.dividerHeight_DP = dividerHeight_DP;
        return this;
    }

    /**
     * set item press color(item按住颜色)
     *
     * @param itemPressColor
     * @return ActionSheetDialog
     */
    public ActionSheetDialog itemPressColor(int itemPressColor) {
        this.itemPressColor = itemPressColor;
        return this;
    }

    /**
     * set item textcolor(item字体颜色)
     *
     * @param itemTextColor
     * @return ActionSheetDialog
     */
    public ActionSheetDialog itemTextColor(int itemTextColor) {
        this.itemTextColor = itemTextColor;
        return this;
    }

    /**
     * set item textsize(item字体大小)
     *
     * @param itemTextSize_SP
     * @return ActionSheetDialog
     */
    public ActionSheetDialog itemTextSize(float itemTextSize_SP) {
        this.itemTextSize_SP = itemTextSize_SP;
        return this;
    }

    /**
     * set item height(item高度)
     *
     * @param itemHeight_DP
     * @return ActionSheetDialog
     */
    public ActionSheetDialog itemHeight(float itemHeight_DP) {
        this.itemHeight_DP = itemHeight_DP;
        return this;
    }

    /**
     * set layoutAnimation(设置layout动画 ,传入null将不显示layout动画)
     *
     * @param lac
     * @return ActionSheetDialog
     */
    public ActionSheetDialog layoutAnimation(LayoutAnimationController lac) {
        this.lac = lac;
        return this;
    }

    class ListDialogAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return contents.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final DialogMenuItem item = contents.get(position);

            LinearLayout ll_item = new LinearLayout(context);
            ll_item.setOrientation(LinearLayout.HORIZONTAL);
            ll_item.setGravity(Gravity.CENTER_VERTICAL);

            ImageView iv_item = new ImageView(context);
            iv_item.setPadding(0, 0, dp2px(15), 0);
            ll_item.addView(iv_item);

            TextView tv_item = new TextView(context);
            tv_item.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            tv_item.setSingleLine(true);
            tv_item.setGravity(Gravity.CENTER);
            tv_item.setTextColor(itemTextColor);
            tv_item.setTextSize(TypedValue.COMPLEX_UNIT_SP, itemTextSize_SP);
            tv_item.setHeight(dp2px(itemHeight_DP));

            ll_item.addView(tv_item);
            float radius = dp2px(cornerRadius_DP);
            if (isTitleShow) {
                ll_item.setBackgroundDrawable((CornerUtils.listItemSelector(radius, Color.TRANSPARENT, itemPressColor,
                        position == contents.size() - 1)));
            } else {
                ll_item.setBackgroundDrawable(CornerUtils.listItemSelector(radius, Color.TRANSPARENT, itemPressColor,
                        contents.size(), position));
            }

            iv_item.setImageResource(item.resId);
            tv_item.setText(item.operName);
            iv_item.setVisibility(item.resId == 0 ? View.GONE : View.VISIBLE);

            return ll_item;
        }
    }
}
