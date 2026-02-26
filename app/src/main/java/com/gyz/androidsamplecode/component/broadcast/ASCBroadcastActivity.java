package com.gyz.androidsamplecode.component.broadcast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.widget.Button;
import android.widget.LinearLayout;


import com.gyz.androidsamplecode.R;

public class ASCBroadcastActivity extends Activity {

    private BroadcastReceiver dyCast;
    private BroadcastReceiver localCast;
    private BroadcastReceiver firstCast;
    private BroadcastReceiver secondCast;
    private LocalBroadcastManager localBroadcastManager;

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dyCast = new DynamicReceiver();
        localCast = new LocalReceiver();
        firstCast = new FirstOrderReceiver();
        secondCast = new SecondOrderReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        setContentView(R.layout.activity_layout);
        LinearLayout container = findViewById(R.id.btn_container);
        Button btn1 = new Button(this);
        btn1.setText("send static broadcast");
        btn1.setOnClickListener(v -> {
            // 从Android 8.0（API级别26）开始，系统对隐式广播进行了限制，许多隐式广播无法通过静态注册接收
//            Intent intent = new Intent("STATIC_BROADCAST");
            Intent intent = new Intent(ASCBroadcastActivity.this,StaticReceiver.class);
            intent.putExtra("message","this is from static broadCast");
            sendBroadcast(intent);
        });

        Button btn2 = new Button(this);
        btn2.setText("register broadcast");
        btn2.setOnClickListener(v -> {
            IntentFilter filter = new IntentFilter();
            filter.addAction("dynamicBroadcast");
            registerReceiver(dyCast,filter);
        });

        Button btn3 = new Button(this);
        btn3.setText("send broadcast");
        btn3.setOnClickListener(v -> {

            Intent intent = new Intent("dynamicBroadcast");
            intent.putExtra("message","this is from static broadCast");
            sendBroadcast(intent);
        });

        Button btn4 = new Button(this);
        btn4.setText("unregister broadcast");
        btn4.setOnClickListener(v -> unregisterReceiver(dyCast));

        Button btn5 = new Button(this);
        btn5.setText("register local broadcast");
        btn5.setOnClickListener(v -> {
            IntentFilter filter = new IntentFilter();
            filter.addAction("localBroadcast");
            localBroadcastManager.registerReceiver(localCast,filter);
        });

        Button btn6 = new Button(this);
        btn6.setText("send local broadcast");
        btn6.setOnClickListener(v -> {
            Intent intent = new Intent("localBroadcast");
            intent.putExtra("message","this is from local broadCast");
            localBroadcastManager.sendBroadcast(intent);
        });

        Button btn7 = new Button(this);
        btn7.setText("unregister local broadcast");
        btn7.setOnClickListener(v -> localBroadcastManager.unregisterReceiver(localCast));


        Button btn8 = new Button(this);
        btn8.setText("send order broadcast");
        btn8.setOnClickListener(v -> {
            // 从Android 8.0（API级别26）开始，系统对隐式广播进行了限制，许多隐式广播无法通过静态注册接收
            Intent intent = new Intent("orderedBroadcast");
            intent.putExtra("message","this is from order broadCast");
            sendOrderedBroadcast(intent,null);
        });


        container.addView(btn1);
        container.addView(btn2);
        container.addView(btn3);
        container.addView(btn4);
        container.addView(btn5);
        container.addView(btn6);
        container.addView(btn7);
        container.addView(btn8);
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter("orderedBroadcast");
        filter.setPriority(2); // Set priority for ordering
        registerReceiver(firstCast, filter);

        filter.setPriority(1); // Set different priority
        registerReceiver(secondCast, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(firstCast);
        unregisterReceiver(secondCast);
    }
}