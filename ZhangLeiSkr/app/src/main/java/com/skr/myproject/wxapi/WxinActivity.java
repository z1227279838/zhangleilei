package com.skr.myproject.wxapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.skr.myproject.R;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WxinActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq);
    }
}
