package com.example.zhang.servicedemo.Binder;

import android.os.Binder;

import com.example.zhang.servicedemo.Service.MyService;

/**
*  作者 ： Administrator
*  时间 ： 2018/1/17
*  描述： 填写类作用
*/
public class MyBinder extends Binder {

    private static final String TAG = "zrw";
    private MyService myService;
    private OnTestListener myListener;

    public MyBinder(MyService myService) {
        this.myService = myService;
    }

    public void testMethod(String str){
      myService.serviceMethod(str);
        myListener.onTest("Hi  ,  Activity  我来了 ");
    }
    //MyBinder里面提供一个注册回调的方法
    public void setOnTestListener(OnTestListener listener){
        this.myListener = listener;
    }

    /**自定义个一个回调接口*/
    public interface OnTestListener{
        void onTest(String str);
    }
}
