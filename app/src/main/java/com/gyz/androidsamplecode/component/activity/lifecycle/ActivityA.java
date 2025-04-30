package com.gyz.androidsamplecode.component.activity.lifecycle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.gyz.androidsamplecode.R;

public class ActivityA extends Activity {
   private StringBuilder sb = new StringBuilder();
   TextView tv;
   LinearLayout ll;
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_layout);
      tv = findViewById(R.id.content);
      ll = findViewById(R.id.btn_container);
      Button btn1 = new Button(this);
      // onRestart->onStart->onResume
      btn1.setText("startActivityB standard");
      btn1.setOnClickListener(v -> {
         Intent intent = new Intent(ActivityA.this,ActivityB.class);
         startActivity(intent);
      });
      // onStop->onSaveInstance->onRestart->onStart->onActivityResult->onResume
      Button btn2 = new Button(this);
      btn2.setText("startActivityBForResult standard");
      btn2.setOnClickListener(v -> {
         Intent intent = new Intent(ActivityA.this,ActivityB.class);
         startActivityForResult(intent,0);
      });
      // onPause->onNewIntent->onResume
      Button btn3 = new Button(this);
      btn3.setText("startActivityA singleTop");
      btn3.setOnClickListener(v -> {
         Intent intent = new Intent(ActivityA.this,ActivityA.class);
         intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
         startActivity(intent);
      });

      Button btn4 = new Button(this);
      // dialog不影响生命周期
      btn4.setText("show dialog");
      btn4.setOnClickListener(v -> {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle("Dialog Title");
         builder.setMessage("This is a simple dialog message.");
         builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
         });
         builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
         });
         AlertDialog dialog = builder.create();
         dialog.show();
      });
      ll.addView(btn1);
      ll.addView(btn2);
      ll.addView(btn3);
      ll.addView(btn4);
      sb.append("onCreate\n");
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

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      sb.append("onActivityResult\n");
      tv.setText(sb.toString());
   }
}
