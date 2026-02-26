package com.gyz.androidsamplecode;

import android.os.Bundle;
import android.os.Trace;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BaseFragmentActivity extends FragmentActivity {
   protected LinkedHashMap<String, Fragment> fragmentTreeMap = new LinkedHashMap<>();

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_layout);
      List<String> keyList = new ArrayList<>(fragmentTreeMap.keySet());
      Spinner container = findViewById(R.id.fragment_type);
      container.setVisibility(View.VISIBLE);
      ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
              android.R.layout.simple_spinner_item, keyList);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      container.setAdapter(adapter);
      container.setSelection(0);
      container.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String key = keyList.get(position);
            Trace.beginSection("gyzzzSwitchFragment");
            switchFragment(fragmentTreeMap.get(key));
            Trace.endSection();
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
      });
   }

   private void switchFragment(Fragment fragment) {
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

      // Replace the current fragment with a new fragment
      fragmentTransaction.replace(R.id.fragment_container, fragment);

      // Optionally add the transaction to the back stack
//        fragmentTransaction.addToBackStack(null);

      // Commit the transaction
      fragmentTransaction.commit();
   }
}
