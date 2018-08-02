package com.fresh.app.model;

/**
 * Created by mr.miao on 2018/5/10.
 */

public interface IPayeeModel {
    boolean updateLocalData(String product_id);
    void creatOrder(String product_id, String deviceid );

    void payForOrderUseCard(String order_id,String card_id);

}
