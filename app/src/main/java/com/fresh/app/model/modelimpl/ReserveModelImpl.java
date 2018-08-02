package com.fresh.app.model.modelimpl;


import com.fresh.app.constant.AppConstant;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.model.BaseModel;
import com.fresh.app.model.IReserveModel;

/**
 * Created by mr.miao on 2018/6/28.
 */

public class ReserveModelImpl extends BaseModel implements IReserveModel {

    @Override
    public void getAllProductInfo(String deviceId) {
        map.clear();
        map.put("deviceid",deviceId);
        getDataFromNet(HttpConstant.RESERVE_ALL_PRODUCT, HttpUrl.ALL_PRODUCT_URL,map);
    }

    @Override
    public void creatReserve(String product_id, String user_tel, String product_num) {
        map.clear();
        map.put("device_id", AppConstant.DEVICE_ID);
        map.put("product_id",product_id);
        map.put("user_tel",user_tel);
        map.put("product_num",product_num);
        getDataFromNet(HttpConstant.STATE_RESERVE_CREAT,HttpUrl.RESERVE_CREAT_URL,map);
    }
}
