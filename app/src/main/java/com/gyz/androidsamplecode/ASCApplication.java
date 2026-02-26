package com.gyz.androidsamplecode;


import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;


import com.bytedance.rheatrace.RheaTrace3;

import java.util.Map;

public class ASCApplication extends Application {

   @Override
   protected void attachBaseContext(Context base) {
      super.attachBaseContext(base);
      RheaTrace3.init(base);
   }

   @Override
   public void onCreate() {
      super.onCreate();
      }
}
