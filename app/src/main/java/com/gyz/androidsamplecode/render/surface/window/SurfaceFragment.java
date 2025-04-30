package com.gyz.androidsamplecode.render.surface.window;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;


import com.gyz.androidsamplecode.BaseFragment;
import com.gyz.androidsamplecode.R;
import com.gyz.androidsamplecode.render.view.PinchImageView;

public class SurfaceFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opts.add("Surface 是什么");
        opts.add("Surface 与 Window、视图对应关系");
        opts.add("Surface 数量计算方式");
        opts.add("Surface 与硬件加速");
        opts.add("Surface 过多时性能问题");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PinchImageView imageView = new PinchImageView(getActivity());
        imageView.setImageResource(R.mipmap.ic_launcher);
        ((ViewGroup)view).addView(imageView);
    }

    @Override
    public void handleClick(View v) {
        int index = (int) v.getTag();
        sb.setLength(0);
        switch (index) {
            case 0:
                sb.append("Surface 的核心职责是管理图形缓冲区（Graphic Buffer），这些缓冲区是存储图像数据的物理内存区域。每个 Surface 维护一个 BufferQueue，遵循生产者-消费者模型\n" +
                        "   - 生产者（Producer）：应用或渲染引擎（如 Canvas、OpenGL ES、MediaCodec）将绘制内容写入缓冲区\n" +
                        "   - 消费者（Consumer）：通常是 SurfaceFlinger（显示合成服务）或另一个 Surface（如视频播放器），从缓冲区读取数据并显示");
                break;
            case 1:
                sb.append("一个 Window 持有一个 Surface\n" +
                        "   - Activity 窗口：每个 Activity 的顶层窗口默认关联一个 Surface\n" +
                        "   - Dialog/PopupWindow：每个独立弹窗（非嵌入式的 Dialog 或 PopupWindow）会创建新的 Surface\n" +
                        "   - 多窗口模式：分屏、画中画等场景下，每个可见窗口独立拥有 Surface\n" +
                        "特殊视图组件\n" +
                        "   - SurfaceView：每个 SurfaceView 实例会创建独立的 Surface（独立于 Activity 的 Surface）\n" +
                        "   - TextureView：通过 SurfaceTexture 间接使用 Surface，通常共享 Activity 的 Surface");
                break;
            case 2:
                sb.append("adb shell dumpsys SurfaceFlinger | grep \"SurfaceView\"");
                break;
            case 3:
                sb.append(" - 启用硬件加速时，普通 View 的绘制共享 Activity 的 Surface。\n" +
                        " - 禁用硬件加速时，某些复杂视图可能生成离屏 Surface（通过 Canvas 软件渲染）。");
                break;
            case 4:
                sb.append(" - 内存消耗：每个 Surface 至少包含 2 个图形缓冲区（双缓冲），高分辨率下内存占用显著。\n" +
                        " - 合成开销：SurfaceFlinger 需要合成多个 Surface，数量过多可能导致掉帧。");
                break;
        }
        content.setText(sb.toString());
    }
}
