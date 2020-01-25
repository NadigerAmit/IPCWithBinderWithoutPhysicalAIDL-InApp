package com.example.aidlclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.util.Log

class MainActivity : AppCompatActivity() {
    internal val TAG = "MainActivityClient"
    val remoteServiceConnectionTestObj =  RemoteServiceConnectionTest(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG, "Binding the remote server =" )
        bindToServerService(this)

    }

    private fun bindToServerService(context: Context) {
        Log.e(TAG, "bindToServerService is called  =" )
        remoteServiceConnectionTestObj.BindToRemoteService(this)
    }
}
