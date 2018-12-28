package com.bawei.zhanglei.leishen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bawei.zhanglei.leishen.home.presenter.HomeListPresenter;
import com.bawei.zhanglei.leishen.home.view.IHomeListView;


public class MainActivity extends AppCompatActivity implements IHomeListView {

    private HomeListPresenter homeListPresenter;
    private TextView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.tv);
        //初始化presenter
        homeListPresenter = new HomeListPresenter(this);
        homeListPresenter.getModelDate();
    }
    @Override
    public void getViewData(String mViewData) {
        viewById.setText(mViewData);
    }
}

