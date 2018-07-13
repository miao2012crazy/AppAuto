package com.fresh.app.model;

import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.CardHistoryBean;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.listener.OnCardHistoryListener;
import com.fresh.app.listener.OnGetSmsCodeListener;
import com.fresh.app.listener.OnLoadCardInfoListener;
import com.fresh.app.listener.OnUpdateCardInfoListener;

/**
 * Created by mr.miao on 2018/6/1.
 */

public interface IQueryModel {
    void getCardInfo(String card_id, OnLoadCardInfoListener onLoadCardInfoListener);

    void getCardHistory(String card_id, OnCardHistoryListener loadListener);

    void getSmsCode(String tel, OnGetSmsCodeListener onGetSmsCodeListener);


    void updateCardInfo(String code, String msg_id,String member_id, String tel, String device_id, OnUpdateCardInfoListener onUpdateCardInfoListener);


}
