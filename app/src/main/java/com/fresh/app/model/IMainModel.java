package com.fresh.app.model;

/**
 * Created by mr.miao on 2018/7/12.
 */

public interface IMainModel {
    void takeGoods(String code,String device_id);

    void checkDeviceID(String device_id);

    void bindClientId(String client_id, String deviceId);
}
