package com.gyz.androidsamplecode.store;

import android.os.Bundle;
import android.support.annotation.Nullable;


import com.gyz.androidsamplecode.BaseFragmentActivity;

public class ASCStoreActivity extends BaseFragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentTreeMap.put("内部存储",new InternalStoreFragment());
        fragmentTreeMap.put("外部存储",new ExternalStoreFragment());
        fragmentTreeMap.put("SP存储",new SPFragment());
        fragmentTreeMap.put("数据库存储",new SQLiteFragment());
        super.onCreate(savedInstanceState);
    }
}
