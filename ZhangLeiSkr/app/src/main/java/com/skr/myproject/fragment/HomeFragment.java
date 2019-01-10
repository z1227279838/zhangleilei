package com.skr.myproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.skr.myproject.DetailsActivity;
import com.skr.myproject.R;
import com.skr.myproject.adapter.MyMlssAdapter;
import com.skr.myproject.adapter.MyPzshAdapter;
import com.skr.myproject.adapter.MyRxxpAdapter;
import com.skr.myproject.bean.MyBanner;
import com.skr.myproject.bean.MyData;
import com.skr.myproject.home.presenter.HomePresenter;
import com.skr.myproject.home.view.IHomeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements IHomeView {
    @BindView(R.id.xbanner)
     XBanner xbanner;
    @BindView(R.id.grid_view_rxxp)
     GridView grid_view_rxxp;
    @BindView(R.id.list_view_mlss)
  ListView list_view_mlss;
    @BindView(R.id.grid_view_pzsh)
    GridView grid_view_pzsh;
    private List<MyData.ResultBean.MlssBean.CommodityListBeanXX> mlssList;
    private List<MyData.ResultBean.PzshBean.CommodityListBeanX> pzshList;
    private List<MyData.ResultBean.RxxpBean.CommodityListBean> rxxpList;
    private Unbinder Unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_layout, container, false);
        Unbinder = ButterKnife.bind(this, view);
        //初始化presenter
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.getPresenterData();
        return view;
    }

    @Override
    public void getViewData(String mViewData) {
        Gson gson = new Gson();
        MyData myData = gson.fromJson(mViewData, MyData.class);
        mlssList = myData.getResult().getMlss().get(0).getCommodityList();
        pzshList = myData.getResult().getPzsh().get(0).getCommodityList();
        rxxpList = myData.getResult().getRxxp().get(0).getCommodityList();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (rxxpList != null){
                    MyRxxpAdapter rxxpAdapter = new MyRxxpAdapter(getActivity(),rxxpList);
                    grid_view_rxxp.setAdapter(rxxpAdapter);
                    grid_view_rxxp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent intent = new Intent(getActivity(),DetailsActivity.class);

                            intent.putExtra("id",rxxpList.get(position).getCommodityId()+"");
                            //   Toast.makeText(getActivity(), rxxpList.get(position).getCommodityId(), Toast.LENGTH_SHORT).show();
                            //Log.i("bb", "onItemClick: "+rxxpList.get(position).getCommodityId());
                            startActivity(intent);
                        }
                    });
                }
                if (mlssList != null){
                    MyMlssAdapter myMlssAdapter = new MyMlssAdapter(getActivity(),mlssList);
                    list_view_mlss.setAdapter(myMlssAdapter);
                    list_view_mlss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent intent = new Intent(getActivity(),DetailsActivity.class);

                            intent.putExtra("id",mlssList.get(position).getCommodityId()+"");
                            //   Toast.makeText(getActivity(), rxxpList.get(position).getCommodityId(), Toast.LENGTH_SHORT).show();
                            //Log.i("bb", "onItemClick: "+rxxpList.get(position).getCommodityId());
                            startActivity(intent);
                        }
                    });
                }
                if (pzshList != null){
                    MyPzshAdapter pzshAdapter = new MyPzshAdapter(getActivity(),pzshList);
                    grid_view_pzsh.setAdapter(pzshAdapter);
                    grid_view_pzsh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent intent = new Intent(getActivity(),DetailsActivity.class);

                            intent.putExtra("id",pzshList.get(position).getCommodityId()+"");
                            //   Toast.makeText(getActivity(), rxxpList.get(position).getCommodityId(), Toast.LENGTH_SHORT).show();
                            //Log.i("bb", "onItemClick: "+rxxpList.get(position).getCommodityId());
                            startActivity(intent);
                        }
                    });
                }

            }
        });


    }

    @Override
    public void getBannerData(String banner) {
        Gson gson = new Gson();
        MyBanner myBanner = gson.fromJson(banner, MyBanner.class);
        final List<MyBanner.ResultBean> result = myBanner.getResult();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                xbanner.setData(result,null);
                xbanner.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, View view, int position) {
                        Glide.with(getActivity()).load(result.get(position).getImageUrl()).into((ImageView) view);
                    }
                });
                xbanner.setPageTransformer(Transformer.Default);
                xbanner.setPageChangeDuration(500);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        xbanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xbanner.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Unbinder.unbind();
    }
}
