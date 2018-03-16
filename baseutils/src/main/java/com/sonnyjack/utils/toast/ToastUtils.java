package com.sonnyjack.utils.toast;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by SonnyJack on 2018/3/13.
 */
public class ToastUtils {

    private ToastUtils() {
    }

    /**
     * show toast long
     */
    public static void showLongMsg(Context c, int resId) {
        show(c, c.getResources().getString(resId), Toast.LENGTH_LONG);
    }

    /**
     * show toast long
     */
    public static void showLongMsg(Context c, String s) {
        show(c, s, Toast.LENGTH_LONG);
    }

    /**
     * show toast short
     */
    public static void showShortMsg(Context c, int resId) {
        show(c, c.getResources().getString(resId), Toast.LENGTH_SHORT);
    }

    /**
     * show toast short
     */
    public static void showShortMsg(Context c, String s) {
        show(c, s, Toast.LENGTH_SHORT);
    }


    private static void show(Context context, String message, int duration) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(context, message, duration).show();
    }

    /**
     * show short toast in center
     * @param context
     * @param message
     */
    public static void showMsgInCenter(Context context, int message){
        showMsgInCenter(context, context.getString(message));
    }

    /**
     * show short toast in center
     * @param context
     * @param message
     */
    public static void showMsgInCenter(Context context, String message){
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
