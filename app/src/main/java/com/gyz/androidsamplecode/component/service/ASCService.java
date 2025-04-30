package com.gyz.androidsamplecode.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ASCService extends Service {

    private static final String TAG = "ASCDemo-" + ASCService.class.getSimpleName();

    private Binder binder;

    public ASCService() {
         binder = new MyBinder();
    }

    // 只会调用一次
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println(TAG + " onCreate");
    }

//    多次startService会多次调用onStartCommand
//    返回值决定了系统在服务被杀死后如何处理服务的重启：
//    START_NOT_STICKY：如果系统在onStartCommand返回后杀死了服务，则不会重新创建服务，除非有新的显式启动请求。

//    START_STICKY：如果系统在onStartCommand返回后杀死了服务，则会重新创建服务，并调用onStartCommand，
//    但传递的Intent为null，除非有挂起的Intent。

//    START_REDELIVER_INTENT：如果系统在onStartCommand返回后杀死了服务，则会重新创建服务，
//    并再次传递最后一个Intent给onStartCommand。
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(TAG + " onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    // 多次bind只会调用一次
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println(TAG + " onBind");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println(TAG + " onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println(TAG + " onDestroy");
    }

    public class MyBinder extends Binder {
         ASCService getService() {
             // Return this instance of MyService so clients can call public methods
             return ASCService.this;
         }
     }

}