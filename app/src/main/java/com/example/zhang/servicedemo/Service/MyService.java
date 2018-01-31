package com.example.zhang.servicedemo.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.zhang.servicedemo.Binder.MyBinder;

public class MyService extends Service {

    private static final String TAG = "zrw";
    private MyBinder myBinder = new MyBinder(this);

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
    public void serviceMethod(String str){
        Log.e(TAG, "   serviceMethod:   " + str);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "   onDestroy:   " + "服务关闭");
    }
}
