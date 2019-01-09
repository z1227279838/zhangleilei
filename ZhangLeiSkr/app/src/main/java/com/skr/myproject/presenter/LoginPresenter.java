package com.skr.myproject.presenter;

import com.skr.myproject.MainActivity;
import com.skr.myproject.RegistActivity;
import com.skr.myproject.ShowActivity;
import com.skr.myproject.api.Api;
import com.skr.myproject.model.ILoginModel;
import com.skr.myproject.model.LoginModel;

public class LoginPresenter implements ILoginPresenter {

    MainActivity mainActivity;
    RegistActivity registActivity;
    private final LoginModel loginModel;

    public LoginPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        loginModel = new LoginModel();
    }

    public LoginPresenter(RegistActivity registActivity) {
        this.registActivity = registActivity;
        loginModel = new LoginModel();
    }

    @Override
    public void loginPre(String name, String pwd) {

        loginModel.login(Api.LOGIN, name, pwd, new ILoginModel.ILoginCallBack() {
            @Override
            public void onStatus(String data) {
                mainActivity.showMsg(data);
                mainActivity.jumpActivity();
            }

            @Override
            public void onFailed() {

            }
        });
    }

    @Override
    public void registPre(String name, String pwd) {
        loginModel.regist(Api.REGIST, name, pwd, new ILoginModel.ILoginCallBack() {
            @Override
            public void onStatus(String data) {
                registActivity.showMsg(data);
                registActivity.jumpActivity();
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
