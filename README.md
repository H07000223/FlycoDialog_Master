#FlycoDialog-Matser

This is an Android Dialog Library that you can directly use it's default inner dialog and default animation
and it also support both custom dialog and animation by self.

##Demo


##Gradle

```groovy
dependencies{
    compile 'com.flyco.dialog:FlycoDialog_Lib:1.0.0'
}
```

##Usage
###Inner Default Dialog

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


###Custom Dialog
