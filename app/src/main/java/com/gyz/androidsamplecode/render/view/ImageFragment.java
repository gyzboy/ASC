package com.gyz.androidsamplecode.render.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.gyz.androidsamplecode.BaseFragment;

public class ImageFragment extends BaseFragment {

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      opts.add("图片内存占用大小");
      opts.add("scaleType");

   }

   @SuppressLint("SetTextI18n")
   @Override
   public void handleClick(View v) {
      int index = (int) v.getTag();
      switch (index) {
         case 0:
            content.setText("w*当前dpi/目标文件夹dpi*h*当前dpi/目标文件夹dpi*4(ARGB)");
            break;
         case 1:
            content.setText("FIT_开头，都会对图片进行缩放\n" +
                    "   - FIT_CENTER(默认值):图片缩放至居中显示\n" +
                    "   - FIT_START:图片在左边或者上边显示\n" +
                    "   - FIT_END:图片在底部或者右边显示\n" +
                    "   - FIT_XY:宽高填充满控件大小\n\n" +
                    "CENTER_开头，都会居中显示\n" +
                    "   - CENTER:按原图片SIZE居中显示，截取图片居中显示\n" +
                    "   - CENTER_CROP:按比例扩大or缩放图片SIZE等于View大小，然后居中显示\n" +
                    "   - CENTER_INSIDE（图片SIZE>VIEW SIZE时=FIT_CENTER）:以完全展示图片的内容为目的。图片将被等比缩放到能够完整展示在ImageView中并居中");
            break;
      }
   }
}
