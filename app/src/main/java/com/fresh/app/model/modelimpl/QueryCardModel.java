package com.fresh.app.model.modelimpl;

import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.model.BaseModel;
import com.fresh.app.model.IQueryModel;

/**
 * Created by mr.miao on 2018/6/1.
 */

public class QueryCardModel extends BaseModel implements IQueryModel{


    @Override
    public void getCardInfo(String card_id) {
        map.clear();
        map.put("card_id",card_id);
        getDataFromNet(HttpConstant.STATE_CARD_INFO, HttpUrl.CARD_INFO_URL,map);


    }

    @Override
    public void getCardHistory(String card_id) {
        map.clear();
        map.put("card_id",card_id);
        getDataFromNet(HttpConstant.STATE_CARD_HISTORY, HttpUrl.CARD_HISTORY_URL,map);
    }

    @Override
    public void getSmsCode(String tel) {
        map.clear();
        map.put("tel",tel);
        getDataFromNet(HttpConstant.STATE_GET_SMS_CODE, HttpUrl.GET_SMSCODE_URL,map);
    }

    @Override
    public void updateCardInfo(String code, String msg_id, String member_id, String tel, String device_id) {
        map.clear();
        map.put("code",code);
        map.put("tel",tel);
        map.put("msg_id",msg_id);
        map.put("member_id",member_id);
        map.put("device_id",device_id);
        getDataFromNet(HttpConstant.STATE_UPDATE_CARD_INFO,HttpUrl.UPDATE_CARD_INFO,map);
    }




}
