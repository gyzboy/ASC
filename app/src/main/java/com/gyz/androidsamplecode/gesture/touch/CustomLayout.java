package com.gyz.androidsamplecode.gesture.touch;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.gyz.androidsamplecode.ASCUtil;

public class CustomLayout extends LinearLayout {
   private final static String TAG = "ASCTouch - cl:";
   public CustomLayout(Context context) {
      super(context);
   }

   public boolean dispatchRet;
   public boolean interceptRet;
   public boolean touchRet;

   public boolean reset = true;


   @Override
   public boolean dispatchTouchEvent(MotionEvent ev) {
      Log.d(TAG," dispatchTouchEvent " + ASCUtil.actionToStr(ev.getAction()));
      boolean ret = super.dispatchTouchEvent(ev);
//      Log.d(TAG, " dispatchTouchEvent " + TouchUtil.actionToStr(ev.getAction()) + " ret = " + ret);
      if (reset) {
         return ret;
      }
      return dispatchRet;
   }

   @Override
   public boolean onTouchEvent(MotionEvent event) {
      Log.d(TAG, " onTouchEvent " + ASCUtil.actionToStr(event.getAction()));
      boolean ret = super.onTouchEvent(event);
//      Log.d(TAG, " onTouchEvent " + TouchUtil.actionToStr(event.getAction()) + " ret = " + ret);
      if (reset) {
         return ret;
      }
      return touchRet;
   }

   @Override
   public boolean onInterceptTouchEvent(MotionEvent ev) {
      Log.d(TAG, " onInterceptTouchEvent " + ASCUtil.actionToStr(ev.getAction()));
      boolean ret = super.onInterceptTouchEvent(ev);
//      Log.d(TAG, " onInterceptTouchEvent " + TouchUtil.actionToStr(ev.getAction()) + " ret = " + ret);
      if (reset) {
         return ret;
      }
      return interceptRet;
   }
}
