package com.gyz.androidsamplecode.component.service;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gyz.androidsamplecode.R;

public class ASCServiceActivity extends Activity {

    private LinearLayout container;
    private MyConnect connect;
    private boolean isBinded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        connect = new MyConnect();
        Button btn1 = new Button(this);
        Button btn2 = new Button(this);
        Button btn3 = new Button(this);
        btn1.setText("startService");
        btn2.setText("bindService");
        btn3.setText("unBindService");
        btn1.setOnClickListener(v -> {
            Intent intent = new Intent(ASCServiceActivity.this, ASCService.class);
            startService(intent);
        });
        btn2.setOnClickListener(v -> {
            Intent intent = new Intent(ASCServiceActivity.this, ASCService.class);
            bindService(intent, connect, BIND_AUTO_CREATE);
            isBinded = true;
        });
        btn3.setOnClickListener(v -> {
            // unbind多次，会crash
            unbindService(connect);
            isBinded = false;
        });
        container = findViewById(R.id.btn_container);
        container.addView(btn1);
        container.addView(btn2);
        container.addView(btn3);
    }

    private class MyConnect implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBinded) {
            unbindService(connect);
        }
        Intent intent = new Intent(ASCServiceActivity.this, ASCService.class);
        stopService(intent);
    }
}