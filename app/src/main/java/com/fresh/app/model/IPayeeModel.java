package com.fresh.app.model;

import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.FreshOrderBean;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.listener.OnCreatOrderListener;

/**
 * Created by mr.miao on 2018/5/10.
 */

public interface IPayeeModel {
    boolean updateLocalData(String product_id);
    void creatOrder(String product_id, String deviceid, OnCreatOrderListener onCreatOrderListener);


}
