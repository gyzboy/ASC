package com.gyz.androidsamplecode.render.drawable.window;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;


import com.gyz.androidsamplecode.BaseFragment;

public class DrawableFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opts.add("Drawable 是什么");
        opts.add("Drawable 分类");
        opts.add("View 如何转换为 Drawable");
    }

    @Override
    public void handleClick(View v) {
        int index = (int) v.getTag();
        sb.setLength(0);
        switch (index) {
            case 0:
                sb.append("- Drawable 是一个抽象的类，用来表示可以绘制在屏幕上绘制的图形\n" +
                        "- Drawable和View的区别在于前者不能和用户交互，只能展示，因此Drawable不会像View那样持有很多变量和引用，所以Drawable比View从内存上看要轻量很多");
                break;
            case 1:
                sb.append("无状态：\n" +
                        " - BitmapDrawable:用于将图片转为BitmapDrawable\n" +
                        " - ShapeDrawable:通过颜色来构造Drawable\n" +
                        " - VectorDrawable:矢量图，Android5.0及以上支持。便于在缩放过程中保证显示质量，以及一个矢量图支持多个屏幕，减少apk大小\n" +
                        " - TransitionDrawable:用于实现Drawable间的淡入淡出效果\n" +
                        " - InsetDrawable:用于将其他Drawable内嵌到自己当中,并可以在四周留出一定的间距,当一个View希望自己的背景比实际的区域小时，可以采用其来实现。\n" +
                        "有状态：\n" +
                        " - StateListDrawable:用于有状态交互时的View设置，比如 按下时 的背景，松开时 的背景，有焦点时的背景灯\n" +
                        " - LevelListDrawable:根据等级(level)来切换不同的 Drawble。在View中可以通过设置 setImageLevel 更改不同的Drawable\n" +
                        " - ScaleDrawable:根据不同的等级(level)指定 Drawable 缩放到一定比例;\n" +
                        " - ClipDrawable:根据当前等级(level)来裁剪 Drawable ;");
                break;
            case 2:
                sb.append("1、创建 Bitmap：首先需要创建一个 Bitmap 对象，用于存储 View 的绘制内容。\n" +
                        "2、设置 Canvas：使用 Bitmap 创建一个 Canvas 对象，然后将 View 的内容绘制到这个 Canvas 上。\n" +
                        "3、转换为 Drawable：将 Bitmap 转换为 BitmapDrawable，这是一种 Drawable 的子类。");
                break;
        }
        content.setText(sb.toString());
    }
}
