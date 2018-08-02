package com.fresh.app.model;

/**
 * Created by mr.miao on 2018/6/1.
 */

public interface IQueryModel {
    void getCardInfo(String card_id);

    void getCardHistory(String card_id);

    void getSmsCode(String tel);


    void updateCardInfo(String code, String msg_id,String member_id, String tel, String device_id);


}
