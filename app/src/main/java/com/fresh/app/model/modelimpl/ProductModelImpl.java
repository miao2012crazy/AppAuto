package com.fresh.app.model.modelimpl;

import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.model.BaseModel;
import com.fresh.app.model.IProductModel;


/**
 * Created by mr.miao on 2018/5/7.
 */
public class ProductModelImpl extends BaseModel implements IProductModel {

    @Override
    public void getProductData(String deviceid) {
        map.clear();
        map.put("deviceid", deviceid);
        getDataFromNet(HttpConstant.STATE_PRODUCT, HttpUrl.PRODUCT_URL, map);
    }
}
