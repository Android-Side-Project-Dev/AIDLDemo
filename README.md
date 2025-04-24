# AIDLDemo


![image](https://github.com/user-attachments/assets/621afc2a-be28-4eca-b1c0-0f25a4e8e476)
![image](https://github.com/user-attachments/assets/2bc384aa-0262-4586-8f3b-65102e4f7683)



以下是兩個進程角色的具體使用步驟：  
服務器端（Service）  
步驟1：新建定義AIDL文件，並聲明該服務需要向客戶端提供的接口  
步驟2：在Service子類中實現AIDL中定義的接口方法，並定義生命週期的方法（onCreat、onBind()、blabla）  
步驟3：在AndroidMainfest.xml中註冊服務 & 聲明為遠程服務  
客戶端（Client）  
步驟1：拷貝服務端的AIDL文件到目錄下  
步驟2：使用Stub.asInterface接口獲取服務器的Binder，根據需要調用服務提供的接口方法  
步驟3：通過Intent指定服務端的服務名稱和所在包，綁定遠程Service  

特別注意  
AIDL File 的packageName都要與Server一致  
AIDL File 裡面的api儘量一致，不然會某一方找不到方法  
Server的Service在Manifest中定義intent filter來接收Client的action  
Server定義AIDL方法的實作  
Server定義Service讓Client來進行綁定  
Client Manifest定義quries 訪問Server  
Client bindService進行綁定  
