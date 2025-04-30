package com.gyz.androidsamplecode;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ASCUtil {
   public static String actionToStr(int index){
      String ret = "DOWN";
      switch (index) {
         case 0:
            ret = "DOWN";
            break;
         case 1:
            ret = "UP";
            break;
         case 2:
            ret = "MOVE";
            break;
         case 3:
            ret = "CANCEL";
            break;
      }
      return ret;
   }

   public static void captureLogcat(TextView textView,String tag) {
      new Thread(() -> {
         try {
            Thread.sleep(500);
            // 执行logcat命令
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
               if (line.contains(tag)) {
                  log.append(line.split(tag)[1]).append("\n");
               }
            }
            textView.post(() -> textView.setText(log.toString()));
         } catch (IOException | InterruptedException e) {
            e.printStackTrace();
         }
      }).start();
   }

   public static void cleanLogcat() {
      try {
         Process process = Runtime.getRuntime().exec("logcat -c");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
