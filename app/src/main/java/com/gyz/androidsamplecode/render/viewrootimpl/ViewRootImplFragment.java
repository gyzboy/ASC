package com.gyz.androidsamplecode.render.viewrootimpl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;


import com.gyz.androidsamplecode.BaseFragment;

public class ViewRootImplFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opts.add("主要职责");
        opts.add("关键方法");
        opts.add("工作流程");
        opts.add("关联组件");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void handleClick(View v) {
        int index = (int) v.getTag();
        switch (index) {
            case 0:
                content.setText("ViewRootImpl是连接Window和View的桥梁，负责处理View树的测量、布局和绘制，同时处理输入事件等：\n" +
                        "   - ViewRootImpl是视图树的根节点，负责从窗口管理器接收输入事件并将其分发给视图层次结构中的合适视图进行处理。\n" +
                        "事件分发：\n" +
                        "   - 处理来自用户的输入事件（如触摸事件、键盘事件），并将这些事件分发给视图树中的各个视图。\n" +
                        "绘制和布局：\n" +
                        "   - 负责触发视图的测量（measure）、布局（layout）和绘制（draw）过程。通过与Choreographer协作，协调视图的刷新和重绘。\n" +
                        "与WindowManager的交互：\n" +
                        "   - 通过WindowManager与系统窗口进行交互，处理窗口的添加、删除和更新。负责管理窗口的大小和位置变化。");
                break;
            case 1:
                content.setText("performTraversals()：\n" +
                        "   - 负责执行视图树的测量、布局和绘制过程。在视图树需要重新布局或重绘时调用。\n" +
                        "dispatchInputEvent()：\n" +
                        "   - 处理输入事件的分发，将事件传递给视图树中的合适视图进行处理。\n" +
                        "scheduleTraversals()：\n" +
                        "   - 调度视图树的遍历过程，包括测量、布局和绘制。\n" +
                        "requestLayout()：\n" +
                        "   - 请求重新布局视图树，通常在视图的尺寸或位置发生变化时调用。\n" +
                        "checkThread():\n" +
                        "   - 验证当前线程是否是创建 ViewRootImpl 的原始线程，当触发 invalidate、requestLayout、焦点变化时会进行线程检测");
                break;
            case 2:
                content.setText("初始化：\n" +
                        "   - 当一个窗口被创建时(onResume时)，ViewRootImpl被初始化，并与WindowManager关联。\n" +
                        "输入事件处理：\n" +
                        "   - 接收来自InputManager的输入事件并进行分发。\n" +
                        "视图树遍历：\n" +
                        "   - 通过performTraversals()方法，依次执行视图树的测量、布局和绘制。\n" +
                        "窗口管理：\n" +
                        "   - 通过WindowManager处理窗口的添加、删除和更新。");
                break;
            case 3:
                content.setText("WindowManager：\n" +
                        "   - 管理应用窗口的显示和布局，通过ViewRootHandler与WindowManager协作进行窗口管理。\n" +
                        "Choreographer：\n" +
                        "   - 协调视图的刷新和重绘，确保动画和视图更新的平滑性。\n" +
                        "InputManager：\n" +
                        "   - 通过InputChannel建立输入通道，通过InputEventReceiver进行事件的接收，调用dispatchInputEvent分发事件。");
                break;

        }
    }
}
