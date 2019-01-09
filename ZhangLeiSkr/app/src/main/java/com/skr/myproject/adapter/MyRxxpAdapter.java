package com.skr.myproject.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.skr.myproject.R;
import com.skr.myproject.bean.MyData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRxxpAdapter extends BaseAdapter {
    private Context context;
    private List<MyData.ResultBean.RxxpBean.CommodityListBean> rxxpList;

    public MyRxxpAdapter(Context context, List<MyData.ResultBean.RxxpBean.CommodityListBean> rxxpList) {
        this.context = context;
        this.rxxpList = rxxpList;
    }

    @Override
    public int getCount() {
        return rxxpList.size();
    }

    @Override
    public Object getItem(int position) {
        return rxxpList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.rxxp_grid_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(rxxpList.get(position).getMasterPic(), holder.rxxp_img);
       holder.rxxp_title.setText(rxxpList.get(position).getCommodityName());
        holder.rxxp_price.setText("￥:"+rxxpList.get(position).getPrice());
        return convertView;
    }
    static class ViewHolder {
        @BindView(R.id.rxxp_img)
        ImageView rxxp_img;
        @BindView(R.id.rxxp_price)
        TextView rxxp_price;
        @BindView(R.id.rxxp_title)
        TextView  rxxp_title;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
