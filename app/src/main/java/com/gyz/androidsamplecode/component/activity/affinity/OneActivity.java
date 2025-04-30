package com.gyz.androidsamplecode.component.activity.affinity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyz.androidsamplecode.R;
import com.gyz.androidsamplecode.component.activity.ASCActivityUtil;

import java.util.TreeMap;


public class OneActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_layout);
		LinearLayout layout = findViewById(R.id.btn_container);
		Button button = new Button(this);
		button.setText("添加newTask Flag");
		Button button2 = new Button(this);
		button.setOnClickListener(v -> {
			//taskAffnity的几种情况:
			//1、与FLAG_ACTIVITY_NEW_TASK或者LaunchMode="singleTask"一起使用的话，新的Activity会在新的task中
			//2、不使用FLAG_ACTIVITY_NEW_TASK，无论如何设置Activity的taskAffnity属性都会在同一个task中

			//allowTaskReparenting
			//允许两个应用的activity存在于同一个task中
			startActivity(new Intent(OneActivity.this,SecondActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
		});
		button2.setText("未添加跳转flag");
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(OneActivity.this,SecondActivity.class));
			}
		});
		TextView textView = findViewById(R.id.content);
		textView.setText(ASCActivityUtil.printActivityInfo(this));
		layout.addView(button);
		layout.addView(button2);
	}
}
