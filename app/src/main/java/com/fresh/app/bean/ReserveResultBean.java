package com.fresh.app.bean;

/**
 * Created by mr.miao on 2018/7/31.
 */

public class ReserveResultBean {

    /**
     * result : true
     * msg : 数据请求成功
     * data : {"id":1,"reserveOrderId":"7ed17125a145422c9c9fbb3d535d5c1f","takeCode":"1234","deviceId":"20180515_01","productId":"201805151349","productName":"天津小站稻","productNum":1,"orderState":1,"orderPayWay":0,"orderPrice":25.5,"userTel":"18222703922","payDate":"","takeDate":"2018-07-12 11:31:45","regDate":"2018-07-12 10:47:23"}
     */

    private boolean result;
    private String msg;
    private DataBean data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * reserveOrderId : 7ed17125a145422c9c9fbb3d535d5c1f
         * takeCode : 1234
         * deviceId : 20180515_01
         * productId : 201805151349
         * productName : 天津小站稻
         * productNum : 1
         * orderState : 1
         * orderPayWay : 0
         * orderPrice : 25.5
         * userTel : 18222703922
         * payDate :
         * takeDate : 2018-07-12 11:31:45
         * regDate : 2018-07-12 10:47:23
         */

        private int id;
        private String reserveOrderId;
        private String takeCode;
        private String deviceId;
        private String productId;
        private String productName;
        private int productNum;
        private int orderState;
        private int orderPayWay;
        private double orderPrice;
        private String userTel;
        private String payDate;
        private String takeDate;
        private String regDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
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

        public int getOrderState() {
            return orderState;
        }

        public void setOrderState(int orderState) {
            this.orderState = orderState;
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

        public String getUserTel() {
            return userTel;
        }

        public void setUserTel(String userTel) {
            this.userTel = userTel;
        }

        public String getPayDate() {
            return payDate;
        }

        public void setPayDate(String payDate) {
            this.payDate = payDate;
        }

        public String getTakeDate() {
            return takeDate;
        }

        public void setTakeDate(String takeDate) {
            this.takeDate = takeDate;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }
    }
}
