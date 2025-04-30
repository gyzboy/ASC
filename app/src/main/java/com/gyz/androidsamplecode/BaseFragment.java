package com.gyz.androidsamplecode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

public abstract class BaseFragment extends Fragment {
   protected TextView content;
   protected LinearLayout btn_container;
   protected static final StringBuilder sb = new StringBuilder();
   protected final ArrayList<String> opts = new ArrayList<>();
   protected final ArrayList<Fragment> optFragments = new ArrayList<>();

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      opts.clear();
      optFragments.clear();
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.activity_layout, container, false);
      content = rootView.findViewById(R.id.content);
      btn_container = rootView.findViewById(R.id.btn_container);
      return rootView;
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      for (int i = 0; i < opts.size(); i++) {
         Button button = new Button(getActivity());
         button.setText(opts.get(i));
         button.setTag(i);
         button.setOnClickListener(this::handleClick);
         btn_container.addView(button);
      }
   }

   public abstract void handleClick(View v);

   protected void switchFragment(Fragment fragment){
      getFragmentManager().beginTransaction()
              .replace(R.id.fragment_container, fragment)
              .addToBackStack(null)
              .commit();
   }

   @Override
   public void onDestroyView() {
      super.onDestroyView();
      sb.setLength(0);
   }
}
