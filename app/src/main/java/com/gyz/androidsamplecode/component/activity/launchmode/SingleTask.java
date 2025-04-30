package com.gyz.androidsamplecode.component.activity.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.annotation.Nullable;

public class SingleTask extends Activity {
//    描述：该模式确保Activity在任务栈中只有一个实例。如果实例已经存在，则将其上方的所有Activity出栈，
//    并调用该实例的onNewIntent()方法。

//    使用场景：适用于需要在应用中保持唯一实例的Activity，如主屏幕或主页Activity。

//    行为：
//    如果Activity实例存在于任何任务栈中，则将其上方的所有Activity出栈，复用该实例，并调用其onNewIntent()方法。
//    如果不存在，则创建新的实例，并放入一个新的或现有的任务栈中。

//    示例：
//    现在栈的结构为：A B C D。A通过singleTask模式启动B，B位于新的栈中，此时D通过Intent跳转到B，
//    则栈的结构变成了：A B。其中的C和D被栈弹出销毁了，也就是说位于B之上的实例都被销毁了
//    会调用B的onNewIntent方法
    private TextView textView;
    private StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button button = new Button(this);
        button.setText("Jump To SingleTask");
        button.setOnClickListener(v -> {
            Intent intent = new Intent(SingleTask.this,SingleTask.class);
            startActivity(intent);
        });
        textView = new TextView(this);
        sb.append("adb shell dumpsys activity activities | grep ActivityRecord 查看任务栈").append("\nonCreate");
        textView.setText(sb.toString());
        layout.addView(button);
        layout.addView(textView);
        setContentView(layout);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        sb.append("\nonNewIntent");
        textView.setText(sb.toString());
    }
}
