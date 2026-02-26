package com.gyz.androidsamplecode.component.activity.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SingleInstance extends Activity {
//    描述：与singleTask类似，但更加严格。该模式确保Activity是其任务栈中唯一的Activity，并且该任务栈中不会有其他Activity。

//    使用场景：适用于需要完全独立于其他Activity运行的Activity，如视频播放器的全屏模式。

//    行为：
//    Activity在一个单独的任务栈中运行，该栈中没有其他Activity。每次启动该Activity时，
//    都会复用该独立任务栈中的实例，调用其onNewIntent方法。

//    示例：
//    Task栈1中结构为：A B C ，C通过Intent跳转到了D（D的模式为singleInstance），
//    那么则会新建一个Task 栈2，栈1中结构依旧为A B C，栈2中结构为D，此时屏幕中显示D，此时返回，返回到桌面。之后D通过Intent跳转到D，
//    栈2中不会压入新的D，所以2个栈中的情况没发生改变。如果D跳转到了C，那么就会根据C对应的launchMode的在栈1中进行对应的操作，
//    C如果为standard，那么D跳转到C，栈1的结构为A B C C ，此时点击返回按钮，还是在C，栈1的结构变为A B C，而不会回到D.
    private TextView textView;
    private StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button button = new Button(this);
        button.setText("Jump To SingleInstance");
        button.setOnClickListener(v -> {
            Intent intent = new Intent(SingleInstance.this,SingleInstance.class);
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
