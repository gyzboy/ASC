package com.gyz.androidsamplecode.communication.aidl;

import static android.content.Context.BIND_AUTO_CREATE;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;


import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyz.androidsamplecode.R;


public class ASCAIDLFragment extends Fragment {
    private IExampleService exampleService;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            exampleService = IExampleService.Stub.asInterface(service);
            try {
                StringBuilder sb = new StringBuilder();
                MyData data = new MyData(1,"2");
                sb.append("before modify:\n");
                sb.append("number:").append(data.number).append("\n").append("text").append(data.text);
                exampleService.modifyData(data);
                sb.append("\nafter modify:\n");
                sb.append("number:").append(data.number).append("\n").append("text").append(data.text);
                if (getView() != null) {
                    TextView resultView = getView().findViewById(R.id.content);
                    resultView.setText(sb.toString());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            exampleService = null;
        }
    };
    public ASCAIDLFragment() {
        // Required empty public constructor
    }

    public static ASCAIDLFragment newInstance(String param1, String param2) {
        ASCAIDLFragment fragment = new ASCAIDLFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity(), ExampleService.class);
        if (getActivity() != null) {
            getActivity().bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_layout, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() != null) {
            getActivity().unbindService(mServiceConnection);
        }
    }
}