package com.gyz.androidsamplecode.communication;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.gyz.androidsamplecode.BaseFragmentActivity;
import com.gyz.androidsamplecode.communication.aidl.ASCAIDLFragment;
import com.gyz.androidsamplecode.communication.binder.ASCBinderFragment;
import com.gyz.androidsamplecode.communication.handler.ASCHandlerFragment;
import com.gyz.androidsamplecode.communication.intent.ASCIntentFragment;
import com.gyz.androidsamplecode.communication.messagener.ASCMessengerFragment;

public class ASCCommActivity extends BaseFragmentActivity {


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragmentTreeMap.put("Binder",new ASCBinderFragment());
        fragmentTreeMap.put("AIDL",new ASCAIDLFragment());
        fragmentTreeMap.put("Handler",new ASCHandlerFragment());
        fragmentTreeMap.put("Messenger",new ASCMessengerFragment());
        fragmentTreeMap.put("Intent",new ASCIntentFragment());
        super.onCreate(savedInstanceState);
    }
}