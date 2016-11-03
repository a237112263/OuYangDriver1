package com.keyi.ouyang1.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.keyi.ouyang1.DetailsActivity;
import com.keyi.ouyang1.R;
import com.keyi.ouyang1.utils.ACache;


/**
 * Created by Administrator on 2016/4/29.
 */
public class AllCarAdapter extends BaseAdapter implements View.OnClickListener {
    private  Object[] dataBeanList;
    private Button button;
    private Context context;
    public AllCarAdapter(Context context, Object[] dataBeanList){
        this.context=context;
       this.dataBeanList= dataBeanList;
    }
    @Override
    public int getCount() {
        return dataBeanList.length;
    }

    @Override
    public Object getItem(int position) {
        return dataBeanList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_allcar,parent,false);
        TextView textView= (TextView) convertView.findViewById(R.id.tv_allcar);
        button= (Button) convertView.findViewById(R.id.bt_allcar);
        textView.setText("车次号：" + dataBeanList[position]);
        button.setOnClickListener(this);
        button.setTag(position);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        ACache aCache=ACache.get(context);
        if (v.getId()==R.id.bt_allcar){
            Intent intent=new Intent(context, DetailsActivity.class);
            for (int i=0;i<dataBeanList.length;i++){
                if ((int)v.getTag()==i){
                    aCache.put("车次号",dataBeanList[i].toString());
                }
            }
            context.startActivity(intent);
        }
    }
}
