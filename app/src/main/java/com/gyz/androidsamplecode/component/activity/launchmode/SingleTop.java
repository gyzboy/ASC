package com.gyz.androidsamplecode.component.activity.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SingleTop extends Activity {
//    描述：如果启动的Activity实例已经位于任务栈的顶部，则不会创建新的实例，而是复用该实例。

//    使用场景：适用于需要避免在栈顶重复创建实例的Activity，如处理通知的Activity。

//    行为：
//    如果Activity实例已经在栈顶，则复用该实例，并调用其onNewIntent()方法。
//    如果栈顶不是该Activity的实例，则创建新的实例。

//    示例：
//    栈中顺序是A B C D ，当前Activity D位于栈顶的时候，如果通过Intent跳转到它本身的Activity （即D），调用onNewIntent方法
//    那么不会重新创建一个新的D实例，所以栈中的结构依旧为A B C D，如果跳转到B，那么由于B不处于栈顶，
//    所以会新建一个B实例并压入到栈中，结构就变成了A B C D B
    private TextView textView;
    private StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button button = new Button(this);
        button.setText("Jump To SingleTop");
        button.setOnClickListener(v -> {
            Intent intent = new Intent(SingleTop.this,SingleTop.class);
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
