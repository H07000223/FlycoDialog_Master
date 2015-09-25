#FlycoDialog-Matser
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-FlycoDialog--Matser-green.svg?style=flat)](https://android-arsenal.com/details/1/2430)

A powerful android dialog library with inner widgets and animations supports customization.Easy to use, easy to customise. Support for Android 2.2 and up. 

##Demo
![](https://github.com/H07000223/FlycoDialog_Master/blob/master/preview_FlycoDialog.gif)

####[Here is a DemoApk download](http://pkg.fir.im/0793e0044c968f6dfeb0be8ccfe15c14e0621694?attname=FlycoDialogSamples.apk_1.0.0.apk&e=1442478927&token=LOvmia8oXF4xnLh0IdH05XMYpH6ENHNpARlmPc-T:ari5WusSPG5Lt4M7QmjwMepUb18=)

##Gradle

```groovy
dependencies{
     compile 'com.flyco.dialog:FlycoDialog_Lib:1.0.2@aar'
     compile 'com.flyco.animation:FlycoAnimation_Lib:1.0.0@aar'
     compile 'com.nineoldandroids:library:2.4.0'
}
```

##Eclispe
Eclipse Developers should include jars below into your project.
*   [FlycoAnimation_Lib-v1.0.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/v1.0.0/FlycoAnimation_Lib-v1.0.0.jar)
*   [FlycoDialog_Lib-v1.0.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/v1.0.0/FlycoDialog_Lib-v1.0.0.jar)
*   [nineoldandroids-2.4.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/nineoldandroids-2.4.0.jar)

##Thanks
*   [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids)
*   [NiftyDialogEffects](https://github.com/sd6352051/NiftyDialogEffects)
*   [AndroidViewAnimations](https://github.com/daimajia/AndroidViewAnimations)

##Usage
###Inner Default Dialog(默认内置自带Dialog)

Here's the dialog usages corresponding to the demo listview item sort.(下面dialog使用方法和Demo中的列表顺序对应)
```Java

    BaseAnimatorSet bas_in = new FlipVerticalSwingEnter();
    BaseAnimatorSet bas_out = new FadeExit();
    private void NormalDialogStyleOne() {
            final NormalDialog dialog = new NormalDialog(context);
            dialog.content("是否确定退出程序?")//
                    .showAnim(bas_in)//
                    .dismissAnim(bas_out)//
                    .show();

            dialog.setOnBtnLeftClickL(new OnBtnLeftClickL() {
                @Override
                public void onBtnLeftClick() {
                    T.showShort(context, "onBtnLeftClick");
                    dialog.dismiss();
                }
            });

            dialog.setOnBtnRightClickL(new OnBtnRightClickL() {
                @Override
                public void onBtnRightClick() {
                    T.showShort(context, "onBtnRightClick");
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

            dialog.setOnBtnLeftClickL(new OnBtnLeftClickL() {
                @Override
                public void onBtnLeftClick() {
                    T.showShort(context, "onBtnLeftClick定");
                    dialog.dismiss();
                }
            });

            dialog.setOnBtnRightClickL(new OnBtnRightClickL() {
                @Override
                public void onBtnRightClick() {
                    T.showShort(context, "onBtnRightClick");
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
                    .btnColorPress(Color.parseColor("#2B2B2B"))//
                    .widthScale(0.85f)//
                    .showAnim(bas_in)//
                    .dismissAnim(bas_out)//
                    .show();

            dialog.setOnBtnLeftClickL(new OnBtnLeftClickL() {
                @Override
                public void onBtnLeftClick() {
                    T.showShort(context, "onBtnLeftClick");
                    dialog.dismiss();
                }
            });

            dialog.setOnBtnRightClickL(new OnBtnRightClickL() {
                @Override
                public void onBtnRightClick() {
                    T.showShort(context, "onBtnRightClick");
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

        private void MaterialDialog() {
            final MaterialDialog dialog = new MaterialDialog(context);
            dialog.content(
                    "嗨！这是一个 MaterialDialog. 它非常方便使用，你只需将它实例化，这个美观的对话框便会自动地显示出来。"
                            + "它简洁小巧，完全遵照 Google 2014 年发布的 Material Design 风格，希望你能喜欢它！^ ^")//
                    .btnText("取消", "确定")//
                    .showAnim(bas_in)//
                    .dismissAnim(bas_out)//
                    .show();

            dialog.setOnBtnLeftClickL(new OnBtnLeftClickL() {
                @Override
                public void onBtnLeftClick() {
                    T.showShort(context, "onBtnLeftClick");
                    dialog.dismiss();
                }
            });

            dialog.setOnBtnRightClickL(new OnBtnRightClickL() {
                @Override
                public void onBtnRightClick() {
                    T.showShort(context, "onBtnRightClick");
                    dialog.dismiss();
                }
            });
        }


        private void MaterialDialogNoTitle() {
            final MaterialDialog dialog = new MaterialDialog(context);
            dialog.isTitleShow(false)//
                    .content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。")//
                    .btnText("确定", "取消")//
                    .showAnim(bas_in)//
                    .dismissAnim(bas_out)//
                    .show();

            dialog.setOnBtnLeftClickL(new OnBtnLeftClickL() {
                @Override
                public void onBtnLeftClick() {
                    T.showShort(context, "onBtnLeftClick");
                    dialog.dismiss();
                }
            });

            dialog.setOnBtnRightClickL(new OnBtnRightClickL() {
                @Override
                public void onBtnRightClick() {
                    T.showShort(context, "onBtnRightClick");
                    dialog.dismiss();
                }
            });
        }

        private void MaterialTipDialog() {
            final MaterialTipDialog dialog = new MaterialTipDialog(context);
            dialog//
                    // .isTitleShow(false)//
                    .content("为保证咖啡豆的新鲜度和咖啡的香味，并配以特有的传统烘焙和手工冲。")//
                    .btnText("确定")//
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
```


###Custom Dialog(自定义Dialog)

*   extends BaseDialog(继承BaseDialog)
*   create layout and find views in method onCreateView(在onCreateView方法填充布局和查找控件)
*   do some logic operation in method setUiBeforeShow , return false go on to show dialog else return true don't(在setUiBeforShow方法中做一些逻辑操作,返回值false显示dialog,返回值true不显示)

```Java
public class CustomBaseDialog extends BaseDialog {
    private TextView tv_cancel;
    private TextView tv_exit;

    public CustomBaseDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        widthScale(0.85f);
        showAnim(new Swing());

        // dismissAnim(this, new ZoomOutExit());
        View inflate = View.inflate(context, R.layout.dialog_custom_base, null);
        tv_cancel = ViewFindUtils.find(inflate, R.id.tv_cancel);
        tv_exit = ViewFindUtils.find(inflate, R.id.tv_exit);
        inflate.setBackgroundDrawable(
                CornerUtils.cornerDrawable(Color.parseColor("#ffffff"), dp2px(5)));

        return inflate;
    }

    @Override
    public boolean setUiBeforShow() {
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return false;
    }
}
```

###Custom Animation(自定义动画)
Here is an example that custom animation in custom dialog. You can also just custom animation for showAnim() and dismissAnim() method.(下面例子是在自定Dialog内部中自定义动画,你也可以只是自定义动画给showAnim和dismissAnim方法调用)

```Java
public class IOSTaoBaoDialog extends BottomBaseDialog {
    private LinearLayout ll_wechat_friend_circle;
    private LinearLayout ll_wechat_friend;
    private LinearLayout ll_qq;
    private LinearLayout ll_sms;

    public IOSTaoBaoDialog(Context context, View animateView) {
        super(context, animateView);
    }

    public IOSTaoBaoDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        View inflate = View.inflate(context, R.layout.dialog_ios_taobao, null);
        ll_wechat_friend_circle = ViewFindUtils.find(inflate, R.id.ll_wechat_friend_circle);
        ll_wechat_friend = ViewFindUtils.find(inflate, R.id.ll_wechat_friend);
        ll_qq = ViewFindUtils.find(inflate, R.id.ll_qq);
        ll_sms = ViewFindUtils.find(inflate, R.id.ll_sms);

        return inflate;
    }

    @Override
    public boolean setUiBeforShow() {
        ll_wechat_friend_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(context, "朋友圈");
                dismiss();
            }
        });
        ll_wechat_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(context, "微信");
                dismiss();
            }
        });
        ll_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(context, "QQ");
                dismiss();
            }
        });
        ll_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(context, "短信");
                dismiss();
            }
        });

        return false;
    }

    private BaseAnimatorSet windowInAs;
    private BaseAnimatorSet windowOutAs;

    @Override
    protected BaseAnimatorSet getWindowInAs() {
        if (windowInAs == null) {
            windowInAs = new WindowsInAs();
        }
        return windowInAs;
    }

    @Override
    protected BaseAnimatorSet getWindowOutAs() {
        if (windowOutAs == null) {
            windowOutAs = new WindowsOutAs();
        }
        return windowOutAs;
    }

    class WindowsInAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f).setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.8f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.8f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", 0, -0.1f * dm.heightPixels).setDuration(350)
            );
        }
    }

    class WindowsOutAs extends BaseAnimatorSet {
        @Override
        public void setAnimation(View view) {
            ObjectAnimator rotationX = ObjectAnimator.ofFloat(view, "rotationX", 10, 0f).setDuration(150);
            rotationX.setStartDelay(200);
            animatorSet.playTogether(
                    ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f).setDuration(350),
//                    ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f).setDuration(350),
                    ObjectAnimator.ofFloat(view, "rotationX", 0f, 10).setDuration(200),
                    rotationX,
                    ObjectAnimator.ofFloat(view, "translationY", -0.1f * dm.heightPixels, 0).setDuration(350)
            );
        }
    }
```
