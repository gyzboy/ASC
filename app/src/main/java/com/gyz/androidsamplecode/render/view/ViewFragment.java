package com.gyz.androidsamplecode.render.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.gyz.androidsamplecode.BaseFragment;

import org.jetbrains.annotations.Nullable;

public class ViewFragment extends BaseFragment {

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      opts.add("CustomView");
      opts.add("TextView");
      opts.add("ImageView");
      opts.add("ScrollView");
      opts.add("RecyclerView");
      opts.add("FrameLayout");

      optFragments.add(new CustomViewFragment());
      optFragments.add(new TextViewFragment());
      optFragments.add(new ImageFragment());
      optFragments.add(new ScrollerFragment());
      optFragments.add(new RecyclerViewFragment());
      optFragments.add(new FrameLayoutFragment());
   }

   @Override
   public void handleClick(View v) {
      int index = (int) v.getTag();
      switchFragment(optFragments.get(index));
   }
}
