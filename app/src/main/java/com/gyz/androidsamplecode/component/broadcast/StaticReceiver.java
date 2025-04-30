package com.gyz.androidsamplecode.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

//适合需要在应用未运行时接收广播，如系统启动完成、网络状态变化等。静态注册的广播接收器在应用未运行时也有效。
public class StaticReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Toast.makeText(context, "Received Broadcast: " + message, Toast.LENGTH_SHORT).show();
    }
}