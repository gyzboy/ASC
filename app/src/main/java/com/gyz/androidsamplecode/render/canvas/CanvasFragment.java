package com.gyz.androidsamplecode.render.canvas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.gyz.androidsamplecode.BaseFragment;

public class CanvasFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opts.add("Canvas是什么");
        opts.add("Canvas变化原理");
        opts.add("软件绘制");
        opts.add("硬件绘制");
        opts.add("save与restore");
        opts.add("离屏绘制");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void handleClick(View v) {
        int index = (int) v.getTag();
        sb.setLength(0);
        switch (index) {
            case 0:
                sb.append("Canvas是一个用于在屏幕上绘制图形的2D绘图类。它提供了绘制各种形状、文本和图像的方法\n" +
                        "同一个窗口的视图层次树中的View节点，其onDraw中的Canvas参数都是同一个，\n" +
                        "是由该窗口的ViewRootImpl通过surface.lockCanvas获得，并把该Canvas传给根视图， \n" +
                        "对于应用窗口来说，其根视图的Canvas大小一般为整个屏幕");
                break;
            case 1:
                sb.append("改变的是View的坐标系\n" +
                        "x,y>0->scrollTo(x,y)->Canvas.translate(left-x,top-y)->坐标系上移且左移->视图上移且左移");
                break;
            case 2:
                sb.append("软件绘制是指使用CPU进行图形的渲染和绘制操作，使用的是Canvas API,绘制结果存储在内存的位图中，然后由系统将其复制到屏幕上\n" +
                        "优点：\n" +
                        "   - 软件绘制不依赖于特定的硬件特性，因此可以在所有设备上运行\n" +
                        "   - 软件绘制允许对每个像素进行精细的控制，适合需要自定义复杂绘制逻辑的场景\n" +
                        "缺点：\n" +
                        "   - 性能低下");
                break;
            case 3:
                sb.append("硬件绘制将绘制操作委托给GPU，通过OpenGL ES等图形接口进行渲染。\n" +
                        "优点：\n" +
                        "   - GPU可以并行处理大量的绘制操作，提高了效率\n" +
                        "缺点：\n" +
                        "   - 兼容性\n" +
                        "   - 精细控制力弱");
                break;
            case 4:
                sb.append("save将当前图层压入栈，restore出栈，如果restore次数>save次数，将Crash");
                break;
            case 5:
                sb.append("离屏绘制是指在不直接影响屏幕显示的情况下进行图形绘制。这种技术通常用于提高绘制性能、实现复杂的图形效果或进行图像处理\n" +
                        "1、使用 Bitmap 和 Canvas：" +
                        "   - 创建 Bitmap 对象：创建一个空的 Bitmap，指定其宽度、高度和颜色配置。\n" +
                        "   - 创建 Canvas 对象：使用 Bitmap 创建一个 Canvas 对象，所有绘制操作都将在这个 Canvas 上进行\n" +
                        "   - 执行绘制操作：使用 Canvas 的绘制方法（如 drawRect()、drawCircle() 等）在 Bitmap 上绘制图形\n" +
                        "   - 使用绘制结果：绘制完成后，可以将 Bitmap 用作 Drawable 或在其他 Canvas 上绘制\n" +
                        "2、使用 RenderScript\n" +
                        "3、使用 OpenGL ES");
                break;
        }
        content.setText(sb.toString());
    }
}
