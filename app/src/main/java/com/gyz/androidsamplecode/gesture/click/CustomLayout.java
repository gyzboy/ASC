package com.gyz.androidsamplecode.gesture.click;

import android.content.Context;
import android.widget.LinearLayout;

public class CustomLayout extends LinearLayout {
   private StringBuilder sb = new StringBuilder();
   private final static String TAG = "cl:";
   public CustomLayout(Context context) {
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
