package com.skr.myproject.myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skr.myproject.R;

//自定义加减器按钮
public class MyView extends LinearLayout implements View.OnClickListener {

    TextView tv_sub_view;
    TextView tv_number_view;
    TextView tv_add_view;

    private int number = 1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        tv_number_view.setText(number + "");
    }

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_xml, this);

        tv_sub_view = inflate.findViewById(R.id.dec);
        tv_number_view = inflate.findViewById(R.id.num);
        tv_add_view = inflate.findViewById(R.id.add);
        //设置点击事件
        tv_sub_view.setOnClickListener(this);
        tv_add_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.dec:
                if (number > 1) {
                    --number;
                    tv_number_view.setText(number + "");
                    if (onNumChange != null) {
                        onNumChange.onNumberChange(number);
                    }
                } else {
                    Toast.makeText(getContext(), "不能小于1", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add:
                ++number;
                tv_number_view.setText(number + "");
                if (onNumChange != null) {
                    onNumChange.onNumberChange(number);
                }
                break;
        }
    }

    onNumChange onNumChange;

    public void setOnNumChange(MyView.onNumChange onNumChange) {
        this.onNumChange = onNumChange;
    }

    public interface onNumChange {
        void onNumberChange(int number);
    }
}