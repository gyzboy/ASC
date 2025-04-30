package com.gyz.androidsamplecode.system;

import android.os.Bundle;

import android.support.annotation.Nullable;

import com.gyz.androidsamplecode.BaseFragmentActivity;
import com.gyz.androidsamplecode.render.choreographer.ChoreographerFragment;
import com.gyz.androidsamplecode.render.view.ViewFragment;
import com.gyz.androidsamplecode.render.window.WindowFragment;

public class ASCSystemActivity extends BaseFragmentActivity {
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      fragmentTreeMap.put("Process",new ProcessFragment());
      super.onCreate(savedInstanceState);
   }
}
