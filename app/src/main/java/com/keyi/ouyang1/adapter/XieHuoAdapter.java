package com.keyi.ouyang1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.keyi.ouyang1.Bean.DetailData;
import com.keyi.ouyang1.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class XieHuoAdapter extends BaseAdapter{
    List<DetailData.DataBean> Data;
    private int count=0;
    public XieHuoAdapter(List<DetailData.DataBean> Data){
        this.Data=Data;
    }
    @Override
    public int getCount() {
        return Data.size();
    }

    @Override
    public Object getItem(int position) {
        return Data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convert, ViewGroup parent) {
        ViewHolder holder=null;
        if (convert==null) {
            convert = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.listview_xiehuo, parent, false);
            holder=new ViewHolder();
            holder.tv1=(TextView) convert.findViewById(R.id.tv_1);
            holder.tv2=(TextView) convert.findViewById(R.id.iv_2);
            holder.tv3=(TextView) convert.findViewById(R.id.tv3);
            holder.tv4=(TextView) convert.findViewById(R.id.tv4);
            holder.tv5=(TextView) convert.findViewById(R.id.tv5);
            holder.tv6=(TextView) convert.findViewById(R.id.tv6);
            holder.tv7=(TextView) convert.findViewById(R.id.tv7);
            holder.tv8=(TextView) convert.findViewById(R.id.tv8);
            holder.tv9=(TextView) convert.findViewById(R.id.tv9);
            holder.tv10=(TextView) convert.findViewById(R.id.tv10);
            holder.tv11=(TextView) convert.findViewById(R.id.tv11);
            holder.tv12=(TextView) convert.findViewById(R.id.tv12);
            holder.tv13=(TextView) convert.findViewById(R.id.tv13);
            convert.setTag(holder);
        }else{
            holder=(ViewHolder) convert.getTag();
        }
        setDataToUi1(position, holder);
        return convert;
    }

    private void setDataToUi1(int position, ViewHolder holder) {

            holder.tv1.setText("托运单号：" + Data.get(position).getCheckNo());
            holder.tv2.setText("货单状态:已卸货");
            holder.tv3.setText("起运地:" + Data.get(position).getDeparture());
            holder.tv4.setText("到达地："+Data.get(position).getArrival());
            holder.tv5.setText("托运单号："+Data.get(position).getCheckNo());
            holder.tv6.setText("车次号："+Data.get(position).getLogisBatchNo());
            holder.tv7.setText("分销商："+Data.get(position).getDistributor());
            holder.tv8.setText("托运人：" + Data.get(position).getShipper());
            holder.tv9.setText("收货人："+Data.get(position).getReceiverName());
            holder.tv10.setText("收货人电话："+Data.get(position).getReceiverMobile());
            holder.tv11.setText("商品总体积："+Data.get(position).getVolumeSum());
            holder.tv12.setText("商品总数："+Data.get(position).getOrderNum());
            holder.tv13.setText("商品总包件数："+Data.get(position).getOrderPackcount());

    }

    class ViewHolder{
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private TextView tv5;
        private TextView tv6;
        private TextView tv7;
        private TextView tv8;
        private TextView tv9;
        private TextView tv10;
        private TextView tv11;
        private TextView tv12;
        private TextView tv13;
    }
}
