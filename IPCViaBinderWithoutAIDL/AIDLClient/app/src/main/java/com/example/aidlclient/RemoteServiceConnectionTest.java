package com.example.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.aidlserver.IMyRemoteService;

public class RemoteServiceConnectionTest implements ServiceConnection {
    final private String TAG = "RemoteServiceConnTest";
    IMyRemoteService serverStb = null;
    RemoteServiceConnectionTest testServiceCon = null;
    Context mContext = null;
    RemoteServiceConnectionTest(Context context) {
        mContext = context;
        testServiceCon = this;
    }

    void BindToRemoteService(Context context) {
        Intent binderIntent = new Intent();
        binderIntent.setComponent(
                new ComponentName("com.example.aidlserver",
                        "com.example.aidlserver.TestService"));
        context.bindService(binderIntent,testServiceCon,Context.BIND_AUTO_CREATE);
        if(testServiceCon.serverStb != null) {
            try {
                Log.e(TAG, "ConnectionResult = " + testServiceCon.serverStb.GetServiceConnectionTestResult(0));
                } catch (Exception e) {
                Log.e(TAG, "Exception e=" + e.getMessage());
            }
        }
    }
    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        Log.e(TAG,"in onServiceConnected return num - called ");
        serverStb = IMyRemoteService.Stub.asInterface(binder);
        try {
            int num = serverStb.GetServiceConnectionTestResult(0);

            Log.e(TAG,"in onServiceConnected return num - "+ num);
        } catch(RemoteException e) {
            Log.e(TAG,"-->RemoteException in onServiceConnected");
        }
    }
    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.e(TAG,"in onServiceDisconnected  called ");

    }
}
