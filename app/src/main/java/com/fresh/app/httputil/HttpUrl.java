package com.fresh.app.httputil;

import com.fresh.app.constant.AppConstant;

/**
 * Created by Administrator on 2017/9/26.
 */

public class HttpUrl {

    public  static String SOCKET_URL="192.168.1.3";
    private static String baseUrl="";
    public static String getBaseUrl() {
        if (AppConstant.isDebug){
//            return "http://192.168.1.100:8080/";
            //手机热点
//            return "172.20.10.3:8080/";
            return "http://39.105.77.235/";
        }
        return baseUrl;
    }
}
