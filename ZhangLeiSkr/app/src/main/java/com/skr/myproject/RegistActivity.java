package com.skr.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.skr.myproject.bean.RegistBean;
import com.skr.myproject.presenter.LoginPresenter;
import com.skr.myproject.view.IRegistView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RegistActivity extends AppCompatActivity implements IRegistView {
    @BindView(R.id.et_reg_name)
     EditText et_reg_name;
    @BindView(R.id.et_reg_pwd)
     EditText et_reg_pwd;
    @BindView(R.id.et_reg_yan)
     EditText et_reg_yan;
    @BindView(R.id.text_login)
     TextView text_login;
    LoginPresenter loginPresenter;
    @BindView(R.id.btn_regist)
     Button btn_regist;
    butterknife.Unbinder Unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        Unbinder = ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_reg_name.getText().toString();
                String pwd = et_reg_pwd.getText().toString();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(RegistActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    loginPresenter.registPre(name,pwd);
                }
            }
        });
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                RegistBean registBean = gson.fromJson(msg, RegistBean.class);
                String status = registBean.getStatus();
                if (status.equals("0000")){
                    Intent intent = new Intent(RegistActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegistActivity.this, registBean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void jumpActivity() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Unbinder.unbind();
    }
}
