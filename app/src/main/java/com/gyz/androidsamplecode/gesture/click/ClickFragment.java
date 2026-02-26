package com.gyz.androidsamplecode.gesture.click;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.Nullable;

import com.gyz.androidsamplecode.BaseFragment;

public class ClickFragment extends BaseFragment {

   CustomView cv;
   CustomLayout cl;

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      opts.add("子View绑定click事件");
      opts.add("父View绑定click事件");
      opts.add("父&&子View绑定click事件");
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      TextView tv = new TextView(view.getContext());
      tv.setText("- 设置click事件监听会使View变为clickable\n" +
              "- 父&&子View同时设置click监听，谁获取焦点，谁响应事件");
      cv = new CustomView(view.getContext());
      cl = new CustomLayout(view.getContext());
      cl.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
      cl.setBackgroundColor(Color.BLUE);
      cv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
      cv.setBackgroundColor(Color.RED);
      cv.setClickable(false);
      cl.setPadding(200,200,200,200);
      cl.addView(cv);
      ((ViewGroup)view).addView(tv,2);
      ((ViewGroup)view).addView(cl,3);
   }

   @Override
   public void handleClick(View v) {
      int index = (int) v.getTag();
      switch (index) {
         case 0:
            cv.setOnClickListener(v1 -> {
               sb.append(cv.getLog()).append(cl.getLog());
               content.setText(sb.toString());
            });
            break;
         case 1:
            cl.setOnClickListener(v12 -> {
               sb.append(cv.getLog()).append(cl.getLog());
               content.setText(sb.toString());
            });
            break;
         case 2:
            cv.setOnClickListener(v13 -> {
               sb.append(cv.getLog()).append(cl.getLog());
               content.setText(sb.toString());
            });
            cl.setOnClickListener(v14 -> {
               sb.append(cv.getLog()).append(cl.getLog());
               content.setText(sb.toString());
            });
            break;
      }
   }
}
