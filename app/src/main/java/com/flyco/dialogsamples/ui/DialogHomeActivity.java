package com.flyco.dialogsamples.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.flyco.dialogsamples.R;
import com.flyco.dialogsamples.adapter.HomeAdapter;
import com.flyco.dialogsamples.adapter.TestAdapter;
import com.flyco.dialogsamples.extra.CustomBaseDialog;
import com.flyco.dialogsamples.extra.IOSTaoBaoDialog;
import com.flyco.dialogsamples.extra.ShareBottomDialog;
import com.flyco.dialogsamples.extra.ShareTopDialog;
import com.flyco.dialogsamples.utils.DiaogAnimChoose;
import com.flyco.dialogsamples.utils.T;
import com.flyco.dialogsamples.utils.ViewFindUtils;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.animation.FlipEnter.FlipVerticalSwingEnter;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalListDialog;
import com.flyco.dialog.widget.NormalTipDialog;

import java.util.ArrayList;

public class DialogHomeActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener {
    private Context context = this;
    public static String[] groups = {"Default Inner Dialog", "Custom Dialog", "Default Inner Anim", "Custom Anim"};
    public static String[][] childs = {
            /**Default Inner Dialog*/
            {
                    "NormalDialog StyleOne",                    //0
                    "NormalDialog StyleTwo",                    //1
                    "NormalDialog Custom Attr",                 //2
                    "NormalTipDialog StyleOne",                 //3
                    "NormalTipDialog StyleTwo",                 //4
                    "MaterialDialogDefault Default(two btns)",  //5
                    "MaterialDialogDefault(one btn)",           //6
                    "MaterialDialogDefault(three btns)",        //7
                    "NormalListDialog",                         //8
                    "NormalListDialog Custom Attr",             //10
                    "NormalListDialog No Title",                //11
                    "NormalListDialog DataSource String[]",     //12
                    "NormalListDialog DataSource Adapter",      //13
                    "ActionSheetDialog",                        //14
                    "ActionSheetDialog NoTitle"                 //15
            },
            /**Custom Dialog*/
            {
                    "Custome Dialog extends BaseDialog", "Custome Dialog extends BottomBaseDialog", "Custome Dialog extends TopBaseDialog"
            },
            /**Default Inner Animation*/
            {
                    "Show Anim", "Dismiss Anim"
            },
            /**Custom Anim*/
            {
                    "Custom Anim like ios taobao"
            }
    };
    private ArrayList<DialogMenuItem> testItems = new ArrayList<>();
    private String[] stringItems = {"收藏", "下载", "分享", "删除", "歌手", "专辑"};
    private BaseAnimatorSet bas_in;
    private BaseAnimatorSet bas_out;
    private ExpandableListView elv;


    public void setBasIn(BaseAnimatorSet bas_in) {
        this.bas_in = bas_in;
    }

    public void setBasOut(BaseAnimatorSet bas_out) {
        this.bas_out = bas_out;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ac_dialog_home);

        testItems.add(new DialogMenuItem("收藏", R.mipmap.ic_winstyle_favor));
        testItems.add(new DialogMenuItem("下载", R.mipmap.ic_winstyle_download));
        testItems.add(new DialogMenuItem("分享", R.mipmap.ic_winstyle_share));
        testItems.add(new DialogMenuItem("删除", R.mipmap.ic_winstyle_delete));
        testItems.add(new DialogMenuItem("歌手", R.mipmap.ic_winstyle_artist));
        testItems.add(new DialogMenuItem("专辑", R.mipmap.ic_winstyle_album));

        bas_in = new FlipVerticalSwingEnter();
        bas_out = new FadeExit();


        View decorView = getWindow().getDecorView();
        elv = ViewFindUtils.find(decorView, R.id.elv);
        HomeAdapter adapter = new HomeAdapter(context);
        elv.setAdapter(adapter);
        // extend all group
        for (int i = 0; i < groups.length; i++) {
            elv.expandGroup(i);
        }

        elv.setOnChildClickListener(this);
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        /**Default Inner Dialog*/
        if (groupPosition == 0) {
            if (childPosition == 0) {
                NormalDialogStyleOne();
            } else if (childPosition == 1) {
                NormalDialogStyleTwo();
            } else if (childPosition == 2) {
                NormalDialogCustomAttr();
            } else if (childPosition == 3) {
                NormalTipDialogStyleOne();
            } else if (childPosition == 4) {
                NormalTipDialogStyleTwo();
            } else if (childPosition == 5) {
                MaterialDialogDefault();
            } else if (childPosition == 6) {
                MaterialDialogOneBtn();
            } else if (childPosition == 7) {
                MaterialDialogThreeBtns();
            } else if (childPosition == 8) {
                NormalListDialog();
            } else if (childPosition == 9) {
                NormalListDialogCustomAttr();
            } else if (childPosition == 10) {
                NormalListDialogNoTitle();
            } else if (childPosition == 11) {
                NormalListDialogStringArr();
            } else if (childPosition == 12) {
                NormalListDialogAdapter();
            } else if (childPosition == 13) {
                ActionSheetDialog();
            } else if (childPosition == 14) {
                ActionSheetDialogNoTitle();
            }
        }
        /**Custom Dialog*/
        if (groupPosition == 1) {
            if (childPosition == 0) {
                final CustomBaseDialog dialog = new CustomBaseDialog(context);
                dialog.show();
            } else if (childPosition == 1) {
                final ShareBottomDialog dialog = new ShareBottomDialog(context, elv);
                dialog.showAnim(bas_in)//
                        .show();//
                // .show(0, 100);
            } else if (childPosition == 2) {
                final ShareTopDialog dialog = new ShareTopDialog(context);
                dialog.showAnim(bas_in)//
                        .show();
                // .show(0, 100);
            }
        }
        /**Default Inner Anim*/
        if (groupPosition == 2) {
            if (childPosition == 0) {
                DiaogAnimChoose.showAnim(context);
            } else if (childPosition == 1) {
                DiaogAnimChoose.dissmissAnim(context);
            }
        }
        /**Custom Anim*/
        if (groupPosition == 3) {
            if (childPosition == 0) {
                final IOSTaoBaoDialog dialog = new IOSTaoBaoDialog(context, (View) elv.getParent());
                dialog.show();
                // .show(0, 100);
            }
        }
        return false;
    }

    private void NormalDialogStyleOne() {
        final NormalDialog dialog = new NormalDialog(context);
        dialog.content("是否确定退出程序?")//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "left");
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "right");
                        dialog.dismiss();
                    }
                });
    }

    private void NormalDialogStyleTwo() {
        final NormalDialog dialog = new NormalDialog(context);
        dialog.content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23)//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "left");
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "right");
                        dialog.dismiss();
                    }
                });

    }

    private void NormalDialogCustomAttr() {
        final NormalDialog dialog = new NormalDialog(context);
        dialog.isTitleShow(false)//
                .bgColor(Color.parseColor("#383838"))//
                .cornerRadius(5)//
                .content("是否确定退出程序?")//
                .contentGravity(Gravity.CENTER)//
                .contentTextColor(Color.parseColor("#ffffff"))//
                .dividerColor(Color.parseColor("#222222"))//
                .btnTextSize(15.5f, 15.5f)//
                .btnTextColor(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"))//
                .btnPressColor(Color.parseColor("#2B2B2B"))//
                .widthScale(0.85f)//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "left");
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "right");
                        dialog.dismiss();
                    }
                });
    }

    private void NormalTipDialogStyleOne() {
        final NormalTipDialog dialog = new NormalTipDialog(context);
        dialog.content("你今天的抢购名额已用完~")//
                .btnText("继续逛逛")//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.dismiss();
            }
        });
    }

    private void NormalTipDialogStyleTwo() {
        final NormalTipDialog dialog = new NormalTipDialog(context);
        dialog.content("你今天的抢购名额已用完~")//
                .style(NormalTipDialog.STYLE_TWO)//
                .btnText("继续逛逛")//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.dismiss();
            }
        });
    }

    private void MaterialDialogDefault() {
        final MaterialDialog dialog = new MaterialDialog(context);
        dialog.content(
                "嗨！这是一个 MaterialDialogDefault. 它非常方便使用，你只需将它实例化，这个美观的对话框便会自动地显示出来。"
                        + "它简洁小巧，完全遵照 Google 2014 年发布的 Material Design 风格，希望你能喜欢它！^ ^")//
                .btnText("取消", "确定")//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//left btn click listener
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "left");
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//right btn click listener
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "right");
                        dialog.dismiss();
                    }
                }
        );
    }


    private void MaterialDialogThreeBtns() {
        final MaterialDialog dialog = new MaterialDialog(context);
        dialog.isTitleShow(false)//
                .btnNum(3)
                .content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。")//
                .btnText("确定", "取消", "知道了")//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//left btn click listener
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "left");
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//right btn click listener
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "right");
                        dialog.dismiss();
                    }
                }
                ,
                new OnBtnClickL() {//middle btn click listener
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "middle");
                        dialog.dismiss();
                    }
                }
        );
    }

    private void MaterialDialogOneBtn() {
        final MaterialDialog dialog = new MaterialDialog(context);
        dialog//
                .btnNum(1)
                .content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。")//
                .btnText("确定")//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                T.showShort(context, "middle");
                dialog.dismiss();
            }
        });
    }

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(context, testItems);
        dialog.title("请选择")//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(context, testItems.get(position).operName);
                dialog.dismiss();
            }
        });
    }


    private void NormalListDialogCustomAttr() {
        final NormalListDialog dialog = new NormalListDialog(context, testItems);
        dialog.title("请选择")//
                .titleTextSize_SP(18)//
                .titleBgColor(Color.parseColor("#409ED7"))//
                .itemPressColor(Color.parseColor("#85D3EF"))//
                .itemTextColor(Color.parseColor("#303030"))//
                .itemTextSize(14)//
                .cornerRadius(0)//
                .widthScale(0.8f)//
                .show(R.style.myDialogAnim);

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(context, testItems.get(position).operName);
                dialog.dismiss();
            }
        });
    }


    private void NormalListDialogNoTitle() {
        final NormalListDialog dialog = new NormalListDialog(context, testItems);
        dialog.title("请选择")//
                .isTitleShow(false)//
                .itemPressColor(Color.parseColor("#85D3EF"))//
                .itemTextColor(Color.parseColor("#303030"))//
                .itemTextSize(15)//
                .cornerRadius(2)//
                .widthScale(0.75f)//
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(context, testItems.get(position).operName);
                dialog.dismiss();
            }
        });
    }

    private void NormalListDialogStringArr() {
        final NormalListDialog dialog = new NormalListDialog(context, stringItems);
        dialog.title("请选择")//
                .layoutAnimation(null)
                .show(R.style.myDialogAnim);
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(context, testItems.get(position).operName);
                dialog.dismiss();
            }
        });
    }

    private void NormalListDialogAdapter() {
        final NormalListDialog dialog = new NormalListDialog(context, new TestAdapter(context, testItems));
        dialog.title("请选择")//
                .show(R.style.myDialogAnim);
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(context, testItems.get(position).operName);
                dialog.dismiss();
            }
        });
    }


    private void ActionSheetDialog() {
        final String[] stringItems = {"接收消息并提醒", "接收消息但不提醒", "收进群助手且不提醒", "屏蔽群消息"};
        final ActionSheetDialog dialog = new ActionSheetDialog(context, stringItems, null);
        dialog.title("选择群消息提醒方式\r\n(该群在电脑的设置:接收消息并提醒)")//
                .titleTextSize_SP(14.5f)//
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(context, stringItems[position]);
                dialog.dismiss();
            }
        });
    }

    private void ActionSheetDialogNoTitle() {
        final String[] stringItems = {"版本更新", "帮助与反馈", "退出QQ"};
        final ActionSheetDialog dialog = new ActionSheetDialog(context, stringItems, elv);
        dialog.isTitleShow(false).show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                T.showShort(context, stringItems[position]);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        final NormalDialog dialog = new NormalDialog(context);
        dialog.content("亲,真的要走吗?再看会儿吧~(●—●)")//
                .style(NormalDialog.STYLE_TWO)//
                .titleTextSize(23)//
                .btnText("继续逛逛", "残忍退出")//
                .btnTextColor(Color.parseColor("#383838"), Color.parseColor("#D4D4D4"))//
                .btnTextSize(16f, 16f)//
                .showAnim(bas_in)//
                .dismissAnim(bas_out)//
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "left");
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        T.showShort(context, "right");
                        dialog.dismiss();
                    }
                });
    }
}