package com.fresh.app.model;

import com.fresh.app.listener.OnCreatReserveListener;
import com.fresh.app.listener.OnLoadReserveOrderListener;

/**
 * Created by mr.miao on 2018/7/12.
 */

public interface IMainModel {
    void takeGoods(String code,String device_id,OnLoadReserveOrderListener onLoadReserveOrderListener);
}
