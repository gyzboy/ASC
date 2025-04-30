package com.gyz.androidsamplecode.component.activity.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.gyz.androidsamplecode.R;

public class ActivityB extends Activity {
   private StringBuilder sb = new StringBuilder();
   TextView tv;
   LinearLayout ll;
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_layout);
      tv = findViewById(R.id.content);
      sb.append("onCreate");
   }

   @Override
   protected void onStart() {
      super.onStart();
      sb.append("onStart\n");
      tv.setText(sb.toString());
   }

   @Override
   protected void onRestart() {
      super.onRestart();
      sb.append("onRestart\n");
      tv.setText(sb.toString());
   }

   @Override
   protected void onResume() {
      super.onResume();
      sb.append("onResume\n");
      tv.setText(sb.toString());
   }

   @Override
   protected void onNewIntent(Intent intent) {
      super.onNewIntent(intent);
      sb.append("onNewIntent\n");
      tv.setText(sb.toString());
   }

   @Override
   protected void onPause() {
      super.onPause();
      sb.append("onPause\n");
      tv.setText(sb.toString());
   }

   @Override
   protected void onStop() {
      super.onStop();
      sb.append("onStop\n");
      tv.setText(sb.toString());
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      sb.append("onDestroy\n");
      tv.setText(sb.toString());
      setResult(0);
   }

   @Override
   protected void onSaveInstanceState(@NonNull Bundle outState) {
      super.onSaveInstanceState(outState);
      sb.append("onSaveInstanceState\n");
      tv.setText(sb.toString());
   }

   @Override
   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      sb.append("onAttachedToWindow\n");
      tv.setText(sb.toString());
   }

   @Override
   public void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      sb.append("onDetachedFromWindow\n");
      tv.setText(sb.toString());
   }
}
