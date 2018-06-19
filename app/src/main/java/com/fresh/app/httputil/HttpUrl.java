package com.fresh.app.httputil;

import com.fresh.app.constant.AppConstant;

/**
 * Created by Administrator on 2017/9/26.
 */

public class HttpUrl {

    public  static String SOCKET_URL="192.168.product_bg_0.3";
    private static String baseUrl="";
    public static String getBaseUrl() {
        if (AppConstant.isDebug){
            return "http://192.168.product_bg_0.101:8080/";
//            return "http://39.105.77.235/";
        }
        return baseUrl;
    }
}
