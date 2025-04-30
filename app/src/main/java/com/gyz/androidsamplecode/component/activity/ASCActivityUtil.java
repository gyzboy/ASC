package com.gyz.androidsamplecode.component.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ASCActivityUtil {
   public static String printActivityComponentInfo(Activity activity) {
      StringBuilder sb = new StringBuilder();
      try {
         // 获取PackageManager实例
         PackageManager packageManager = activity.getPackageManager();

         // 获取当前Activity的ComponentName
         ComponentName componentName = activity.getComponentName();

         // 获取ActivityInfo
         ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA);

         // 打印Activity的组件信息
         sb.append("\nName: ").append(activityInfo.name);
         sb.append("\nPackageName: ").append(activityInfo.packageName);
         sb.append("\nLabel: ").append(activityInfo.loadLabel(packageManager));
         sb.append("\nTaskAffinity: ").append(activityInfo.taskAffinity);
         sb.append("\nLaunchMode: ").append(activityInfo.launchMode);
         sb.append("\nFlags: ").append(activityInfo.flags);
         sb.append("\nMetaData: ").append(activityInfo.metaData);
      } catch (PackageManager.NameNotFoundException e) {
         e.printStackTrace();
      }
      return sb.toString();
   }

   public static String printActivityInfo(Activity activity) {
      StringBuilder sb = new StringBuilder();
      // 获取Activity的基本信息
      String activityName = activity.getClass().getSimpleName();
      String packageName = activity.getPackageName();
      String title = activity.getTitle().toString();

      // 打印Intent信息
      Intent intent = activity.getIntent();
      String action = intent.getAction();
      Uri data = intent.getData();
      String type = intent.getType();
      Bundle extras = intent.getExtras();

      // 打印信息
      sb.append("Activity Name: ").append(activityName);
      sb.append("\nPackage Name: ").append(packageName);
      sb.append("\nTaskId: ").append(activity.getTaskId());
      sb.append("\nTitle: ").append(title);
      sb.append("\nIntent Action: ").append(action);
      sb.append("\nIntent Data: ").append(data);
      sb.append("\nIntent Type: ").append(type);
      if (extras != null) {
         for (String key : extras.keySet()) {
            sb.append("Extra: ").append(key).append(" = ").append(extras.get(key));
         }
      }
      return sb.toString();
   }

   public static String printTaskStackInfo(Context context) {
      StringBuilder sb = new StringBuilder();
      ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

      if (activityManager != null) {
         List<ActivityManager.AppTask> appTasks = activityManager.getAppTasks();
         for (ActivityManager.AppTask appTask : appTasks) {
            ActivityManager.RecentTaskInfo taskInfo = appTask.getTaskInfo();
            if (taskInfo != null) {
               sb.append("\n----------");
               sb.append("\nTask ID: ").append(taskInfo.id);
               sb.append("\nBase Activity: ").append(taskInfo.baseActivity);
               sb.append("\nTop Activity: ").append(taskInfo.topActivity);
            }
         }
      }
      return sb.toString();
   }

   // 实现沉浸式
   public static void enableImmersiveMode(Activity activity) {
//      普通全屏模式：
//      仅隐藏状态栏，用户可以通过下拉手势显示状态栏。
//      使用WindowManager.LayoutParams.FLAG_FULLSCREEN标志。

//      沉浸模式（Immersive Mode）：
//      隐藏状态栏和导航栏，用户可以通过边缘滑动手势临时显示这些栏。
//      通过View.SYSTEM_UI_FLAG_IMMERSIVE标志启用。

//      粘性沉浸模式（Immersive Sticky Mode）：
//      类似于沉浸模式，但在用户交互时（如滑动手势）显示的系统栏会在短时间后自动隐藏。
//      通过View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY标志启用。
      View decorView = activity.getWindow().getDecorView();
      int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
              | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
      decorView.setSystemUiVisibility(uiOptions);
   }
}
