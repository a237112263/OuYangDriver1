package com.keyi.ouyang1;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.karumi.dexter.Dexter;

import cn.bmob.v3.Bmob;

/**
 * Application类是整个应用程序初始化的上下文
 * 
 * @author Administrator
 * 
 */
public class MyApplicaion extends Application {
	/**
	 * 本方法会在应用程序启动的时候被调用，这个方法中，可以去做一些对整个应用进行初始化的工作
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Dexter.initialize(this);
		SDKInitializer.initialize(getApplicationContext());
		Bmob.initialize(this, "a66ad8ab7ed32dd6828d5e3d23c2f1eb");
		System.out.println("MyApplicaion onCreate");
	}
}
