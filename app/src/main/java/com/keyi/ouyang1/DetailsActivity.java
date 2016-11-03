package com.keyi.ouyang1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.keyi.ouyang1.Bean.DetailData;
import com.keyi.ouyang1.utils.ACache;
import com.keyi.ouyang1.utils.DatasUtils;
import com.keyi.ouyang1.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
public class DetailsActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ACache aCache;
    private Button button,button1;
    private  StringBuffer buffer;
    private int count=0;
    private LinearLayout linearLayout1, linearLayout;
    private TextView[] textViews=new TextView[11];
    private CheckBox checkBox;
    private List<DetailData.DataBean> mData;
    private Intent mIntent;
    private int count1=0;
    private int count2=0;
    private int count3=0;
    private int count4=0;
    private static Handler handler=new Handler();
    private TextView textView;
    @Nullable


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initDatas();
        linearLayout1= (LinearLayout) findViewById(R.id.linear_details);
        button = (Button)findViewById(R.id.bt_details);
        button1= (Button) findViewById(R.id.bt_allselect);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        textView= (TextView) findViewById(R.id.tv_details);
    }

    private void init(List<DetailData.DataBean> Data) {
        mData=Data;
        for (int i=0;i<Data.size();i++){
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams. MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linearLayout=new LinearLayout(this);
            FrameLayout layout=new FrameLayout(this);
            layout.setLayoutParams(lp1);
            TextView textViewx=new TextView(this);
            textViewx.setGravity(Gravity.CENTER);
            textViewx.setTextColor(Color.rgb(89, 168, 255));
            textViewx.setTextSize(12);
            textViewx.setPadding(0, 10, 0, 0);
            if (Data.get(i).getUnloading()==1){
                ++count4;
            }else {
                textViewx.setText("托运单号："+Data.get(i).getCheckNo());
                count++;
            }
            TextView textViewy=new TextView(this);
            if (Data.get(i).getUnloading()==1){

            }else {
                textViewy.setText("货单状态：未卸货");
                textViewy.setTextColor(Color.BLUE);
                textViewy.setPadding(0, 15, 10, 0);
                textViewy.setTextSize(8);
                textViewy.setId(10000 + count2++);
                textViewy.setGravity(Gravity.RIGHT);
                layout.addView(textViewx);
                layout.addView(textViewy);
                LinearLayout linearLayout2=new LinearLayout(this);
                linearLayout2.setLayoutParams(lp1);
                linearLayout2.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(80, 120, 0, 0);
                checkBox=new CheckBox(this);
                checkBox.setLayoutParams(layoutParams);
                checkBox.setChecked(false);
                checkBox.setId(1000 + count3++);
                checkBox.setBackgroundResource(R.drawable.selector_banjia);
                checkBox.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));


                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                intiTextview(i,Data);
                TextView textViewz=new TextView(this);
                textViewz.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                textViewz.setHeight(2);
                textViewz.setPadding(0, 10, 0, 0);
                textViewz.setBackgroundColor(Color.rgb(89,168,255));


                linearLayout2.addView(linearLayout);
                linearLayout2.addView(checkBox);

                linearLayout1.addView(layout);
                linearLayout1.addView(linearLayout2);
                linearLayout1.addView(textViewz);
            }
        }
        if (count4==Data.size()){
            textView.setVisibility(View.VISIBLE);
            button.setVisibility(View.GONE);
        }
    }

    private void intiTextview(int i,List<DetailData.DataBean> Data) {
        for (int i1=0;i1<11;i1++) {
            textViews[i1] = new TextView(this);
            textViews[i1].setPadding(30, 15, 0, 0);
            textViews[i1].setTextSize(12);
            textViews[i1].setTextColor(Color.rgb(83, 169, 255));
            if (Data.get(i).getUnloading()==1){

            }else {
                switch (i1) {
                    case 0:
                        textViews[0].setText("起运地：" + Data.get(i).getDeparture());
                        break;
                    case 1:
                        textViews[1].setText("到达地：" + Data.get(i).getArrival());
                        break;
                    case 2:
                        textViews[2].setText("托运单号：" + Data.get(i).getCheckNo());
                        aCache.put("check"+count1++,Data.get(i).getCheckNo());
                        break;
                    case 3:
                        textViews[3].setText("车次号：" + Data.get(i).getLogisBatchNo());
                        break;
                    case 4:
                        textViews[4].setText("分销商：" + Data.get(i).getDistributor());
                        break;
                    case 5:
                        textViews[5].setText("托运人：" + Data.get(i).getShipper());
                        break;
                    case 6:
                        textViews[6].setText("收货人：" + Data.get(i).getReceiverName());
                        break;
                    case 7:
                        textViews[7].setText("收货人电话：" + Data.get(i).getReceiverMobile());
                        break;
                    case 8:
                        textViews[8].setText("商品总体积：" +Data.get(i).getVolumeSum());
                        break;
                    case 9:
                        textViews[9].setText("商品总数：" + Data.get(i).getOrderNum());
                        break;
                    case 10:
                        textViews[10].setText("商品总包件数：" + Data.get(i).getOrderPackcount());
                        break;
                }
            }
        }
        for (int j=0;j<11;j++){
            if (Data.get(i).getUnloading()==1){

            }else {
                linearLayout.addView(textViews[j]);
            }
        }

    }

    private void initDatas() {
        aCache=ACache.get(this);
        DatasUtils datasUtils=new DatasUtils();
        final String url = DatasUtils.detailsUrl + datasUtils.MobilNumber(this) + DatasUtils.strToken + datasUtils.SMSData(this);
        HttpUtils utils = new HttpUtils();
        Log.e("totalList", url.toString());
        utils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Log.e("totalList", data.toString());
                Gson gson=new Gson();
                DetailData detailData=gson.fromJson(data,DetailData.class);
                if (detailData.isIsOK()==true){
                    aCache.put("ItemData", detailData);
                    List<DetailData.DataBean> Data=new ArrayList<DetailData.DataBean>();
                    for (int i=0;i<detailData.getData().size();i++){
                        try {
                            if (aCache.getAsString("车次号").equals(detailData.getData().get(i).getLogisBatchNo())){
                                Data.add(detailData.getData().get(i));
                            }
                        }catch (Exception e){

                        }
                    }
                    init(Data);
                }else {

                }
            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_details:
                mIntent = new Intent();
                mIntent.setAction("com.keyi.ouyang1.servie.MapLocationService");//你定义的service的action
                mIntent.setPackage(this.getPackageName());//这里你需要设置你应用的包名
                this.startService(mIntent);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    Log.e("asdadsds",aCache.getAsString("Address"));
                                    if (aCache.getAsString("location").equals("null")){
                                        Toast.makeText(DetailsActivity.this,"获取地理位置失败，请检查网络,重新上传！",Toast.LENGTH_SHORT).show();
                                    }else {
                                        setDialog();
                                    }
                                }catch (Exception e){

                                }
                            }
                        },1000);
                    }
                }).start();
                break;
            case R.id.bt_allselect:
                for (int i=0;i<count;i++){
                    checkBox= (CheckBox)findViewById(1000 + i);
                    checkBox.setChecked(true);
                }
        }

    }

    private void setDialog() {
        buffer=new StringBuffer();
        for (int i=0;i<count;i++){
            Log.i("asdas",aCache.getAsString("check"+i));
            checkBox= (CheckBox)findViewById(1000 + i);
            if (checkBox.isChecked()){
                buffer.append(aCache.getAsString("check"+i) + ",");
            }else {

            }
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
               this);
        alertDialog.setTitle(this
                .getString(R.string.app_close));
        alertDialog.setMessage("单号：\n" + buffer + "\n位置：" + aCache.getAsString("Address"));
        alertDialog.setPositiveButton(this
                        .getString(R.string.btn_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        setZhuangtai();
                    }
                });
        alertDialog.setNegativeButton(this
                        .getString(R.string.btn_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        DetailsActivity.this.stopService(mIntent);
                    }
                });
        alertDialog.show();
    }

    private void setZhuangtai() {
        setLoad();//上次货物状态
    }


    private void setLoad() {
        DatasUtils datasUtils=new DatasUtils();
        final String url=DatasUtils.updateUrl+datasUtils.MobilNumber(this) + DatasUtils.strToken + datasUtils.SMSData(this)+"&checknos="+buffer.toString()+"&Text="+aCache.getAsString("Address")+"已卸货"+DatasUtils.dimension+aCache.getAsString("Latitude");
        Log.e("url",url.toString());
        HttpUtils utils = new HttpUtils();
        utils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    if (jsonObject.getString("IsOK").toString().equals("true")) {
                        for (int i = 0; i < count; i++) {
                            checkBox = (CheckBox)findViewById(1000 + i);
                            TextView textView = (TextView) findViewById(10000 + i);
                            if (checkBox.isChecked()) {
                                textView.setTextColor(Color.RED);
                                textView.setText("货单状态：已卸货");
                                checkBox.setChecked(false);
                            } else {
                                
                            }
                        }
                        Toast.makeText(DetailsActivity.this, "已成功上传卸货信息!", Toast.LENGTH_SHORT).show();
                        DetailsActivity.this.stopService(mIntent);
                        Intent intent=new Intent(DetailsActivity.this, DetailsActivity.class);
                        startActivity(intent);
                        DetailsActivity.this.finish();
                    } else {
                        Toast.makeText(DetailsActivity.this, "上传卸货信息失败!", Toast.LENGTH_SHORT).show();
                        DetailsActivity.this.stopService(mIntent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

