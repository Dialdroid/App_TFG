package com.example.medicinapp.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import com.example.medicinapp.providers.AuthProvider;
import com.example.medicinapp.providers.UserProvider;

import java.util.List;

public class ViewedMessageHelper {

    public static void updateOnline(boolean status, final Context context) {
        UserProvider usersProvider = new UserProvider();
        AuthProvider authProvider = new AuthProvider();
        if (authProvider.getUID() != null) {
            if (isApplicationSentToBackground(context)) {
                usersProvider.updateOnline(authProvider.getUID(), status);
            }
            else if (status){
                usersProvider.updateOnline(authProvider.getUID(), status);
            }
        }
    }

    public static boolean isApplicationSentToBackground(final Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

}
