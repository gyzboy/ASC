package com.gyz.androidsamplecode.system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import android.support.annotation.Nullable;

import com.gyz.androidsamplecode.BaseFragment;

public class ScreenFragment extends BaseFragment {

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      opts.add("dpi是什么，与density关系");
      opts.add("dp是什么，如何dp转px");

   }

   @SuppressLint("SetTextI18n")
   @Override
   public void handleClick(View v) {
      int index = (int) v.getTag();
      switch (index) {
         case 0:
            content.setText("density是一个用于描述屏幕像素密度的指标，dpi（dots per inch）是单位。它表示每英寸屏幕上包含的像素点数\n" +
                    "计算公式：√宽²+高² / 对角线英寸长");
            break;
         case 1:
            content.setText("dp是一种抽象单位，基于屏幕的物理密度\n" +
                    "计算公式：px = dpi / 160 * dp");
            break;
      }
   }
}
