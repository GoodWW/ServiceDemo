package com.example.zhang.servicedemo.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.zhang.servicedemo.Binder.MyBinder;
import com.example.zhang.servicedemo.R;
import com.example.zhang.servicedemo.Service.MyService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "zrw";
    private MyBinder myBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //第5步所说的在Activity里面取得Service里的binder对象
            myBinder = (MyBinder) iBinder;
            myBinder.setOnTestListener(new MyBinder.OnTestListener() {
                @Override
                public void onTest(String str) {
                    Log.e(TAG, "   onTest:   收到从服务发来的消息" + str);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);
        findViewById(R.id.main_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBinder.testMethod("Hi,   Service   我想向你打招呼了");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "   onStop:   " + "stop");
    }
}
