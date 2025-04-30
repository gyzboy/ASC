package com.gyz.androidsamplecode.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FirstOrderReceiver extends BroadcastReceiver {
   @Override
   public void onReceive(Context context, Intent intent) {
      String message = intent.getStringExtra("message");
      Toast.makeText(context, "First Receiver: " + message, Toast.LENGTH_SHORT).show();

      // Modify the broadcast data
      Bundle extras = getResultExtras(true);
      extras.putString("additionalData", "Data from FirstReceiver");

//      abortBroadcast();
   }
}
