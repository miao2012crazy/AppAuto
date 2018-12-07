package com.fresh.app.bean;

/**
 * Created by mr.miao on 2018/6/25.
 */
public class QrBean {

    /**
     * wechat_url : weixin://wxpay/bizpayurl?pr=dVqM4i7
     */

    private String wechat_url;
    private String order_id;
    private String order_price;
    private String alipay_url;

    public String getAlipay_url() {
        return alipay_url;
    }

    public void setAlipay_url(String alipay_url) {
        this.alipay_url = alipay_url;
    }

    /**
     * msg : 下单成功
     */

    private String msg;

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

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
