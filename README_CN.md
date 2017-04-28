# FlycoDialog-Master
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-FlycoDialog--Matser-green.svg?style=flat)](https://android-arsenal.com/details/1/2430)

一个强大的Android对话框库,简化自定义对话框.支持2.2+.

## 特点
- [内置Dialog,方便直接使用](#内置Dialog)
- [丰富的内置动画库,方便直接使用](#丰富的内置动画库)
- [支持快速自定义Dialog](#如何快速自定义Dialog)
- [支持快速自定义Popup](#如何快速自定义Popup)
- [支持自定义Dialog动画](#自定义Dialog动画)

#### [DemoApk下载](http://fir.im/mj9p)

## <a name="内置Dialog"></a>内置Dialog
|对话框|描述|截图|gif动画
|:---:|:---:|:---:|:---:|
| NormalDialog | 默认(两个按钮) | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_1.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_1.gif)
| NormalDialog | 第二种风格 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_2.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_2.gif)
| NormalDialog | 自定义属性 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_3.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_3.gif)
| NormalDialog | 一个按钮 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_4.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_4.gif)
| NormalDialog | 三个按钮 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_5.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_5.gif)
| MaterialDialog | 默认(两个按钮) | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/material_dialog_1.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_6.gif)
| MaterialDialog | 一个按钮 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/material_dialog_2.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_7.gif)
| MaterialDialog | 三个按钮 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/material_dialog_3.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_8.gif)
| NormalListDialog | 默认 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_list_1.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_9.gif)
| NormalListDialog | 自定义属性 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_list_2.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_10.gif)
| NormalListDialog | 无标题 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_list_3.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_11.gif)
| ActionSheetDialog | 默认 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/action_sheet_1.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_12.gif)
| ActionSheetDialog | 无标题 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/action_sheet_2.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_13.gif)

## 内置Popup
|弹窗|描述|截图|gif动画
|---|---|---|---|
| 弹窗 | 带三角箭头的提示弹窗 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/bubble_popup.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_popup_1.gif)

>## 更新说明

 > v1.3.0(2015-05-21)
   - 删除了对NineOldAnimation库依赖(仅支持3.0+)

 > v1.2.6(2015-01-07)
   - 将FlycoAnimation_Lib库合并到FlycoDialog_Lib中
   - 修复bug,在Popup中的View设置点击监听无效
   - 完善BaseBubblePopup功能
   
 > v1.2.2(2015-12-20)
   - BasePopup 小bug修复
 
 > v1.2.0(2015-12-19)
  - 新增基类BasePopup,用于快速自定义Popwindow样式Dialog
  - 新增内置控件BubblePopup
  - 新增支持Dialog自动消失技能

## <a name="如何快速自定义Dialog"></a>如何快速自定义Dialog
> 
  - 步骤一:继承BaseDialog(或者BottomBaseDialog或者TopBaseDialog)
  - 步骤二:在onCreateView方法填充布局和查找控件
  - 步骤三:在setUiBeforShow方法中做一些逻辑操作,例如设置数据,设置监听之类
  
  ```Java
  public class CustomBaseDialog extends BaseDialog<CustomBaseDialog> {
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
  
## <a name="如何快速自定义Popup"></a>如何快速自定义Popup
> 
  - 步骤一:继承BasePopup
  - 步骤二:在onCreatePopupView方法填充布局和查找控件
  - 步骤三:在setUiBeforShow方法中做一些逻辑操作,例如设置数据,设置监听之类
  
  ```java
  public class SimpleCustomPop extends BasePopup<SimpleCustomPop> {
          public SimpleCustomPop(Context context) {
              super(context);
          }
  
          @Override
          public View onCreatePopupView() {
              return View.inflate(mContext, R.layout.popup_custom, null);
          }
  
          @Override
          public void setUiBeforShow() {
  
          }
      }
  ```
  
## Gradle

```groovy
dependencies{
     compile 'com.flyco.dialog:FlycoDialog_Lib:1.2.2@aar'
     compile 'com.flyco.animation:FlycoAnimation_Lib:1.0.0@aar'
     compile 'com.nineoldandroids:library:2.4.0'
}


After v1.2.6
dependencies{
     compile 'com.flyco.dialog:FlycoDialog_Lib:1.2.8@aar'
     compile 'com.nineoldandroids:library:2.4.0'
}

After v1.3.0
dependencies{
     compile 'com.flyco.dialog:FlycoDialog_Lib:1.3.2@aar'
}

```

## Eclispe(不再维护更新)
Eclipse Developers should include jars below into your project.
*   [FlycoAnimation_Lib-v1.0.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/v1.0.0/FlycoAnimation_Lib-v1.0.0.jar)
*   [FlycoDialog_Lib-v1.0.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/v1.0.0/FlycoDialog_Lib-v1.0.0.jar)
*   [nineoldandroids-2.4.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/nineoldandroids-2.4.0.jar)

## Thanks
*   [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids)
*   [NiftyDialogEffects](https://github.com/sd6352051/NiftyDialogEffects)
*   [AndroidViewAnimations](https://github.com/daimajia/AndroidViewAnimations)
