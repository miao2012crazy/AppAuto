package com.fresh.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fresh.app.commonUtil.LocationUtils;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.UIUtils;

/**
 * Created by mr.miao on 2018/4/23.
 */

public abstract class BaseActivity extends AppCompatActivity implements LocationListener {

    protected Bundle bundle = new Bundle();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Location location = LocationUtils.getInstance(this).showLocation();
        if (location != null) {
            String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
            Log.e("FLY.LocationUtils", address);
        }


    }

    //启动页面不带参数 直接启动
    protected void startActivityBase(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }



    /**
     * 带参数启动
     *
     * @param context
     * @param clazz
     * @param bundle
     */
    private void startActivityBase(Context context, Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    public void onLocationChanged(Location location) {
        LogUtils.e(location.getLatitude() + ":" + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LogUtils.e("异常");
    }

    @Override
    public void onProviderEnabled(String provider) {
        LogUtils.e("yichang");
    }

    @Override
    public void onProviderDisabled(String provider) {
        LogUtils.e("yichang");
    }
}
