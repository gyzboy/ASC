package com.gyz.androidsamplecode.communication.binder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gyz.androidsamplecode.BaseFragment;
import com.gyz.androidsamplecode.R;

public class ASCBinderFragment extends BaseFragment {

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      opts.add("为什么选择Binder");
   }

   @Override
   public void handleClick(View v) {
      int index = (int) v.getTag();
      switch (index) {
         case 0:
            content.setText("- 安全性：Binder机制具备更高的安全性。它通过在内核中实现的分层结构来确保通信双方的身份验证和权限检查。而Socket在默认情况下通信数据是裸露的，需要自行加密，而mmap在共享内存时也有安全隐患。\n\n" +
                    "- 性能：Binder机制在性能上表现优异，特别是在传递小数据量时。Binder使用内核中的缓冲区来共享内存并进行数据传输，避免了拷贝数据带来的开销。Socket传输需要通过网络协议栈处理，开销较大。而mmap虽然也通过共享内存来减少拷贝，但它适用于频繁的大数据块传输。\n\n" +
                    "- 简化的开发过程：Binder提供了一个透明的通信方式，隐藏了底层的复杂性，使得开发者可以更专注于业务逻辑。采用Binder进行IPC，只需要定义AIDL文件，系统会自动生成相应的业务接口。而socket需要处理复杂的网络编程，mmap也需要考虑同步机制等问题。\n\n" +
                    "- 内核支持：Binder是Android特有的IPC机制，深度集成到系统框架中，并得到内核的支持，特别是在进程间的大量通信情况下，这种深度集成能够提供很大的便利和效率提升。\n\n" +
                    "- 面向对象的编程：Binder机制允许通过Java接口来进行通信，使得开发者可以使用面向对象的方式编写IPC代码。相比之下，socket和mmap更偏底层，接口较为原始。");
            break;
      }
   }
}
