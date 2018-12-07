package com.fresh.app.model;

import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;

/**
 * Created by mr.miao on 2018/8/20.
 */

public class UpdateRiceBUcketModel extends BaseModel{


    public void getRiceBucket(String deviceId) {
        map.clear();
        map.put("deviceid",deviceId);
        getDataFromNet(HttpConstant.UPDATE_STATE_RICEBUCKET, HttpUrl.RICEBUCKET_URL,map);
    }
}
