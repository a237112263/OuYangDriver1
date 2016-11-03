package com.keyi.ouyang1.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.keyi.ouyang1.Bean.DetailData1;
import com.keyi.ouyang1.R;
import com.keyi.ouyang1.adapter.CarAdapter;
import com.keyi.ouyang1.utils.DatasUtils;
import com.keyi.ouyang1.utils.HttpUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/16.
 */
public class CarFragment extends Fragment  {
    private Context context;
    private View view;
    private ListView listView;
    private CarAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_car, container, false);
        listView= (ListView) view.findViewById(R.id.lv_car);
        context=getContext();
        intiData();
        return view;
    }

    private void intiData() {
        DatasUtils datasUtils=new DatasUtils();
        final String url = DatasUtils.detailsUrl + datasUtils.MobilNumber(getActivity()) + DatasUtils.strToken + datasUtils.SMSData(getActivity());
        HttpUtils utils = new HttpUtils();
        Log.e("totalList", url.toString());
        utils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Log.e("totalList", data.toString());
                Gson gson = new Gson();
                DetailData1 detailData = gson.fromJson(data, DetailData1.class);
                if (detailData.isIsOK() == true) {
                    String[] checkNo=new String[detailData.getData().size()];
                    for (int i=0;i<detailData.getData().size();i++){
                        checkNo[i]=detailData.getData().get(i).getLogisBatchNo();
                    }
                    List<String> numList = new ArrayList<String>();
                    for (String i : checkNo)
                        numList.add(i);
                    Set<String> numSet = new HashSet<String>();
                    numSet.addAll(numList);
                    Object[] toArray = numSet.toArray();
                    adapter = new CarAdapter(context,toArray);
                    listView.setAdapter(adapter);
                } else {

                }
            }
        });
    }


}
