package com.gyz.androidsamplecode.render.view;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gyz.androidsamplecode.BaseFragment;

public class FrameLayoutFragment extends BaseFragment {
   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      FrameLayout frameLayout = new FrameLayout(getContext());
      ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(200,200);
      frameLayout.setLayoutParams(params);
      frameLayout.setBackgroundColor(Color.BLUE);
      ColorDrawable drawable = new ColorDrawable(Color.RED);
      drawable.setBounds(params.width - 50,0,params.width,50);
      frameLayout.getOverlay().add(drawable);
//      frameLayout.setForegroundGravity(Gravity.TOP|Gravity.END);
      ((ViewGroup)view).addView(frameLayout);
   }

   @Override
   public void handleClick(View v) {
      int index = (int) v.getTag();
      sb.setLength(0);
   }
}
