package com.gyz.androidsamplecode.communication.messagener;

import static android.content.Context.BIND_AUTO_CREATE;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.gyz.androidsamplecode.R;

public class ASCMessengerFragment extends Fragment {

    private Messenger serviceMessenger = null;
    private boolean isBound = false;
    private TextView content;
    private StringBuilder sb = new StringBuilder();

    // Define a Handler to handle replies from the service
    private final Handler replyHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // Handle the reply from the service
            Bundle data = msg.getData();
            String reply = data.getString("reply");
            sb.append("Service Reply:").append(reply).append("\n");
            content.setText(sb.toString());
            return true;
        }
    });

    // Create a Messenger for the reply Handler
    private final Messenger replyMessenger = new Messenger(replyHandler);

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            serviceMessenger = new Messenger(service);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            serviceMessenger = null;
            isBound = false;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_layout, container, false);
        LinearLayout layout = rootView.findViewById(R.id.btn_container);
        Button button = new Button(getActivity());
        button.setText("发送消息给Service");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBound) {
                    // Send a message to the service
                    Message msg = Message.obtain(null, 1);
                    Bundle data = new Bundle();
                    data.putString("message", "Hello from Client");
                    msg.setData(data);
                    msg.replyTo = replyMessenger;
                    try {
                        serviceMessenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        content = rootView.findViewById(R.id.content);
        layout.addView(button);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), MessengerService.class);
            getActivity().bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isBound) {
            if (getActivity() != null) {
                getActivity().unbindService(serviceConnection);
            }
            isBound = false;
        }
    }
}
