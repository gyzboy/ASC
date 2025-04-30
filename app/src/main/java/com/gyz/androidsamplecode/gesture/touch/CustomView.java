package com.gyz.androidsamplecode.gesture.touch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.gyz.androidsamplecode.ASCUtil;

public class CustomView extends View {

   private StringBuilder sb;
   private final static String TAG = "ASCTouch - cv:";
   public CustomView(Context context) {
      super(context);
   }

   public boolean touchRet;
   public boolean dispatchRet;

   public  boolean reset = true;

   @Override
   public boolean dispatchTouchEvent(MotionEvent ev) {
      Log.d(TAG, " dispatchTouchEvent " + ASCUtil.actionToStr(ev.getAction()));
      boolean ret = super.dispatchTouchEvent(ev);
//      Log.d(TAG,  " dispatchTouchEvent " + TouchUtil.actionToStr(ev.getAction()) + "ret = " + ret);
      if (reset) {
         return ret;
      }
      return dispatchRet;
   }

   @SuppressLint("ClickableViewAccessibility")
   @Override
   public boolean onTouchEvent(MotionEvent ev) {
      Log.d(TAG, " onTouchEvent " + ASCUtil.actionToStr(ev.getAction()));
      boolean ret = super.onTouchEvent(ev);
//      Log.d(TAG, " onTouchEvent " + TouchUtil.actionToStr(ev.getAction()) + " ret = " + ret);
      if (reset) {
         return ret;
      }
      return touchRet;
   }
}
