package com.gyz.androidsamplecode.component.activity.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.support.annotation.Nullable;

import com.gyz.androidsamplecode.R;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ASCLaunchMode extends Activity {
    private TreeMap<String,Class<?>> modes = new TreeMap<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        modes.put("Standard", Standard.class);
        modes.put("SingleTop", SingleTop.class);
        modes.put("SingleTask", SingleTask.class);
        modes.put("SingleInstance", SingleInstance.class);
        LinearLayout layout = findViewById(R.id.btn_container);
        for (Map.Entry<String, Class<?>> stringClassEntry : modes.entrySet()) {
            Button button = new Button(this);
            button.setText(stringClassEntry.getKey());
            button.setOnClickListener(v -> {
                Intent intent = new Intent(ASCLaunchMode.this,modes.get(stringClassEntry.getKey()));
                startActivity(intent);
            });
            layout.addView(button);
        }
    }
}
