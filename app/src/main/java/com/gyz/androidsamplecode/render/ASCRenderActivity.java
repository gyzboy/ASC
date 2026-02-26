package com.gyz.androidsamplecode.render;

import android.os.Bundle;
import androidx.annotation.Nullable;


import com.gyz.androidsamplecode.BaseFragmentActivity;
import com.gyz.androidsamplecode.render.choreographer.ChoreographerFragment;
import com.gyz.androidsamplecode.render.drawable.window.DrawableFragment;
import com.gyz.androidsamplecode.render.surface.window.SurfaceFragment;
import com.gyz.androidsamplecode.render.view.ViewFragment;
import com.gyz.androidsamplecode.render.window.WindowFragment;

public class ASCRenderActivity extends BaseFragmentActivity {
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      fragmentTreeMap.put("View",new ViewFragment());
      fragmentTreeMap.put("Choreographer",new ChoreographerFragment());
      fragmentTreeMap.put("Window",new WindowFragment());
      fragmentTreeMap.put("Drawable",new DrawableFragment());
      fragmentTreeMap.put("Surface",new SurfaceFragment());
      super.onCreate(savedInstanceState);
   }
}
