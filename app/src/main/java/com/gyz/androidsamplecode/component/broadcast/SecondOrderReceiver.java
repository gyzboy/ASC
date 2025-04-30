package com.gyz.androidsamplecode.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SecondOrderReceiver extends BroadcastReceiver {
   @Override
   public void onReceive(Context context, Intent intent) {
      String message = intent.getStringExtra("message");
      String additionalData = getResultExtras(true).getString("additionalData");
      Toast.makeText(context, "Second Receiver: " + message + ", Extra: " + additionalData, Toast.LENGTH_SHORT).show();
   }
}
