#FlycoDialog-Master
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-FlycoDialog--Matser-green.svg?style=flat)](https://android-arsenal.com/details/1/2430)
####[中文版](https://github.com/H07000223/FlycoDialog_Master/blob/master/README_CN.md)
An Android Dialog Lib simplify customization. Supprot 2.2+.

##Features
- [Built-in Dialog, convenient to use](#Built-in Dialog)
- [Abundant Built-in Animations, convenient to use](#Abundant Built-in Animations)
- [Qucik Customize Dialog](#Qucik Customize Dialog)
- [Qucik Customize Popup](#Qucik Customize Popup)
- [Support Customize Dialog Animation](#Customize Dialog Animation)

####[DemoApk Download](http://fir.im/mj9p)

## <a name="Built-in Dialog"></a>Built-in Dialog
|Dialog|Description|ScreenShot|gif
|:---:|:---:|:---:|:---:|
| NormalDialog | Default(Two Btns) | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_1.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_1.gif)
| NormalDialog | Style Two | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_2.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_2.gif)
| NormalDialog | Custom Attr | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_3.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_3.gif)
| NormalDialog | One Btn | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_4.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_4.gif)
| NormalDialog | Three Btns | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_5.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_5.gif)
| MaterialDialog | Default(Two Btns) | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/material_dialog_1.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_6.gif)
| MaterialDialog | One Btn | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/material_dialog_2.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_7.gif)
| MaterialDialog | Three Btns | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/material_dialog_3.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_8.gif)
| NormalListDialog | Default | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_list_1.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_9.gif)
| NormalListDialog | Custom Attr | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_list_2.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_10.gif)
| NormalListDialog | No Title| <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_list_3.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_11.gif)
| ActionSheetDialog | Default | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/action_sheet_1.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_12.gif)
| ActionSheetDialog | No Title | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/action_sheet_2.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_13.gif)

##Built-in Popup
|Popup|Description|ScreenShot|gif
|:---:|:---:|:---:|:---:|
| BubblePopup | BubblePopup | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/bubble_popup.png" width="250"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_popup_1.gif)

>##Change Log
 > v1.2.2(2015-12-20)
   - BasePopup small bug fix
 
 > v1.2.0(2015-12-19)
   - new added base widget - BasePopup
   - new added built-in widget - BubblePopup
   - BaseDialog support auto dimiss in given delay

## <a name="Qucik Customize Dialog"></a>Qucik Customize Dialog
> 
  - step1:extends BaseDialog(or BottomBaseDialog or TopBaseDialog)
  - step2:inflate layout and find views in onCreateView method
  - step3:do logic operation in setUiBeforShow method
  
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
  
## <a name="Qucik Customize Popup"></a>Qucik Customize Popup
> 
  - step1:extends BasePopup
  - step2:inflate layout and find views in onCreatePopupView method
  - step3:do logic operation in setUiBeforShow method
  
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
  
##Gradle

```groovy
dependencies{
     compile 'com.flyco.dialog:FlycoDialog_Lib:1.2.2@aar'
     compile 'com.flyco.animation:FlycoAnimation_Lib:1.0.0@aar'
     compile 'com.nineoldandroids:library:2.4.0'
}
```

##Eclispe(no update)
Eclipse Developers should include jars below into your project.
*   [FlycoAnimation_Lib-v1.0.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/v1.0.0/FlycoAnimation_Lib-v1.0.0.jar)
*   [FlycoDialog_Lib-v1.0.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/v1.0.0/FlycoDialog_Lib-v1.0.0.jar)
*   [nineoldandroids-2.4.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/nineoldandroids-2.4.0.jar)

##Thanks
*   [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids)
*   [NiftyDialogEffects](https://github.com/sd6352051/NiftyDialogEffects)
*   [AndroidViewAnimations](https://github.com/daimajia/AndroidViewAnimations)
