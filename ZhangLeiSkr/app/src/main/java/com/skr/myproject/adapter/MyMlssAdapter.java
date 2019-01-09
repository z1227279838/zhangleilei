package com.skr.myproject.adapter;

import android.content.Context;
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

public class MyMlssAdapter extends BaseAdapter {
    private Context context;
    List<MyData.ResultBean.MlssBean.CommodityListBeanXX> mlssList;

    public MyMlssAdapter(Context context, List<MyData.ResultBean.MlssBean.CommodityListBeanXX> mlssList) {
        this.context = context;
        this.mlssList = mlssList;
    }

    @Override
    public int getCount() {
        return mlssList.size();
    }

    @Override
    public Object getItem(int position) {
        return mlssList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.mlsh_grid_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(mlssList.get(position).getMasterPic(), holder.mlss_img);
       holder.mlss_title.setText(mlssList.get(position).getCommodityName());
        holder.mlss_price.setText("￥:"+mlssList.get(position).getPrice());
       return convertView;
    }

   class ViewHolder {
        @BindView(R.id.mlsh_img)
        ImageView mlss_img;
        @BindView(R.id.mlsh_title)
        TextView mlss_title;
        @BindView(R.id.mlsh_price)
        TextView  mlss_price;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
