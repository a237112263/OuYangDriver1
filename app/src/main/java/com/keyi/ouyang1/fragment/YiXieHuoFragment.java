package com.keyi.ouyang1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.keyi.ouyang1.Bean.DetailData;
import com.keyi.ouyang1.R;
import com.keyi.ouyang1.adapter.XieHuoAdapter;
import com.keyi.ouyang1.utils.DatasUtils;
import com.keyi.ouyang1.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public class YiXieHuoFragment extends Fragment implements View.OnClickListener {
    private Button button;
    private View view;
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xiehuo, container, false);
        listView= (ListView) view.findViewById(R.id.lv_main);
        button= (Button) view.findViewById(R.id.bt_clash);
        button.setOnClickListener(this);
        init();
        return view;
    }


    private void init() {
        DatasUtils datasUtils=new DatasUtils();
        final String url = DatasUtils.detailsUrl + datasUtils.MobilNumber(getActivity()) + DatasUtils.strToken + datasUtils.SMSData(getActivity());
        HttpUtils utils = new HttpUtils();
        Log.e("totalList", url.toString());
        utils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Log.e("totalList", data.toString());
                Gson gson = new Gson();
                DetailData detailData = gson.fromJson(data, DetailData.class);
                if (detailData.isIsOK() == false) {

                } else {
                    List<DetailData.DataBean> Data=new ArrayList<DetailData.DataBean>();
                    for (int i = 0; i < detailData.getData().size(); i++) {
                        if (detailData.getData().get(i).getUnloading() == 1) {
                            Data.add(detailData.getData().get(i));
                            Log.e("1236551",detailData.getData().get(i).getUnloading()+"");
                        }
                    }
                    XieHuoAdapter xieHuoAdapter = new XieHuoAdapter(Data);
                    listView.setAdapter(xieHuoAdapter);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
       init();
    }
}
