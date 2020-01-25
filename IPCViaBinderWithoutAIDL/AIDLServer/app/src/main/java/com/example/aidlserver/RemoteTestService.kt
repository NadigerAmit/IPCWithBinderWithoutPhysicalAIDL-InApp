package com.example.aidlserver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import android.util.Log

class RemoteTestService : Service() {
    internal val TAG = "TestService"
    private val mBinder: IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate() = " + this.javaClass)
    }

    /** The service is starting, due to a call to startService()  */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.e(TAG, "onCreate() = " + this.javaClass)
        return Service.START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.e(TAG, " onBind() called  =")
        return mBinder
    }


    private val testBinder = object : IMyRemoteService.Stub() {
        @Throws(RemoteException::class)
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String
        ) {

        }

        @Throws(android.os.RemoteException::class)
        override fun GetServiceConnectionTestResult(param: Int): Int {
            Log.e(TAG, "Inside GetServiceConnectionTestResult returning =" + 1000)
            return 1000
        }

    }

}
