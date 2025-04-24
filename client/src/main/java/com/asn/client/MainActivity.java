package com.asn.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.asn.aidldemo.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private Button bindService;

    //定義aidl接口變量
    private IMyAidlInterface iMyAidlInterface;

    //創建ServiceConnection的匿名類
    private ServiceConnection connection = new ServiceConnection() {

        //重寫onServiceConnected()方法和onServiceDisconnected()方法
        //在Activity與Service建立關聯和解除關聯的時候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        //在Activity與Service建立關聯時調用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //IMyAidlInterface.Stub.asInterface()方法將傳入的IBinder對象轉換成了mAIDL_Service對象
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);

            try {

                //通過該對象調用在IMyAidlInterface.aidl文件中定義的接口方法,從而實現跨進程通信
                iMyAidlInterface.AIDL_Service();

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService = (Button) findViewById(R.id.bind_service);

        //設置綁定服務的按鈕
        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("點擊了[綁定服務]按鈕");

                //通過Intent指定服務端的服務名稱和所在包，與遠程Service進行綁定
                //參數與服務器端的action要一致,即"服務器包名.aidl接口文件名"
//                Intent intent = new Intent("com.asn.aidldemo.IMyAidlInterface");
//
//                //Android5.0後無法只通過隐式Intent绑定遠程Service
//                //需要通過setPackage()方法指定包名
//                intent.setPackage("com.asn.aidldemo");
//                //绑定服務,傳入intent和ServiceConnection對象
//                bindService(intent, connection, Context.BIND_AUTO_CREATE);


                Intent intent = new Intent();
                intent.setComponent(new ComponentName(
                        "com.asn.aidldemo",           // Server 的 package name
                        "com.asn.aidldemo.MyService"  // Service 的完整類名
                ));
                boolean result = bindService(intent, connection, Context.BIND_AUTO_CREATE);
                Log.d("AIDL_CLIENT", "bindService 結果: " + result);


            }
        });
    }

}