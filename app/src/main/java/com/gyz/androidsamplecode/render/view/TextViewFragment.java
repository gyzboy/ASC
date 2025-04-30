package com.gyz.androidsamplecode.render.view;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.gyz.androidsamplecode.BaseFragment;

public class TextViewFragment extends BaseFragment {
   TextView tv;
   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      opts.add("获取textView 属性信息");
      opts.add("获取textView Layout信息");
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      tv = new TextView(getActivity());
      tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
      tv.setText("0123456789\n0123456789\n0123456789\n\n");
      tv.setBackgroundColor(Color.LTGRAY);
      tv.setMaxLines(10);
      tv.setEms(5);
      ((ViewGroup)view).addView(tv,1);
   }

   @Override
   public void handleClick(View v) {
      int index = (int) v.getTag();
      sb.setLength(0);
      switch (index) {
         case 0:
            content.setText(getTextInfo(tv));
            break;
         case 1:
            content.setText(getLayoutInfo(tv));
            break;
      }
   }

   private String getLayoutInfo(TextView text) {
      StringBuilder sb = new StringBuilder();
      Layout layout = text.getLayout();
      sb.append("textView宽度---->").append(layout.getWidth());
      sb.append("\ntextView高度---->").append(layout.getHeight());
      sb.append("\ntextView对齐方式--->").append(layout.getAlignment());
      sb.append("\ntextView行数--->").append(layout.getLineCount());
      sb.append("\n------------------------------");
      sb.append("\n第一行宽度---->").append(layout.getLineWidth(0));
      sb.append("\n第一行首字符索引---->").append(layout.getLineStart(0));
      sb.append("\n第一行尾字符索引---->").append(layout.getLineEnd(0) - 1);
      sb.append("\n获取第一行的顶部位置---->").append(layout.getLineTop(0));
      sb.append("\n获取第一行的底部位置---->").append(layout.getLineBottom(0));
      sb.append("\n获取第一行的左侧位置---->").append(layout.getLineLeft(0));
      sb.append("\n获取第一行的右侧位置---->").append(layout.getLineRight(0));
      sb.append("\ngetLineRight() - getLineLeft()不等于getLineWidth()通常是由于布局属性、文本对齐、内边距、缩进和文本方向等因素造成的");
      Rect bounds = new Rect();
      layout.getLineBounds(0, bounds);
      sb.append("\n第一行矩形边界信息---->").append(bounds);
      sb.append("\n第一行相对于视图顶部的像素值，表示文本行的基线位置---->").append(layout.getLineBaseline(0));
      sb.append("\n第一行下降线位置(下降线是指字符基线以下的部分，通常用于确定文本行的底部边界)--->").append(layout.getLineDescent(0));
      sb.append("\n根据垂直坐标找到对应的文本行索引--->").append(layout.getLineForVertical(layout.getLineTop(0)));
      sb.append("\n根据字符下标找到对应的文本行索引--->").append(layout.getLineForOffset(29));
      sb.append("\n用于获取指定行的可见文本的结束字符索引--->").append(layout.getLineVisibleEnd(0));
      return sb.toString();
   }

   private String getTextInfo(TextView tv){
      StringBuilder sb = new StringBuilder();
      sb.append("textView测量模式---->").append(tv.getWidth());
      sb.append("\ntextView宽度---->").append(tv.getWidth());
      sb.append("\ntextView高度---->").append(tv.getHeight());
      sb.append("\ntextView行高---->").append(tv.getLineHeight());
      sb.append("\ntextView行数---->").append(tv.getLineCount());
      sb.append("\ntextView最大行数---->").append(tv.getMaxLines());
      sb.append("\ntextView最大字符宽度限制(em是一个相对单位，通常等于当前字体大小，返回-1为不限制)---->").append(tv.getMaxEms());
      return sb.toString();
   }
}
