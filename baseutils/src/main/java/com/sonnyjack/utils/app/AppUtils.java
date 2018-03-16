package com.sonnyjack.utils.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by SonnyJack on 2018/3/15.
 */

public class AppUtils {

    private AppUtils() {

    }

    /**
     * return true if the app have exits,otherwise false
     *
     * @param context
     * @param packageName the app package name
     * @return
     */
    public static boolean isInstalledApp(Context context, String packageName) {
        if (null == context) {
            throw new NullPointerException("the context is null");
        }
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        if (null != packageInfoList && packageInfoList.size() > 0) {
            for (int i = 0; i < packageInfoList.size(); i++) {
                String name = packageInfoList.get(i).packageName;
                if (TextUtils.equals(name, packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * return true if the app is running foreground, otherwise false
     *
     * @param context
     * @return
     */
    public static boolean isAppForeground(Context context) {
        return isAppForeground(context, context.getPackageName());
    }

    /**
     * return true if the app is running foreground, otherwise false
     *
     * @param context
     * @param packageName The name of the package.
     * @return
     */
    public static boolean isAppForeground(Context context, String packageName) {
        if (null == context) {
            throw new NullPointerException("the context is null");
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (null == activityManager) {
            return false;
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        if (null == runningAppProcessInfoList || runningAppProcessInfoList.size() == 0) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcessInfoList) {
            if (null == runningAppProcessInfo) {
                continue;
            }
            if (runningAppProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return TextUtils.equals(runningAppProcessInfo.processName, packageName);
            }
        }
        return false;
    }
}
