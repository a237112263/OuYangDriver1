package com.keyi.ouyang1.servie;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.keyi.ouyang1.utils.ACache;

/**
 * Created by Administrator on 2016/5/18.
 */
public class MapLocationService extends Service  {
    private static final String TAG = "ServiceDemo" ;
    public static final String ACTION = "com.keyi.ouyang1.servie.MapLocationService";
    private LocationClient locationClient = null;
    private static final int UPDATE_TIME = 5000;
    private ACache aCache;
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "ServiceDemo onBind");
        locationClient.stop();
        return null;
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "ServiceDemo onCreate");
        super.onCreate();
        aCache=ACache.get(this);
        locationClient = new LocationClient(this);
        //设置定位条件
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );
        option.setOpenGps(true);		//是否打开GPS
        option.setCoorType("bd09ll");        //设置返回值的坐标类型。
        option.setAddrType("all");
        option.setProdName("KEYI");	//设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
        option.setScanSpan(UPDATE_TIME);    //设置定时定位的时间间隔。单位毫秒
        locationClient.setLocOption(option);
        locationClient.start();
        locationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                // TODO Auto-generated method stub
                aCache.put("location", location+"");
                if (location == null) {
                    return;
                }
                StringBuffer sb = new StringBuffer(256);
                sb.append(location.getLatitude());
                sb.append(",");
                sb.append(location.getLongitude());
                LatLng ptCenter = new LatLng(location.getLatitude(),location.getLongitude());
                aCache.put("Address", location.getAddrStr());
                aCache.put("Latitude", sb.toString());
                Log.e("asasddsa", location.getAddrStr());
            }
        });
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.v(TAG, "ServiceDemo onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "ServiceDemo onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

}
