package com.fresh.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mr.miao on 2018/6/25.
 */
public class QrBean {

    /**
     * wechat_url : weixin://wxpay/bizpayurl?pr=dVqM4i7
     */

    private String wechat_url;
    private String order_id;
    /**
     * msg : 下单成功
     */

    private String msg;


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getWechat_url() {
        return wechat_url;
    }

    public void setWechat_url(String wechat_url) {
        this.wechat_url = wechat_url;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
