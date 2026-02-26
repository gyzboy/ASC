package com.gyz.androidsamplecode.store;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;


import com.gyz.androidsamplecode.BaseFragment;
import com.gyz.androidsamplecode.R;

public class SPFragment extends BaseFragment {

    private SharedPreferences sp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        opts.add("commit提交");
        opts.add("apply提交");
        opts.add("sp读取");
        // MODE_PRIVATE是常用的模式，表示只有本应用可以访问
        // MODE_APPEND是常用的模式，表示追加模式而不是覆盖
        // sp基于XML实现，有多线程同步+解析效率问题
        sp = getActivity().getSharedPreferences("sampleSP", MODE_PRIVATE);
    }

    @Override
    public void handleClick(View v) {
        int index = (int) v.getTag();
        sb.setLength(0);
        switch (index) {
            case 0:
                sp.edit().putString("str", "SharedPreferences写文件时，如果调用的commit(),会将数据同步写入内存中，内存数据更新，再同步写入磁盘中，在UI线程操作可能造成ANR").commit();
                break;
            case 1:
                sp.edit().putString("str", "SharedPreferences写文件时，如果调用的apply(),会将数据同步写入内存中，内存数据更新，然后异步写入磁盘，在onStop时会等待sp磁盘写入完毕").apply();
                break;
            case 2:
                sb.append("SP第一次初始化到读取到数据存在一定延迟，因为需要到文件中读取数据，因此可能会对UI线程流畅度造成一定影响，严重情况下会产生ANR\n")
                .append("SharedPreferences读取xml文件时，会以DOM方式解析（把整个xml文件直接加载到内存中解析），在调用getXXX()方法时取到的是内存中的数据，方法执行时会有个锁来阻塞，目的是等待文件加载完毕，没加载完成之前会wait()\n")
                .append("\n\nSP读取:").append(sp.getString("str", ""));
                break;
        }
        content.setText(sb.toString());
    }
}
