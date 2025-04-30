package com.gyz.androidsamplecode.component.activity.affinity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.gyz.androidsamplecode.component.activity.ASCActivityUtil;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText(ASCActivityUtil.printActivityInfo(this));
		setContentView(textView);
	}
}
