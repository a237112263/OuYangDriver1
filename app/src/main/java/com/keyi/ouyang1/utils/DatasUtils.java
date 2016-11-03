package com.keyi.ouyang1.utils;

import android.content.Context;

import com.keyi.ouyang1.Bean.SMSData;

/**
 * Created by Administrator on 2016/4/27.
 */
public class DatasUtils {
    public static final String yanzhengUrl="http://appweb.keyierp.com/sms.aspx?m=";
    public static final String mobUrl="http://appweb.keyierp.com/ERP/Login.aspx?mobile=";
    public static final String detailsUrl="http://oyapp.keyierp.com/OYWL/GetDateByDriverMobile.aspx?mobile=";
    public static final String strToken="&Token=";
    public static final String updateUrl="http://oyapp.keyierp.com/OYWL/UpdateConsignmentStatus.aspx?mobile=";
    public static final String updateAdressUrl="http://oyapp.keyierp.com/OYWL/UpdateAddr.aspx?mobile=";
    public static final String dimension="&dimension=";
    public String SMSData(Context context){
        ACache aCache=ACache.get(context);
        SMSData smsData= (SMSData) aCache.getAsObject("SMSData");
        return smsData.getData().toString();
    }
    public String MobilNumber(Context context){
        ACache aCache=ACache.get(context);
        return aCache.getAsString("MobilNumber").toString();
    }

}
