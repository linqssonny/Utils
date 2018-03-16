package com.sonnyjack.utils.example;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/7/4.
 */
public class PermissionUtils {

    /**
     * 日历
     */
    public static final int PERMISSION_GROUP_CALENDAR = 0x1000;
    /**
     * 相机
     */
    public static final int PERMISSION_GROUP_CAMERA = 0x1001;
    /**
     * 联系人
     */
    public static final int PERMISSION_GROUP_CONTACTS = 0x1002;
    /**
     * 定位
     */
    public static final int PERMISSION_GROUP_LOCATION = 0x1003;
    /**
     * 麦克风
     */
    public static final int PERMISSION_GROUP_MICROPHONE = 0x1004;
    /**
     * 电话
     */
    public static final int PERMISSION_GROUP_PHONE = 0x1005;
    /**
     * 传感器
     */
    public static final int PERMISSION_GROUP_SENSORS = 0x1006;
    /**
     * 短信
     */
    public static final int PERMISSION_GROUP_SMS = 0x1007;
    /**
     * 存储器
     */
    public static final int PERMISSION_GROUP_STORAGE = 0x1008;

    public static List<String> createRequestPermission(int action) {
        List<String> permissionsList = new ArrayList<>();
        switch (action) {
            case PERMISSION_GROUP_CALENDAR:
                //日历
                permissionsList.add(Manifest.permission.READ_CALENDAR);
                permissionsList.add(Manifest.permission.WRITE_CALENDAR);
                break;
            case PERMISSION_GROUP_CAMERA:
                //相机
                permissionsList.add(Manifest.permission.CAMERA);
                break;
            case PERMISSION_GROUP_CONTACTS:
                //联系人
                permissionsList.add(Manifest.permission.WRITE_CONTACTS);
                permissionsList.add(Manifest.permission.READ_CONTACTS);
                break;
            case PERMISSION_GROUP_LOCATION:
                //定位
                permissionsList.add(Manifest.permission.ACCESS_FINE_LOCATION);
                permissionsList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
                break;
            case PERMISSION_GROUP_MICROPHONE:
                //麦克风
                permissionsList.add(Manifest.permission.RECORD_AUDIO);
                break;
            case PERMISSION_GROUP_PHONE:
                //电话
                permissionsList.add(Manifest.permission.READ_PHONE_STATE);
                permissionsList.add(Manifest.permission.CALL_PHONE);
                permissionsList.add(Manifest.permission.READ_CALL_LOG);
                permissionsList.add(Manifest.permission.WRITE_CALL_LOG);
                permissionsList.add(Manifest.permission.ADD_VOICEMAIL);
                permissionsList.add(Manifest.permission.USE_SIP);
                permissionsList.add(Manifest.permission.PROCESS_OUTGOING_CALLS);
                break;
            case PERMISSION_GROUP_SENSORS:
                //传感器
                permissionsList.add(Manifest.permission.BODY_SENSORS);
                break;
            case PERMISSION_GROUP_SMS:
                //短息
                permissionsList.add(Manifest.permission.READ_SMS);
                /*permissionsList.add(Manifest.permission.RECEIVE_WAP_PUSH);
                permissionsList.add(Manifest.permission.RECEIVE_MMS);
                permissionsList.add(Manifest.permission.RECEIVE_SMS);
                permissionsList.add(Manifest.permission.SEND_SMS);*/
                break;
            case PERMISSION_GROUP_STORAGE:
                //外部存储器
                permissionsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            default:
                throw new IllegalStateException("the action is valid");
        }
        return permissionsList;
    }

    public static int[] createGrantedArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = PackageManager.PERMISSION_GRANTED;
        }
        return result;
    }

    /***
     * 判断是否有权限
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkSelfPermission(Context context, String permission) {
        int hasPermission = ContextCompat.checkSelfPermission(context, permission);
        if (hasPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    public static boolean requestPermissions(Activity activity, List<String> permissions, int requestCode) {
        if (null == activity || null == permissions) {
            return false;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        List<String> permissionsList = new ArrayList<>();
        for (int i = 0; i < permissions.size(); i++) {
            String permission = permissions.get(i);
            if (!PermissionUtils.checkSelfPermission(activity, permission)) {
                permissionsList.add(permission);
            }
        }
        if (permissionsList.size() <= 0) {
            return true;
        } else {
            if (!PermissionUtils.shouldShowRequestPermissionRationale(activity, permissionsList.get(0))) {
                ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]), requestCode);
            } else {
                ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]), requestCode);
            }
        }
        return false;
    }
}
