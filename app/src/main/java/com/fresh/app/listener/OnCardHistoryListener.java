package com.fresh.app.listener;

import com.fresh.app.bean.CardHistoryBean;

import java.util.List;

/**
 * Created by mr.miao on 2018/6/3.
 */

public interface OnCardHistoryListener {
    void getCardHistorySuccess(List<CardHistoryBean.DataBean> cardHistoryBeans);
}