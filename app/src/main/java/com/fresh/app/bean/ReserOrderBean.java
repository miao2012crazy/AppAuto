package com.fresh.app.bean;

/**
 * Created by mr.miao on 2018/7/3.
 */

public class ReserOrderBean {


    /**
     * wechat_url : weixin://wxpay/bizpayurl?pr=QQDOW1j
     * order_id : 60992d656d184d74a7de53302fb6ae68
     */

    private String wechat_url;
    private String order_id;

    public String getWechat_url() {
        return wechat_url;
    }

    public void setWechat_url(String wechat_url) {
        this.wechat_url = wechat_url;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
