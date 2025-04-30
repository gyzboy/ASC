package com.gyz.androidsamplecode.gesture.touch;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import android.support.annotation.Nullable;

import com.gyz.androidsamplecode.BaseFragment;
import com.gyz.androidsamplecode.ASCUtil;

public class TouchFragment extends BaseFragment {

   CustomView cv;
   CustomLayout cl;

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      opts.add("父View dispatch为false");
      opts.add("父View intercept为true");
      opts.add("父View onTouch为true");
      opts.add("子View dispatch为false");
      opts.add("子View onTouch为false");
      opts.add("显示日志");
      opts.add("清除日志");
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      TextView tv = new TextView(view.getContext());
      tv.setText("");
      cv = new CustomView(view.getContext());
      cl = new CustomLayout(view.getContext());
      cl.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
      cl.setBackgroundColor(Color.BLUE);
      cv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
      cv.setBackgroundColor(Color.RED);
      cv.setClickable(false);
      cl.setPadding(200,200,200,200);
      cl.addView(cv);
      cv.setClickable(true);
      cl.setClickable(true);
      tv.setText("- dispatchTouchEvent是根本，决定是否后续传递，true为继续传递，false后续事件接收不到\n" +
              "   - ViewGroup中：在dispatch中首先处理onIntercept返回结果，然后找可以消费事件的View" +
              "   - View中：决定后续onTouch是否可以正常接收" +
              "\n" +
              "事件传递流程 ViewRootImpl->DecorView->Activity->Window->DecorView->ViewGroup->View\n" +
              "在ACTION_MOVE中会判断事件的位置是否超出view的边界，如果超出边界则将mPrivateFlags置为not PRESSED状态\n" +
              ",但如果父view没有拦截事件，则会继续受到ACTION_MOVE和ACTION_UP等事件,在ACTION_UP中不会执行performClick()等逻辑");
      ((ViewGroup)view).addView(tv,2);
      ((ViewGroup)view).addView(cl,3);
   }

   @Override
   public void handleClick(View v) {
      int index = (int) v.getTag();
      switch (index) {
         case 0:
            cl.reset = false;
            cv.reset = true;
            cl.dispatchRet = false;
            cl.interceptRet = false;
            cl.touchRet = false;
            break;
         case 1:
            cl.reset = false;
            cv.reset = true;
            cl.dispatchRet = true;
            cl.interceptRet = true;
            cl.touchRet = false;
            break;
         case 2:
            cl.reset = false;
            cv.reset = true;
            cl.dispatchRet = true;
            cl.interceptRet = false;
            cl.touchRet = true;
            break;
         case 3:
            cl.reset = true;
            cv.reset = false;
            cv.touchRet = true;
            cv.dispatchRet = false;
            break;
         case 4:
            cl.reset = true;
            cv.reset = false;
            cv.dispatchRet = true;
            cv.touchRet = false;
            break;
         case 5:
            ASCUtil.captureLogcat(content,"ASCTouch - ");
            break;
         case 6:
            ASCUtil.cleanLogcat();
            content.setText("");
            cl.reset = true;
            cv.reset = true;
            break;
      }
   }
}
