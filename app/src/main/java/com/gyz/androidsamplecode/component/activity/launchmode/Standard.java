package com.gyz.androidsamplecode.component.activity.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Standard extends Activity {
//    描述：这是默认的启动模式。每次启动Activity时，都会创建Activity的新实例，并将其放入启动它的任务栈中。

//    使用场景：适用于不需要管理实例数量的普通Activity。

//    行为：
//    每次启动都会创建一个新的Activity实例。
//    Activity实例可以在同一个任务栈中存在多次。

//    示例：
//    栈中顺序是A B C D ，此时D通过Intent跳转到A，那么栈中结构就变成 A B C D A ，
//    点击返回按钮的 显示顺序是 D C B A，依次摧毁
    private TextView textView;
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button button = new Button(this);
        button.setText("Jump To Standard");
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Standard.this, Standard.class);
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
