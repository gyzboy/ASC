package com.gyz.androidsamplecode.gesture.click;

import android.content.Context;
import android.view.View;

public class CustomView extends View {

   private StringBuilder sb = new StringBuilder();
   private final static String TAG = "cv:";
   public CustomView(Context context) {
      super(context);
   }

   @Override
   public boolean performClick() {
      sb.append(TAG+"performClick\n");
      return super.performClick();
   }

   public String getLog(){
      String ret = sb.toString();
      sb.setLength(0);
      return ret;
   }
}
