package com.skr.myproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.skr.myproject.R;
import com.skr.myproject.adapter.MyQuanziAdapter;
import com.skr.myproject.bean.QuanZiBean;
import com.skr.myproject.quanzi.presenter.QuanziPresenter;
import com.skr.myproject.quanzi.view.IQuanziView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MineFragment extends Fragment implements IQuanziView {
    @BindView(R.id.rec_view_quan)
    RecyclerView recViewQuan;
    Unbinder unbinder;
    int page = 1;
    int count = 10;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        QuanziPresenter quanziPresenter = new QuanziPresenter(this);
        quanziPresenter.getPresenterData(page,count);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recViewQuan.setLayoutManager(linearLayoutManager);
        return view;
    }
    //视图层回调的方法
    @Override
    public void getViewData(final String s) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                QuanZiBean quanZiBean = new Gson().fromJson(s, QuanZiBean.class);
                List<QuanZiBean.ResultBean> result = quanZiBean.getResult();
                MyQuanziAdapter adapter = new MyQuanziAdapter(getActivity(),result);
                recViewQuan.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
