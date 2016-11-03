package com.keyi.ouyang1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.keyi.ouyang1.Bean.MapUpLoadingData;
import com.keyi.ouyang1.utils.DatasUtils;
import com.keyi.ouyang1.utils.HttpUtils;

public class MapApiDemoActivity extends Activity implements OnClickListener{
	private MapView mMapView = null;
	private BaiduMap mBaiduMap = null;
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	private TextView textView;
	private Button button, button1;
	private static Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// SDK初始化
		SDKInitializer.initialize(getApplicationContext());
		//当前视图
		setContentView(R.layout.activity_map_api_demo);
		//创建地图对象
		init();
		button = (Button) findViewById(R.id.btn_location);
		button.setOnClickListener(this);
		button1 = (Button) findViewById(R.id.btn_location1);
		button1.setOnClickListener(this);
		getLocation();
	}

	public void getLocation() {
		// 定位初始化
		mLocClient = new LocationClient(this);
		LocationClientOption option = new LocationClientOption();

		option.setOpenGps(true);//打开gps
		option.setCoorType("bd09ll"); //设置坐标类型
		option.setScanSpan(300000); //定位时间间隔
		option.setAddrType("all");
		mLocClient.setLocOption(option);
		mLocClient.start();

		mLocClient.registerLocationListener(new BDLocationListener() {
			@Override
			public void onReceiveLocation(BDLocation location) {

				if (location == null) {
					return;
				}
				LatLng ptCenter = new LatLng(location.getLatitude(), location.getLongitude());
				mBaiduMap.clear();
				mBaiduMap.addOverlay(new MarkerOptions().position(ptCenter)
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.icon_openmap_focuse_mark)));
				mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(ptCenter));
				textView.setText("当前位置：" + location.getAddrStr());
				editor.putString("address", location.getAddrStr());
				editor.putString("jingdu", location.getLatitude() + "");
				editor.putString("weidu", location.getLongitude() + "");
				editor.commit();

			}
		});
	}

	/**
	 * 初始化方法
	 */
	private void init() {
		textView = (TextView) findViewById(R.id.tv_baidumap);
		sharedPreferences = MapApiDemoActivity.this.getSharedPreferences("mapdata1", Activity.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		mMapView = new MapView(this, new BaiduMapOptions());
		mBaiduMap = mMapView.getMap();
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(16.0f);
		mBaiduMap.setMapStatus(msu);
		/**添加一个对象*/
		RelativeLayout rlly_map = (RelativeLayout) findViewById(R.id.rlly_map);
		rlly_map.addView(mMapView);
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		//初始化搜索模块，注册事件监听
	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}

	// 定位相关
	LocationClient mLocClient;

	private boolean isFirstLoc = true;

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_location:
				button.setVisibility(View.GONE);
				button1.setVisibility(View.VISIBLE);
				Intent intent = getIntent();
				intent.getStringExtra("车次号");
				DatasUtils datasUtils = new DatasUtils();
				final String url = DatasUtils.updateAdressUrl + datasUtils.MobilNumber(this) + DatasUtils.strToken + datasUtils.SMSData(this) + "&LogisBatchNo=" + intent.getStringExtra("车次号").toString() + "&Text=" + sharedPreferences.getString("address", "") + "&dimension=" + sharedPreferences.getString("jingdu", "") + "," + sharedPreferences.getString("weidu", "");
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						this);
				alertDialog.setTitle("上传的信息如下！");
				alertDialog.setMessage("车次号：" + intent.getStringExtra("车次号") + "\n地理位置：" + sharedPreferences.getString("address", "") + "\n经纬度：" + sharedPreferences.getString("jingdu", "") + "," + sharedPreferences.getString("weidu", ""));
				alertDialog.setPositiveButton(this
								.getString(R.string.btn_ok),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
												int which) {
								HttpUtils utils = new HttpUtils();
								Log.e("url", url.toString());
								utils.getJson(url, new HttpUtils.HttpCallBack() {
									@Override
									public void onSusscess(String data) {
										Gson gson = new Gson();
										MapUpLoadingData data1 = gson.fromJson(data, MapUpLoadingData.class);
										if (data1.isIsOK() == true) {
											Toast.makeText(MapApiDemoActivity.this, "已成功上传地址信息!", Toast.LENGTH_LONG).show();
											sharedPreferences.edit().remove("jingdu");
											sharedPreferences.edit().remove("weidu");
											sharedPreferences.edit().remove("address");
										} else {
											Toast.makeText(MapApiDemoActivity.this, "上传地址信息失败!请稍后再传！", Toast.LENGTH_LONG).show();
										}
										Log.e("data", data.toString());
										new Thread(new Runnable() {
											@Override
											public void run() {
												handler.postDelayed(new Runnable() {

													@Override
													public void run() {
														button.setVisibility(View.VISIBLE);
														button1.setVisibility(View.GONE);
													}
												}, 60000);
											}
										}).start();
									}
								});
							}
						});
				alertDialog.setNegativeButton(this
								.getString(R.string.btn_cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
												int which) {

							}
						});
				alertDialog.show();
				break;
			case R.id.btn_location1:
				Toast.makeText(MapApiDemoActivity.this, "亲，别戳太快哦，请爱护屏幕，请稍后再戳！", Toast.LENGTH_SHORT).show();
				break;
		}
	}

}
