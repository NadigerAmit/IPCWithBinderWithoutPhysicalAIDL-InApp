package com.example.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestService extends Service {
    final String TAG = "TestService";
 //   private IBinder mBinder = null ;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate() = "+this.getClass());
       // service.startForeground();
    }

    /** The service is starting, due to a call to startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "OnStartCommand() = "+this.getClass());
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG," onBind() called ");
        return mBinder;
    }


    private IMyRemoteService.Stub mBinder = new IMyRemoteService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override public int GetServiceConnectionTestResult(int param) throws android.os.RemoteException {
            Log.e(TAG,"Inside GetServiceConnectionTestResult returning ="+ 1000);
            return 1000;
        }

    };

}
