package com.skr.myproject.model;

public interface ILoginModel {
    void login(String url, String name, String pwd, ILoginCallBack iLoginCallBack);
    void regist(String url,String name,String pwd,ILoginCallBack iLoginCallBack);
    //定义接口
    interface ILoginCallBack{
        void onStatus(String data);
        void onFailed();
    }
}
