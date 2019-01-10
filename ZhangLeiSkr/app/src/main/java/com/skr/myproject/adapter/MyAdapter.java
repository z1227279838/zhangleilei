package com.skr.myproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.skr.myproject.R;
import com.skr.myproject.bean.MyData;
import com.skr.myproject.myview.MyView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends BaseExpandableListAdapter {
    Context context;
    List<MyData.ResultBean.PzshBean> data;

    public MyAdapter(Context context, List<MyData.ResultBean.PzshBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return data.size();
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.menu_layout_parent, null);
            groupViewHolder = new GroupViewHolder(view);
            view.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) view.getTag();
        }
        groupViewHolder.tvTitleGroup.setText(data.get(i).getName());

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        ChildViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.menu_layout_son, null);
            holder = new ChildViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ChildViewHolder) view.getTag();
        }
        holder.tvTitleChild.setText(data.get(i).getCommodityList().get(i).getCommodityName());
        Glide.with(context).load(data.get(i).getCommodityList().get(i).getMasterPic()).into(holder.ivIconChild);
        holder.tvPriceChild.setText("¥" + data.get(i).getCommodityList().get(i).getPrice());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    static class ChildViewHolder {
        @BindView(R.id.cb_child_item)
        CheckBox cbChildItem;
        @BindView(R.id.iv_icon_child)
        ImageView ivIconChild;
        @BindView(R.id.tv_title_child)
        TextView tvTitleChild;
        @BindView(R.id.tv_price_child)
        TextView tvPriceChild;
        @BindView(R.id.add_sub_view_child)
        MyView addSubViewChild;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class GroupViewHolder {
        @BindView(R.id.cb_group_item)
        CheckBox cbGroupItem;
        @BindView(R.id.tv_title_group)
        TextView tvTitleGroup;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
