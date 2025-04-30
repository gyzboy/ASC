package com.gyz.androidsamplecode.component.activity.activityinfos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import android.support.annotation.Nullable;

import com.gyz.androidsamplecode.component.activity.ASCActivityUtil;

public class ASCActivityInfo extends Activity {
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sb.append(ASCActivityUtil.printActivityInfo(this)).append(
                ASCActivityUtil.printActivityComponentInfo(this));
        TextView textView = new TextView(ASCActivityInfo.this);
        textView.setText(sb.toString());
        setContentView(textView);
    }
}
