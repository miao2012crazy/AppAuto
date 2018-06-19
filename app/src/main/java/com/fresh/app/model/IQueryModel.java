package com.fresh.app.model;

import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.CardHistoryBean;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.listener.OnCardHistoryListener;

/**
 * Created by mr.miao on 2018/6/product_bg_0.
 */

public interface IQueryModel {
    void getCardInfo(String card_id,BaseLoadListener<QueryCardBean> loadListener);
    void getCardHistory(String card_id, OnCardHistoryListener loadListener);
}
