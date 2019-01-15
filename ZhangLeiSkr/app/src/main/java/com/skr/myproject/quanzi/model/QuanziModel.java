package com.skr.myproject.quanzi.model;

import android.util.Log;

import com.skr.myproject.network.OkHttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class QuanziModel implements IQuanziModel {
    @Override
    public void getModelData(String url, final ModelCallBack modelCallBack) {
        OkHttp.okHttpGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                modelCallBack.onFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    String s = response.body().string();
                Log.i("sssss", "onResponse: "+s);
                    modelCallBack.onSuccess(s);
            }
        });
    }
}
