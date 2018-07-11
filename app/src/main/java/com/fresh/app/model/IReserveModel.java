package com.fresh.app.model;

import com.fresh.app.listener.OnAllProductListener;
import com.fresh.app.listener.OnCreatReserveListener;

/**
 * Created by mr.miao on 2018/6/28.
 */

public interface IReserveModel {
    void getAllProductInfo(String deviceId, OnAllProductListener onAllProductListener);

    void creatReserve(String product_id, String user_tel, String product_num,OnCreatReserveListener onCreatReserveListener);
}
