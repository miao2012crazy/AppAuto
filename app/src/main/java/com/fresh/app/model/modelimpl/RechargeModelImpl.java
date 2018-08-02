package com.fresh.app.model.modelimpl;

import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.model.BaseModel;
import com.fresh.app.model.IRechargeModel;

/**
 * Created by mr.miao on 2018/6/26.
 */

public class RechargeModelImpl extends BaseModel implements IRechargeModel{
    @Override
    public void setRecharge(String memberid, String tel, String money_num) {
        map.clear();
        map.put("member_id",memberid);
        map.put("tel",tel);
        map.put("money_num",money_num);
        getDataFromNet(HttpConstant.STATE_RECHARGE_CREAT, HttpUrl.RECHARGE_CREAT_URL,map);
    }
}
