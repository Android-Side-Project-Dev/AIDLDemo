package com.asn.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {

    // 實例化AIDL的Stub類(Binder的子類)
    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void AIDL_Service() throws RemoteException {
            System.out.println("客戶端通過AIDL與遠程後台成功通信");
        }
    };

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("執行了onCreate()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("執行了onStartCommand()");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        System.out.println("執行了onDestory()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("執行了onBind()");
        return mBinder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("執行了onUnbind()");
        return super.onUnbind(intent);
    }
}