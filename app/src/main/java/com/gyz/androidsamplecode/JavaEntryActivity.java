package com.gyz.androidsamplecode;

import android.os.Bundle;

import com.gyz.androidsamplecode.communication.aidl.ASCAIDLFragment;
import com.gyz.androidsamplecode.communication.binder.ASCBinderFragment;
import com.gyz.androidsamplecode.communication.handler.ASCHandlerFragment;
import com.gyz.androidsamplecode.communication.intent.ASCIntentFragment;
import com.gyz.androidsamplecode.communication.messagener.ASCMessengerFragment;

public class JavaEntryActivity extends BaseFragmentActivity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      fragmentTreeMap.put("Binder",new ASCBinderFragment());
      fragmentTreeMap.put("AIDL",new ASCAIDLFragment());
      fragmentTreeMap.put("Messenger",new ASCMessengerFragment());
      fragmentTreeMap.put("Intent",new ASCIntentFragment());
      super.onCreate(savedInstanceState);
   }
}
