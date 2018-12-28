package com.bawei.zhanglei.xiaoleitwo.home.presenter;

import com.bawei.zhanglei.xiaoleitwo.MainActivity;
import com.bawei.zhanglei.xiaoleitwo.api.Api;
import com.bawei.zhanglei.xiaoleitwo.home.model.CModerl;

public class Presenter implements IPresenter,CModerl.ModuleInterface {

    private MainActivity mView;
    private final CModerl moderl;

    public Presenter(MainActivity mainActivity){
        this.mView=mainActivity;
        moderl = new CModerl(this);
    }
    @Override
    public void getPresenterData() {
        moderl.getModelData(Api.SHOPLIST);
    }

    @Override
    public void LoadSuccess(String data) {
            mView.getViewData(data);
    }

    @Override
    public void LoadFailed() {
        mView.getViewData("加载失败[");
    }
}
