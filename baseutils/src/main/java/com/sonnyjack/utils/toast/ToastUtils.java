package com.sonnyjack.utils.toast;

import android.content.Context;
import android.text.TextUtils;
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

    private static void show(Context context, String s, int duration) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        Toast.makeText(context, s, duration).show();
    }
}
