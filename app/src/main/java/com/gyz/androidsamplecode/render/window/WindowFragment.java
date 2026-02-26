package com.gyz.androidsamplecode.render.window;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;


import com.gyz.androidsamplecode.BaseFragment;

public class WindowFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opts.add("系统级Window");
        opts.add("应用级Window");
        opts.add("子Window");
        opts.add("token存在的意义");
    }

    @Override
    public void handleClick(View v) {
        int index = (int) v.getTag();
        sb.setLength(0);
        switch (index) {
            case 0:
                sb.append("- (2000~2999) ：需要声明权限才能创建的Window，比如Toast和系统状态栏，不需要token");
                break;
            case 1:
                sb.append("- (1~99) ：在最下层，直接拿mAppToken");
                break;
            case 2:
                sb.append("- (1000~1999) ：不能单独存在，需要附属在特定的父Window之中，需要从decorView中拿到token");
                break;
            case 3:
                sb.append("- 拥有token的context可以创建界面、进行UI操作，而没有token的context如service、Application，是不允许添加view到屏幕上的（这里的view除了系统窗口）");
                break;
        }
        content.setText(sb.toString());
    }
}
