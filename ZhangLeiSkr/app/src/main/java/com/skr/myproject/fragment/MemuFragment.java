package com.skr.myproject.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.skr.myproject.R;
import com.skr.myproject.adapter.MyAdapter;
import com.skr.myproject.api.Api;
import com.skr.myproject.bean.MyData;
import com.skr.myproject.network.OkHttp;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MemuFragment extends Fragment {

    @BindView(R.id.elc_show_main)
    ExpandableListView elcShowMain;
    @BindView(R.id.cb_allCheck_main)
    CheckBox cbAllCheckMain;
    @BindView(R.id.btn_allPrice_main)
    TextView btnAllPriceMain;
    @BindView(R.id.btn_allNumber_main)
    Button btnAllNumberMain;
    private Unbinder Unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_layout, container, false);
        Unbinder = ButterKnife.bind(this, view);
        OkHttp.okHttpGet(Api.SHOPLIST, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String s = response.body().string();
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       MyData myData = new Gson().fromJson(s, MyData.class);
                       List<MyData.ResultBean.PzshBean> pzsh = myData.getResult().getPzsh();
                       MyAdapter adapter = new MyAdapter(getActivity(),pzsh);
                       elcShowMain.setAdapter(adapter);
                       for (int i = 0; i < pzsh.size(); i++) {
                           elcShowMain.expandGroup(i);
                       }
                   }
               });
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Unbinder.unbind();
    }
}
