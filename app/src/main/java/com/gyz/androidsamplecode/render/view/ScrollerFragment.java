package com.gyz.androidsamplecode.render.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.ScrollView;

import com.gyz.androidsamplecode.BaseFragment;
import com.gyz.androidsamplecode.R;

public class ScrollerFragment extends BaseFragment {

   private ScrollView scrollView;

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      opts.add("不设置 ViewPort");
      opts.add("设置 ViewPort");
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      scrollView = view.findViewById(R.id.scroller);
      scrollView.setBackgroundColor(Color.BLUE);
      scrollView.getLayoutParams().height = 1000;
      content.setBackgroundColor(Color.RED);
   }

   @SuppressLint("SetTextI18n")
   @Override
   public void handleClick(View v) {
      int index = (int) v.getTag();
      switch (index) {
         case 0:
            scrollView.setFillViewport(false);
            break;
         case 1:
            scrollView.setFillViewport(true);
            break;
      }
   }
}
