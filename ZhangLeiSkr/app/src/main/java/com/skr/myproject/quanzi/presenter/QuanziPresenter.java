package com.skr.myproject.quanzi.presenter;

import com.google.gson.Gson;
import com.skr.myproject.adapter.MyQuanziAdapter;
import com.skr.myproject.api.Api;
import com.skr.myproject.bean.QuanZiBean;
import com.skr.myproject.fragment.MineFragment;
import com.skr.myproject.quanzi.model.IQuanziModel;
import com.skr.myproject.quanzi.model.QuanziModel;

import java.util.List;

public class QuanziPresenter implements IQuanziPresenter{
    MineFragment mineFragment;
    private final QuanziModel quanziModel;

    public QuanziPresenter (MineFragment mineFragment){
        this.mineFragment = mineFragment;
        quanziModel = new QuanziModel();
    }
    @Override
    public void getPresenterData(int page,int count) {
        quanziModel.getModelData(Api.QUAN_DETAILS + "?page=" + page + "&count=" + count, new IQuanziModel.ModelCallBack() {
            @Override
            public void onSuccess(String data) {

                mineFragment.getViewData(data);
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
