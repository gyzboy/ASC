package com.gyz.androidsamplecode.communication.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;


public class ExampleService extends Service {

    private final IExampleService.Stub mBinder = new IExampleService.Stub() {
        @Override
        public void processData(MyData inputData) throws RemoteException {
            // Process inputData, but cannot modify it for return
        }

        @Override
        public void updateData(MyData outputData) throws RemoteException {
            // Initialize and modify outputData
            outputData.number = 42;
            outputData.text = "Updated";
        }

        @Override
        public void modifyData(MyData data) throws RemoteException {
            // Modify data and return modifications
            data.number *= 2;
            data.text += " Modified";
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}