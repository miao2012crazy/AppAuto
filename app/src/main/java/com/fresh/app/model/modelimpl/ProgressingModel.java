package com.fresh.app.model.modelimpl;

import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.model.BaseModel;
import com.fresh.app.model.IProgressModel;

/**
 * Created by mr.miao on 2018/7/15.
 *
 */
public class ProgressingModel extends BaseModel implements IProgressModel {


    @Override
    public void getRiceBucket(String deviceid) {
        map.clear();
        map.put("deviceid",deviceid);
        getDataFromNet(HttpConstant.STATE_RICEBUCKET, HttpUrl.RICEBUCKET_URL,map);

    }

    @Override
    public void updateRiceBucket(String deviceid, String riceBucketId) {
        //更新米桶编号
        map.clear();
        map.put("deviceid",deviceid);
        getDataFromNet(HttpConstant.STATE_UPDATE_RICEBUCKET, HttpUrl.URDATE_RICEBUCKET_URL,map);
    }

    @Override
    public void getVideoData(String deviceId) {
        map.clear();
        map.put("device_id",deviceId);
        getDataFromNet(HttpConstant.STATE_GET_VIDEO, HttpUrl.STATE_GET_VIDEO,map);
    }
}
