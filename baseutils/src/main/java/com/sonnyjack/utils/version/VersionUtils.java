package com.sonnyjack.utils.version;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by SonnyJack on 2018/3/13.
 */
public class VersionUtils {

    private VersionUtils() {

    }

    /**
     * return version code
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        if (null == context) {
            throw new NullPointerException("context must not null");
        }
        String packageName = context.getPackageName();
        int versionCode = 1;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /***
     * return version name
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        if (null == context) {
            throw new NullPointerException("context must not null");
        }
        String packageName = context.getPackageName();
        String versionName = "0.0";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
