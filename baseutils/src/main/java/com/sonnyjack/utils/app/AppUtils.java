package com.sonnyjack.utils.app;

import android.app.Activity;
import android.os.Build;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by SonnyJack on 2018/3/13.
 */

public class AppUtils {

    private Stack<Activity> mActivityStack = new Stack<>();

    private AppUtils() {
    }

    private static final class AppUtilsHolder {
        private static AppUtils instance = new AppUtils();
    }

    public static AppUtils getInstance() {
        return AppUtilsHolder.instance;
    }

    /***
     * add activity to stack
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (null == activity) {
            return;
        }
        //the version >= 17
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            return;
        }
        if (activity.isFinishing()) {
            return;
        }
        mActivityStack.add(activity);
    }

    /***
     * remove activity
     * note: the method only remove activity that distance from the top of the stack
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (null == mActivityStack || mActivityStack.size() <= 0) {
            return;
        }
        if (null == activity) {
            return;
        }
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity eachActivity = mActivityStack.get(i);
            if (null == eachActivity) {
                continue;
            }
            if (eachActivity.getClass().equals(activity.getClass())) {
                mActivityStack.remove(eachActivity);
                break;
            }
        }
    }

    /***
     * remove all activity
     * @param activity
     */
    public void removeAllActivity(Activity activity) {
        if (null == mActivityStack || mActivityStack.size() <= 0) {
            return;
        }
        if (null == activity) {
            return;
        }
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity eachActivity = mActivityStack.get(i);
            if (null == eachActivity) {
                continue;
            }
            if (eachActivity.getClass().equals(activity.getClass())) {
                mActivityStack.remove(eachActivity);
            }
        }
    }

    /**
     * finish all activity
     */
    public synchronized void finishAllActivity() {
        for (Activity eachActivity : mActivityStack) {
            if (eachActivity != null) {
                eachActivity.finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * finish all activity except activityName
     *
     * @param activityName(class.getName)
     */
    public void finishAllActivityExcept(String activityName) {
        if (null == mActivityStack || mActivityStack.size() <= 0) {
            return;
        }
        ArrayList<Activity> finishList = new ArrayList<>();
        for (Activity eachActivity : mActivityStack) {
            if (null == eachActivity) {
                continue;
            }
            if (!eachActivity.getClass().getName().equals(activityName)) {
                eachActivity.finish();
                finishList.add(eachActivity);
            }
        }
        mActivityStack.removeAll(finishList);
        finishList.clear();
    }

    /**
     * return true while stack have activityName, otherwise false.
     *
     * @param activityName
     * @return
     */
    public boolean hasActivity(String activityName) {
        for (Activity eachActivity : mActivityStack) {
            if (null == eachActivity) {
                continue;
            }
            if (eachActivity.getClass().getName().equals(activityName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * return current activity (the activity in stack top)
     *
     * @return
     */
    public Activity getCurrentActivity() {
        if (mActivityStack.size() == 0) {
            return null;
        } else {
            return mActivityStack.get(mActivityStack.size() - 1);
        }
    }
}
