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
    /**
     * deviceId : 20180515_01
     * id : 1
     * orderPayWay : 0
     * orderPrice : 25.5
     * orderState : 0
     * payDate :
     * productId : 201805151349
     * productName : 天津小站稻
     * productNum : 1
     * regDate : 2018-07-12 10:47:23
     * reserveOrderId : 7ed17125a145422c9c9fbb3d535d5c1f
     * takeCode : 1234
     * takeDate :
     * userTel : 18222703922
     */

    private String deviceId;
    private int id;
    private int orderPayWay;
    private double orderPrice;
    private int orderState;
    private String payDate;
    private String productId;
    private String productName;
    private int productNum;
    private String regDate;
    private String reserveOrderId;
    private String takeCode;
    private String takeDate;
    private String userTel;

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderPayWay() {
        return orderPayWay;
    }

    public void setOrderPayWay(int orderPayWay) {
        this.orderPayWay = orderPayWay;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getReserveOrderId() {
        return reserveOrderId;
    }

    public void setReserveOrderId(String reserveOrderId) {
        this.reserveOrderId = reserveOrderId;
    }

    public String getTakeCode() {
        return takeCode;
    }

    public void setTakeCode(String takeCode) {
        this.takeCode = takeCode;
    }

    public String getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(String takeDate) {
        this.takeDate = takeDate;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
}
