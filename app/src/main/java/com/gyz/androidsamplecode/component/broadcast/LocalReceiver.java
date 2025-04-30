package com.gyz.androidsamplecode.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// 本地广播，只限于应用内接收使用
public class LocalReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Toast.makeText(context, "Received Broadcast: " + message, Toast.LENGTH_SHORT).show();
    }
}