package com.fresh.app.listener;

import android.location.Location;
import android.os.Bundle;

/**
 * Created by mr.miao on 2018/7/16.
 */

public interface LocationListener {


    // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
    void onStatusChanged(String provider, int status, Bundle extras);


    // Provider被enable时触发此函数，比如GPS被打开
    void onProviderEnabled(String provider);


    // Provider被disable时触发此函数，比如GPS被关闭
    void onProviderDisabled(String provider);

    //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
    public void onLocationChanged(Location location);

}
