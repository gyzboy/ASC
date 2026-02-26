package com.gyz.androidsamplecode.render.view;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.gyz.androidsamplecode.ASCUtil;
import com.gyz.androidsamplecode.BaseFragment;
import com.gyz.androidsamplecode.R;

public class CustomViewFragment extends BaseFragment {
    private ASCView ascView;
    private ASCLayout layout;
    private static final String TAG = "ASCView";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opts.add("获取View生命周期");
        opts.add("手动触发requestLayout");
        opts.add("手动触发invalidate");
        opts.add("手动触发setVisibility");
        opts.add("MeasureSpec解析");
        opts.add("LayoutParams解析");
        opts.add("指定图层类型");
        opts.add("性能优化手段");
        opts.add("测试坐标获取返回值");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ascView = new ASCView(getActivity());
        layout = new ASCLayout(getActivity());
        ascView.setLayoutParams(new LinearLayout.LayoutParams(30, 30));
        content.setText("请通过adb logcat | grep ASCView 查看日志");
        layout.addView(ascView);
        ((ViewGroup) view).addView(layout);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void handleClick(View v) {
        int index = (int) v.getTag();
        switch (index) {
            case 0:
                break;
            case 1:
                ascView.requestLayout();
                break;
            case 2:
                ascView.invalidate();
                break;
            case 3:
                ascView.setVisibility(View.GONE);
                ascView.setVisibility(View.INVISIBLE);
                ascView.setVisibility(View.VISIBLE);
                break;
            case 4:
                String cc = "MeasureSpec代表着测量模式\n" +
                        "   - 高2位代表SpecMode\n" +
                        "   - 低30位代表SpecSize\n" +
                        "      - EXACTLY=match_parent/具体数值\n" +
                        "      - AT_MOST=wrap_content";
                content.setText(cc);
                break;
            case 5:
                String ccc = "LayoutParams存储了子View在加入ViewGroup中时的一些参数信息,可通过重写generateLayoutParams返回自定义LayoutParam\n\n" +
                        "   - 定义视图的布局属性：LayoutParams用于指定视图在父容器中的宽度和高度。这通常通过设置width和height属性来实现，常用的值包括MATCH_PARENT、WRAP_CONTENT和具体的像素值。\n\n" +
                        "   - 控制视图的布局行为：不同的布局管理器（如LinearLayout、RelativeLayout、FrameLayout等）会扩展LayoutParams类，以支持特定的布局规则和属性。例如，LinearLayout.LayoutParams支持weight属性，而RelativeLayout.LayoutParams支持alignParentTop等属性。\n\n" +
                        "   - 影响视图的子布局：当一个视图容器（如ViewGroup）在布局其子视图时，会参考每个子视图的LayoutParams来决定如何放置和调整这些子视图。";
                content.setText(ccc);
                break;
            case 6:
                content.setText("LAYER_TYPE_NONE:\n" +
                        "   - 默认图层类型，不使用任何特殊的图层。视图的绘制操作将直接进行，不会进行额外的缓存或硬件加速。\n\n" +
                        "LAYER_TYPE_SOFTWARE:\n" +
                        "   - 使用软件图层（CPU渲染）进行绘制。绘制操作由CPU执行，绘制结果存储在内存的位图中\n\n" +
                        "LAYER_TYPE_HARDWARE:\n" +
                        "   - 使用硬件图层（GPU渲染）进行绘制。绘制操作由GPU执行，能够提高绘制性能和效率");
                break;
            case 7:
                content.setText("- 尽量不要对背景使用Alpha值:\n" +
                        "当设置透明度setAlpha的时候，android里默认会把当前view绘制到offscreen buffer中，然后再显示出来。 这个offscreen buffer 可以理解为一个临时缓冲区，把当前View放进来并做透明度的转化，然后在显示到屏幕上,在对带有Alpha属性的View渲染时，会先对View做一次rgb的渲染，然后在对第一次渲染的结果做Alpha处理，如此会对该View渲染两次,可以直接调用对应组件设置Alpha的方法\n\n" +
                        "- 优先使用固定大小或者match_parent属性:\n" +
                        "避免频繁测量\n\n" +
                        "- 动画优化:\n" +
                        "如果只是单纯的做动画，不动态修改 View 的内容，那么性能表现为 ：Hardware Layer >= Software Layer > Normal Layer\n" +
                        "如果做动画同时动态修改 View 的内容，那么性能表现为 ：Normal Layer > Software Layer = Hardware Layer");
                break;
            case 8:
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View view = inflater.inflate(R.layout.test_api, (ViewGroup) getView(),true);
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        FrameLayout layout1 = view.findViewById(R.id.test1);
                        FrameLayout layout2 = view.findViewById(R.id.test2);
                        FrameLayout layout3 = view.findViewById(R.id.test3);
                        TextView text1 = layout1.findViewById(R.id.test1text);
                        TextView text2 = layout1.findViewById(R.id.test2text);
                        TextView text3 = layout1.findViewById(R.id.test3text);
                        Rect rect1 = new Rect();
                        Rect rect2 = new Rect();
                        Rect rect3 = new Rect();
                        Rect rect4 = new Rect();
                        layout1.getGlobalVisibleRect(rect1);
                        layout1.getLocalVisibleRect(rect2);
                        int[] location = new int[2];
                        int[] location2 = new int[2];
                        layout1.getLocationInWindow(location);
                        layout1.getLocationOnScreen(location2);
                        rect3.left = location[0];
                        rect3.top = location[1];
                        rect3.right = rect3.left + layout1.getWidth();
                        rect3.bottom = rect3.top + layout1.getHeight();

                        rect4.left = location2[0];
                        rect4.top = location2[1];
                        rect4.right = rect4.left + layout1.getWidth();
                        rect4.bottom = rect4.top + layout1.getHeight();
//                        layout2.getGlobalVisibleRect(rect2);
//                        layout3.getGlobalVisibleRect(rect3);
//                        text1.setText(rect1.toString());
//                        text2.setText(rect2.toString());
//                        text3.setText(rect3.toString());
                        System.out.println("gyz123 getGlobalVisibleRect = " + rect1 + " getLocalVisibleRect = " + rect2 + " getLocationInWindow = " + rect3 + " getLocationOnScreen = " + rect4);
                        System.out.println("gyz123 getStatusBarHeight = " + ASCUtil.getStatusBarHeight(getActivity()) + " getNavigationBarHeight = " + ASCUtil.getNavigationBarHeight(getActivity()));
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int[] location = new int[2];
                                layout1.getLocationInWindow(location);
                            }
                        }).start();
                    }
                });
                break;
        }
    }
}
