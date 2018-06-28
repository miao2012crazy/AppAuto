package com.fresh.app.model;

import com.fresh.app.listener.OnCreatOrderListener;

/**
 * Created by mr.miao on 2018/6/26.
 */

public interface IRechargeModel {
    void setRecharge(String memberid, String tel, String money_num, OnCreatOrderListener onCreatOrderListener);
}
