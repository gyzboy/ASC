package com.gyz.androidsamplecode.communication.messagener;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.TextView;
import android.widget.Toast;


public class MessengerService extends Service {

    private final Messenger messenger;

    public MessengerService() {
        Handler myHandler = new Handler(msg -> {
            // Handle the message from the client
            Bundle data = msg.getData();
            String receivedMessage = data.getString("message");
            Toast.makeText(getApplicationContext(), "Received: " + receivedMessage, Toast.LENGTH_SHORT).show();

            // Send a reply back to the client
            Messenger client = msg.replyTo;
            Message replyMessage = Message.obtain(null, 2);
            Bundle replyData = new Bundle();
            replyData.putString("reply", "Reply from Service");
            replyMessage.setData(replyData);
            try {
                client.send(replyMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return true;
        });
        messenger = new Messenger(myHandler);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}