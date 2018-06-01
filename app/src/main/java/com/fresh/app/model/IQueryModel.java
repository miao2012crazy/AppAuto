package com.fresh.app.model;

import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.QueryCardBean;

/**
 * Created by mr.miao on 2018/6/1.
 */

public interface IQueryModel {
    void getCardInfo(String card_id,BaseLoadListener<QueryCardBean> loadListener);
}
