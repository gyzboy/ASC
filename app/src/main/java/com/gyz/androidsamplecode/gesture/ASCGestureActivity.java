package com.gyz.androidsamplecode.gesture;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.gyz.androidsamplecode.BaseFragmentActivity;
import com.gyz.androidsamplecode.gesture.click.ClickFragment;
import com.gyz.androidsamplecode.gesture.touch.TouchFragment;

public class ASCGestureActivity extends BaseFragmentActivity {
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      fragmentTreeMap.put("Click",new ClickFragment());
      fragmentTreeMap.put("Touch",new TouchFragment());
      super.onCreate(savedInstanceState);
   }
}
