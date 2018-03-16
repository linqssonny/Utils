package com.sonnyjack.utils.keyboard;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by SonnyJack on 2018/3/15.
 */

public class KeyboardUtils {

    private KeyboardUtils() {

    }

    /**
     * show the soft input
     *
     * @param view
     */
    public static void showSoftInput(View view) {
        if (null == view) {
            throw new NullPointerException("the view is null");
        }
        Context context = view.getContext();
        if (null == context) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * show the soft input
     *
     * @param activity
     */
    public static void showSoftInput(Activity activity) {
        if (null == activity) {
            throw new NullPointerException("the activity is null");
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (null == inputMethodManager) {
            return;
        }
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * hide the soft input.
     *
     * @param activity the activity.
     */
    public static void hideSoftInput(Activity activity) {
        if (null == activity) {
            throw new NullPointerException("the activity is null");
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * hide the soft input.
     *
     * @param view The view.
     */
    public static void hideSoftInput(View view) {
        if (null == view) {
            throw new NullPointerException("the view is null");
        }
        Context context = view.getContext();
        if (null == context) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null == inputMethodManager) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * toggle the soft input display or hide.
     */
    public static void toggleSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null == inputMethodManager) {
            return;
        }
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
