package com.gyz.androidsamplecode.system;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.gyz.androidsamplecode.BaseFragmentActivity;

public class ASCSystemActivity extends BaseFragmentActivity {
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      fragmentTreeMap.put("Process",new ProcessFragment());
      super.onCreate(savedInstanceState);
   }
}
