package com.gyz.androidsamplecode.communication.intent;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gyz.androidsamplecode.R;

public class ASCIntentFragment extends Fragment {


   //Intent类型分为显式Intent（直接类型）、隐式Intent（间接类型）。官方建议使用隐式Intent。
   //上述属性中，component属性为直接类型，其他均为间接类型

   //Action 尽管一个Intent只可以设置一个Action，但一个Intentfilter可以持有一个或多个Action用于过滤，
   //到达的Intent只需要匹配其中一个Action即可。 深入思考：如果一个Intentfilter没有设置Action的值，那么，任何一个Intent都不会被通过；
   //反之，如果一个Intent对象没有设置Action值，那么它能通过所有的Intentfilter的Action检查
   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.activity_layout, container, false);
      TextView content = rootView.findViewById(R.id.content);
      String attrs = "Intent相关属性:\n" +
              "    Intent由以下各个组成部分：\n" +
              "    component(组件)：目的组件\n" +
              "    action（动作）：用来表现意图的行动\n" +
              "    category（类别）：用来表现动作的类别\n" +
              "    data（数据）：表示与动作要操纵的数据\n" +
              "    type（数据类型）：对于data范例的描写\n" +
              "    extras（扩展信息）：扩展信息\n" +
              "    Flags（标志位）：期望这个意图的运行模式";
      String str = "数据大小限制\n" +
              "Binder Transaction限制：\n" +
              "      Android的Binder机制有一个事务数据大小限制，通常为1MB（具体大小可能因设备和系统版本而异）。\n" +
              "      这个限制不仅包括Intent中的数据，还包括所有其他Binder事务的数据。\n\n" +
              "TransactionTooLargeException：\n" +
              "      当传递的数据超过Binder的限制时，会抛出TransactionTooLargeException。\n" +
              "      这通常发生在尝试通过Intent传递大量数据（如大图片或长字符串）时。";
      content.setText(attrs+str);
      return rootView;
   }
}
