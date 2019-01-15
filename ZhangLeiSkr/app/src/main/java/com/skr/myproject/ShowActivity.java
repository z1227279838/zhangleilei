package com.skr.myproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hjm.bottomtabbar.BottomTabBar;
import com.skr.myproject.fragment.HomeFragment;
import com.skr.myproject.fragment.MineFragment;
import com.skr.myproject.fragment.SecondFragment;
import com.skr.myproject.fragment.MemuFragment;
import com.skr.myproject.fragment.ShopCarFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShowActivity extends AppCompatActivity {


    @BindView(R.id.bottom_bar)
    BottomTabBar bottom_bar;
    private Unbinder Unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Unbinder = ButterKnife.bind(this);
        bottom_bar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(8)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.BLACK)
                .addTabItem("首页",R.drawable.rb1_selector,HomeFragment.class)
                .addTabItem("分类",R.drawable.rb2_selector,MineFragment.class)
                .addTabItem("觅Me",R.drawable.buy,ShopCarFragment.class)
                .addTabItem("购物车",R.drawable.rb4_selector,MemuFragment.class)
                .addTabItem("我的",R.drawable.rb5_selector,SecondFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Unbinder.unbind();
    }
}
