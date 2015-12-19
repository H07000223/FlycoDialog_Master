package com.flyco.dialogsamples.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.flyco.dialogsamples.ui.DialogHomeActivity;
import com.flyco.animation.Attention.Flash;
import com.flyco.animation.Attention.RubberBand;
import com.flyco.animation.Attention.ShakeHorizontal;
import com.flyco.animation.Attention.ShakeVertical;
import com.flyco.animation.Attention.Swing;
import com.flyco.animation.Attention.Tada;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceBottomEnter;
import com.flyco.animation.BounceEnter.BounceEnter;
import com.flyco.animation.BounceEnter.BounceLeftEnter;
import com.flyco.animation.BounceEnter.BounceRightEnter;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.FadeEnter.FadeEnter;
import com.flyco.animation.FadeExit.FadeExit;
import com.flyco.animation.FallEnter.FallEnter;
import com.flyco.animation.FallEnter.FallRotateEnter;
import com.flyco.animation.FlipEnter.FlipBottomEnter;
import com.flyco.animation.FlipEnter.FlipHorizontalEnter;
import com.flyco.animation.FlipEnter.FlipHorizontalSwingEnter;
import com.flyco.animation.FlipEnter.FlipLeftEnter;
import com.flyco.animation.FlipEnter.FlipRightEnter;
import com.flyco.animation.FlipEnter.FlipTopEnter;
import com.flyco.animation.FlipEnter.FlipVerticalEnter;
import com.flyco.animation.FlipEnter.FlipVerticalSwingEnter;
import com.flyco.animation.FlipExit.FlipHorizontalExit;
import com.flyco.animation.FlipExit.FlipVerticalExit;
import com.flyco.animation.Jelly;
import com.flyco.animation.NewsPaperEnter;
import com.flyco.animation.SlideEnter.SlideBottomEnter;
import com.flyco.animation.SlideEnter.SlideLeftEnter;
import com.flyco.animation.SlideEnter.SlideRightEnter;
import com.flyco.animation.SlideEnter.SlideTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.animation.SlideExit.SlideLeftExit;
import com.flyco.animation.SlideExit.SlideRightExit;
import com.flyco.animation.SlideExit.SlideTopExit;
import com.flyco.animation.ZoomEnter.ZoomInBottomEnter;
import com.flyco.animation.ZoomEnter.ZoomInEnter;
import com.flyco.animation.ZoomEnter.ZoomInLeftEnter;
import com.flyco.animation.ZoomEnter.ZoomInRightEnter;
import com.flyco.animation.ZoomEnter.ZoomInTopEnter;
import com.flyco.animation.ZoomExit.ZoomInExit;
import com.flyco.animation.ZoomExit.ZoomOutBottomExit;
import com.flyco.animation.ZoomExit.ZoomOutExit;
import com.flyco.animation.ZoomExit.ZoomOutLeftExit;
import com.flyco.animation.ZoomExit.ZoomOutRightExit;
import com.flyco.animation.ZoomExit.ZoomOutTopExit;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import java.util.ArrayList;

public class DiaogAnimChoose {
    public static void showAnim(final Context context) {
        final Class<?> cs[] = {BounceEnter.class,//
                BounceTopEnter.class,//
                BounceBottomEnter.class,//
                BounceLeftEnter.class,//
                BounceRightEnter.class,//
                FlipHorizontalEnter.class,//
                FlipHorizontalSwingEnter.class,//
                FlipVerticalEnter.class,//
                FlipVerticalSwingEnter.class,//
                FlipTopEnter.class,//
                FlipBottomEnter.class,//
                FlipLeftEnter.class,//
                FlipRightEnter.class,//
                FadeEnter.class, //
                FallEnter.class,//
                FallRotateEnter.class,//
                SlideTopEnter.class,//
                SlideBottomEnter.class,//
                SlideLeftEnter.class, //
                SlideRightEnter.class,//
                ZoomInEnter.class,//
                ZoomInTopEnter.class,//
                ZoomInBottomEnter.class,//
                ZoomInLeftEnter.class,//
                ZoomInRightEnter.class,//

                NewsPaperEnter.class,//
                Flash.class,//
                ShakeHorizontal.class,//
                ShakeVertical.class,//
                Jelly.class,//
                RubberBand.class,//
                Swing.class,//
                Tada.class,//
        };

        ArrayList<String> itemList = new ArrayList<>();
        for (Class<?> c : cs) {
            itemList.add(c.getSimpleName());
        }

        final String[] contents = new String[itemList.size()];
        final ActionSheetDialog dialog = new ActionSheetDialog(context, //
                itemList.toArray(contents), null);
        dialog.title("使用内置show动画设置对话框显示动画\r\n指定对话框将显示效果")//
                .titleTextSize_SP(14.5f)//
                .layoutAnimation(null)//
                .cancelText("mCancel")
                .show();
        dialog.setCanceledOnTouchOutside(false);

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String animType = contents[position];
                    ((DialogHomeActivity) context).setBasIn((BaseAnimatorSet) cs[position].newInstance());
                    T.showShort(context, animType + "设置成功");
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void dissmissAnim(final Context context) {
        final Class<?> cs[] = {FlipHorizontalExit.class,//
                FlipVerticalExit.class,//
                FadeExit.class,//
                SlideTopExit.class,//
                SlideBottomExit.class,//
                SlideLeftExit.class, //
                SlideRightExit.class,//
                ZoomOutExit.class,//
                ZoomOutTopExit.class,//
                ZoomOutBottomExit.class,//
                ZoomOutLeftExit.class,//
                ZoomOutRightExit.class,//
                ZoomInExit.class,//
        };

        ArrayList<String> itemList = new ArrayList<String>();
        for (Class<?> c : cs) {
            itemList.add(c.getSimpleName());
        }

        final String[] contents = new String[itemList.size()];
        final ActionSheetDialog dialog = new ActionSheetDialog(context, //
                itemList.toArray(contents), null);
        dialog.title("使用内置dismiss动画设置对话框消失动画\r\n指定对话框将消失效果")//
                .titleTextSize_SP(14.5f)//
                .cancelText("mCancel")
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String animType = contents[position];
                    ((DialogHomeActivity) context).setBasOut((BaseAnimatorSet) cs[position].newInstance());
                    T.showShort(context, animType + "设置成功");
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
