package com.fresh.app.model;

/**
 * Created by mr.miao on 2018/6/28.
 */

public interface IReserveModel {
    void getAllProductInfo(String deviceId);

    void creatReserve(String product_id, String user_tel, String product_num);
}
