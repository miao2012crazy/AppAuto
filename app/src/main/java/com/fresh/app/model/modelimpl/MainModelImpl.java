package com.fresh.app.model.modelimpl;

import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.model.BaseModel;
import com.fresh.app.model.IMainModel;

/**
 * Created by mr.miao on 2018/7/12.
 */

public class MainModelImpl extends BaseModel implements IMainModel {


    @Override
    public void takeGoods(String code, String device_id) {
        map.clear();
        map.put("device_id", device_id);
        map.put("sms_code", code);
        getDataFromNet(HttpConstant.STATE_TAKEGOODS, HttpUrl.TAKEGOODS_URL, map);
    }

    @Override
    public void checkDeviceID(String device_id) {
        map.clear();
        map.put("device_id", device_id);
        getDataFromNet(HttpConstant.STATE_CHECK, HttpUrl.STATE_CHECK, map);
    }
}
