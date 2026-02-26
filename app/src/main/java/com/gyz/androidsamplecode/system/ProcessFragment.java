package com.gyz.androidsamplecode.system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.gyz.androidsamplecode.BaseFragment;

public class ProcessFragment extends BaseFragment {

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      opts.add("优先级");

   }

   @SuppressLint("SetTextI18n")
   @Override
   public void handleClick(View v) {
      int index = (int) v.getTag();
      switch (index) {
         case 0:
            content.setText("1. 前台进程（Foreground Process）\n" +
                    "描述：前台进程是用户当前正在与之交互的进程。\n" +
                    "条件：\n" +
                    "   - 包含用户正在交互的Activity（在onResume状态）。\n" +
                    "   - 包含一个正在运行的Foreground Service。\n" +
                    "   - 包含一个正在执行的BroadcastReceiver。\n" +
                    "   - 绑定到用户可见的Activity的Service。\n" +
                    "优先级：最高优先级，除非系统内存极度不足，否则不会被终止。\n\n" +
                    "2. 可见进程（Visible Process）\n" +
                    "描述：可见进程是用户当前可见但没有焦点的进程。\n" +
                    "条件：\n" +
                    "   - 包含一个对用户可见但非前台的Activity（在onPause状态）。\n" +
                    "   - 绑定到可见Activity的Service。\n" +
                    "优先级：次高优先级，通常不会被终止，除非需要为前台进程腾出空间。\n\n" +
                    "3. 服务进程（Service Process）\n" +
                    "描述：服务进程是运行一个Service且不属于前台或可见进程的进程。\n" +
                    "条件：\n" +
                    "   - 运行一个启动的Service。\n" +
                    "优先级：较高优先级，可能会被终止以支持前台和可见进程。\n\n" +
                    "4. 后台进程（Background Process）\n" +
                    "描述：后台进程是包含后台Activity的进程。\n" +
                    "条件：\n" +
                    "   - 包含一个不再对用户可见的Activity（在onStop状态）。\n" +
                    "优先级：较低优先级，容易被终止以释放内存。\n\n" +
                    "5. 空进程（Empty Process）\n" +
                    "描述：空进程不包含任何活动的应用组件，仅用于缓存以提高启动速度。\n" +
                    "条件：\n" +
                    "   - 不包含任何Activity、Service或BroadcastReceiver。\n" +
                    "优先级：最低优先级，最容易被终止。");
            break;
      }
   }
}
