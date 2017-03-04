package com.example.jason.dinner_rush;

import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ConnectActivity extends AppCompatActivity {

    NsdHelper mNsdHelper;

    private TextView mStatusView;
    private Handler mUpdateHandler;

    public static final String TAG = "ConnectActivity";

    DataConnection mConnection;
    Handler autoConnectHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        mStatusView = (TextView) findViewById(R.id.status);

        mUpdateHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String chatLine = msg.getData().getString("msg");
                addChatLine(chatLine);
            }
        };
    }

    public void clickAdvertise(View v) {
        // Register service
        if(mConnection.getLocalPort() > -1) {
            mNsdHelper.registerService(mConnection.getLocalPort());
        } else {
            Log.d(TAG, "ServerSocket isn't bound.");
        }
    }

    public void clickDiscover(View v) {
        mNsdHelper.discoverServices();
    }

    public void clickConnect(View v) {
        NsdServiceInfo service = mNsdHelper.getChosenServiceInfo();
        if (service != null) {
            Log.d(TAG, "Connecting.");
            mConnection.connectToServer(service.getHost(),
                    service.getPort());
        } else {
            Log.d(TAG, "No service to connect to!");
        }
    }

    public void clickSend(View v) {
        EditText messageView = (EditText) this.findViewById(R.id.chatInput);
        if (messageView != null) {
            String messageString = messageView.getText().toString();
            if (!messageString.isEmpty()) {
                mConnection.sendMessage(messageString);
            }
            messageView.setText("");
        }
    }

    public void addChatLine(String line) {
        mStatusView.append("\n" + line);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Starting.");
        mConnection = new DataConnection(mUpdateHandler);

        mNsdHelper = new NsdHelper(this);
        mNsdHelper.initializeNsd();

        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Pausing.");
        autoConnectHandler.removeCallbacksAndMessages(null);
        if (mNsdHelper != null) {
            mNsdHelper.stopDiscovery();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Resuming.");
        super.onResume();
        if (mNsdHelper != null) {
            mNsdHelper.discoverServices();
        }
        autoConnect();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Being stopped.");
        mNsdHelper.tearDown();
        mConnection.tearDown();
        mNsdHelper = null;
        mConnection = null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Being destroyed.");
        super.onDestroy();
    }

    protected void autoConnect() {
        // Register service
        if(mConnection.getLocalPort() > -1) {
            mNsdHelper.registerService(mConnection.getLocalPort());
        } else {
            Log.d(TAG, "ServerSocket isn't bound.");
        }

        autoConnectHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NsdServiceInfo service = mNsdHelper.getChosenServiceInfo();
                if(service == null) {
                    mNsdHelper.discoverServices();
                    autoConnectHandler.postDelayed(this, 3000);
                } else {
                    Log.d(TAG, "Connecting.");
                    mConnection.connectToServer(service.getHost(),
                            service.getPort());
                    return;
                }
            }
        }, 1);
    }
}
