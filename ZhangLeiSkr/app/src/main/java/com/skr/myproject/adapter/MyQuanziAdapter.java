package com.skr.myproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skr.myproject.R;
import com.skr.myproject.bean.QuanZiBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyQuanziAdapter extends RecyclerView.Adapter<MyQuanziAdapter.ViewHolder> {
    Context context;
    List<QuanZiBean.ResultBean> list;
    View view;
    private ViewHolder viewHolder;

    public MyQuanziAdapter(Context context, List<QuanZiBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.quanzi_layout, viewGroup, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolde, int i) {
        Glide.with(context).load(list.get(i).getHeadPic()).into(viewHolder.headImgQuan);
        Glide.with(context).load(list.get(i).getImage()).into(viewHolder.headImgQuan2);
        viewHolder.nichengQuan.setText(list.get(i).getNickName());
        viewHolder.feihuaQuan.setText(list.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView headImgQuan;
        TextView nichengQuan;
        TextView feihuaQuan;
        ImageView headImgQuan2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headImgQuan = itemView.findViewById(R.id.head_img_quan);
            nichengQuan = itemView.findViewById(R.id.nicheng_quan);
            feihuaQuan = itemView.findViewById(R.id.feihua_quan);
            headImgQuan2 = itemView.findViewById(R.id.head_img_quan2);
        }
    }
}
