#FlycoDialog-Master
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-FlycoDialog--Matser-green.svg?style=flat)](https://android-arsenal.com/details/1/2430)

一个强大的Android对话框库,简化自定义对话框.支持2.2+.

##特点
* [内置Dialog,方便直接使用](#内置Dialog)
* [支持快速自定义Dialog](#快速自定义Dialog)
* [支持自定义Dialog动画](#自定义Dialog动画)

####[DemoApk下载](http://fir.im/mj9p)

##内置Dialog
|对话框|描述|截图|gif动画
|:---:|:---:|:---:|:---:|
| NormalDialog | 默认(两个按钮) | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_1.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_1.gif)
| NormalDialog | 第二种风格 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_2.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_2.gif)
| NormalDialog | 自定义属性 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_3.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_3.gif)
| NormalDialog | 一个按钮 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_4.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_4.gif)
| NormalDialog | 三个按钮 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_dialog_5.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_5.gif)
| MaterialDialog | 默认(两个按钮) | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/material_dialog_1.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_6.gif)
| MaterialDialog | 一个按钮 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/material_dialog_2.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_7.gif)
| MaterialDialog | 三个按钮 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/material_dialog_3.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_8.gif)
| NormalListDialog | 默认 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_list_1.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_9.gif)
| NormalListDialog | 自定义属性 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_list_2.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_10.gif)
| NormalListDialog | 无标题 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/normal_list_3.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_11.gif)
| ActionSheetDialog | 默认 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/action_sheet_1.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_12.gif)
| ActionSheetDialog | 无标题 | <img src="https://github.com/H07000223/FlycoDialog_Master/blob/master/screenshot/action_sheet_2.png" width="320"> |[gif](https://github.com/H07000223/FlycoDialog_Master/blob/master/gif/preview_13.gif)


>## 更新说明

 > v1.2.0
  - 新增基类BasePopup,用于快速自定义Popwindow样式Dialog
  - 新增内置控件BubblePopup
  - 新增支持Dialog自动消失技能
  
##Gradle

```groovy
dependencies{
     compile 'com.flyco.dialog:FlycoDialog_Lib:1.2.0@aar'
     compile 'com.flyco.animation:FlycoAnimation_Lib:1.0.0@aar'
     compile 'com.nineoldandroids:library:2.4.0'
}
```

##Eclispe(不再维护更新)
Eclipse Developers should include jars below into your project.
*   [FlycoAnimation_Lib-v1.0.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/v1.0.0/FlycoAnimation_Lib-v1.0.0.jar)
*   [FlycoDialog_Lib-v1.0.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/v1.0.0/FlycoDialog_Lib-v1.0.0.jar)
*   [nineoldandroids-2.4.0.jar](https://github.com/H07000223/FlycoDialog_Master/blob/master/Jar/nineoldandroids-2.4.0.jar)

##Thanks
*   [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids)
*   [NiftyDialogEffects](https://github.com/sd6352051/NiftyDialogEffects)
*   [AndroidViewAnimations](https://github.com/daimajia/AndroidViewAnimations)
