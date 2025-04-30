package com.gyz.androidsamplecode.component.activity.feature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gyz.androidsamplecode.R;


/**
 * Created by guoyizhe on 16/6/13.
 * 邮箱:gyzboy@126.com
 */
public class ASCWindowFeature extends Activity {
    private int featureId = Window.FEATURE_NO_TITLE;
    private LinearLayout layout;

//    requestWindowFeature()方法必须在setContentView()之前调用，因为它设置的是窗口级别的特性，而不是视图级别的
//    调用顺序：requestWindowFeature()必须在setContentView()之前调用，否则会抛出异常。
//    主题影响：某些窗口特性可能会被应用主题覆盖或影响，因此确保你的主题设置与窗口特性兼容。
//    API版本：某些特性可能仅在特定的API级别上可用，使用时请检查API兼容性。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature();的取值
        // 1.DEFAULT_FEATURES：系统默认状态，一般不需要指定
        // 2.FEATURE_CONTEXT_MENU：启用ContextMenu，默认该项已启用，一般无需指定
        // 3.FEATURE_CUSTOM_TITLE：自定义标题。当需要自定义标题时必须指定。如：标题是一个按钮时
        // 4.FEATURE_INDETERMINATE_PROGRESS：不确定的进度
        // 5.FEATURE_LEFT_ICON：标题栏左侧的图标
        // 6.FEATURE_NO_TITLE：无标题
        // 7.FEATURE_OPTIONS_PANEL：启用“选项面板”功能，默认已启用。
        // 8.FEATURE_PROGRESS：进度指示器功能
        // 9.FEATURE_RIGHT_ICON:标题栏右侧的图标
        if (getIntent() != null) {
            featureId = getIntent().getIntExtra("windowFeature",Window.FEATURE_NO_TITLE);
        }
        requestWindowFeature(featureId);
        setContentView(R.layout.activity_layout);
        layout = findViewById(R.id.btn_container);
        Button b1 = new Button(this);
        b1.setText("FEATURE_ACTION_BAR");
        b1.setOnClickListener(v -> {
            featureId = Window.FEATURE_ACTION_BAR;
            Intent intent = getIntent();
            intent.putExtra("windowFeature",Window.FEATURE_ACTION_BAR);
            startActivity(intent);
        });
        layout.addView(b1);
    }
}
