package com.bawei.zhanglei.xiaoleitwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bawei.zhanglei.xiaoleitwo.home.presenter.Presenter;
import com.bawei.zhanglei.xiaoleitwo.home.view.IView;

public class MainActivity extends AppCompatActivity implements IView {

    private TextView text_name;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_name = findViewById(R.id.text_name);
        //初始化P层
        presenter = new Presenter(this);
        presenter.getPresenterData();
    }

    @Override
    public void getViewData(String mViewData) {
        text_name.setText(mViewData);
    }

}
