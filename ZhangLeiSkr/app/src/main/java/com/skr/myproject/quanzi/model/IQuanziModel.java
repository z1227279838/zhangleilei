package com.skr.myproject.quanzi.model;

public interface IQuanziModel {
    void getModelData(String url,ModelCallBack modelCallBack);
    interface ModelCallBack{
        void onSuccess(String data);
        void onFailed();
    }
}
