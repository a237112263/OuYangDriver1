package com.keyi.ouyang1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.keyi.ouyang1.MapApiDemoActivity;
import com.keyi.ouyang1.R;
import com.keyi.ouyang1.utils.ACache;


/**
 * Created by Administrator on 2016/4/29.
 */
public class CarAdapter extends BaseAdapter implements View.OnClickListener {
   private  Object[]  detailData;
    private Button button;
    private Context context;
    public CarAdapter( Context context, Object[]  detailData){
        this.context=context;
       this.detailData= detailData;
    }
    @Override
    public int getCount() {
        return detailData.length;
    }

    @Override
    public Object getItem(int position) {
        return detailData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_car,parent,false);
        TextView textView= (TextView) convertView.findViewById(R.id.tv_car);
        textView.setText("车次号：" + detailData[position]);
        button= (Button) convertView.findViewById(R.id.bt_car);
        button.setOnClickListener(this);
        button.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        ACache aCache=ACache.get(context);
        if (v.getId()==R.id.bt_car){
            Intent intent=new Intent(context, MapApiDemoActivity.class);
            for (int i=0;i<detailData.length;i++){
                if ((int)v.getTag()==i){
                    intent.putExtra("车次号",detailData[i].toString());
                }
            }
            context.startActivity(intent);
        }
    }
}
