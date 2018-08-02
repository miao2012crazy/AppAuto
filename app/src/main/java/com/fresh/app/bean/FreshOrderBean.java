package com.fresh.app.bean;

/**
 * Created by mr.miao on 2018/5/14.
 */

public class FreshOrderBean {

    /**
     * result : true
     * msg : 订单结果
     * data : {"id":87,"orderId":"fac9f5c146ec4adaa3407b1af82ab7df","singleDeviceId":"20180515_01","productName":"泰国香米","productImg01":"3.png","productImg02":"1525919372344.jpg","productImg03":"ic_buy_2.png","productImg04":"","productDesc":"其香糯的口感和独特的露兜树香味享誉世界","productOption":"","productManage":"泰国","productPrice":45.5,"orderPrice":45.5,"orderCreatTime":"2018-07-31 14:40:10","orderState":0,"orderPay":0,"regDate":"2018-07-31 14:40:10"}
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
         * id : 87
         * orderId : fac9f5c146ec4adaa3407b1af82ab7df
         * singleDeviceId : 20180515_01
         * productName : 泰国香米
         * productImg01 : 3.png
         * productImg02 : 1525919372344.jpg
         * productImg03 : ic_buy_2.png
         * productImg04 :
         * productDesc : 其香糯的口感和独特的露兜树香味享誉世界
         * productOption :
         * productManage : 泰国
         * productPrice : 45.5
         * orderPrice : 45.5
         * orderCreatTime : 2018-07-31 14:40:10
         * orderState : 0
         * orderPay : 0
         * regDate : 2018-07-31 14:40:10
         */

        private int id;
        private String orderId;
        private String singleDeviceId;
        private String productName;
        private String productImg01;
        private String productImg02;
        private String productImg03;
        private String productImg04;
        private String productDesc;
        private String productOption;
        private String productManage;
        private double productPrice;
        private double orderPrice;
        private String orderCreatTime;
        private int orderState;
        private int orderPay;
        private String regDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getSingleDeviceId() {
            return singleDeviceId;
        }

        public void setSingleDeviceId(String singleDeviceId) {
            this.singleDeviceId = singleDeviceId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductImg01() {
            return productImg01;
        }

        public void setProductImg01(String productImg01) {
            this.productImg01 = productImg01;
        }

        public String getProductImg02() {
            return productImg02;
        }

        public void setProductImg02(String productImg02) {
            this.productImg02 = productImg02;
        }

        public String getProductImg03() {
            return productImg03;
        }

        public void setProductImg03(String productImg03) {
            this.productImg03 = productImg03;
        }

        public String getProductImg04() {
            return productImg04;
        }

        public void setProductImg04(String productImg04) {
            this.productImg04 = productImg04;
        }

        public String getProductDesc() {
            return productDesc;
        }

        public void setProductDesc(String productDesc) {
            this.productDesc = productDesc;
        }

        public String getProductOption() {
            return productOption;
        }

        public void setProductOption(String productOption) {
            this.productOption = productOption;
        }

        public String getProductManage() {
            return productManage;
        }

        public void setProductManage(String productManage) {
            this.productManage = productManage;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public double getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(double orderPrice) {
            this.orderPrice = orderPrice;
        }

        public String getOrderCreatTime() {
            return orderCreatTime;
        }

        public void setOrderCreatTime(String orderCreatTime) {
            this.orderCreatTime = orderCreatTime;
        }

        public int getOrderState() {
            return orderState;
        }

        public void setOrderState(int orderState) {
            this.orderState = orderState;
        }

        public int getOrderPay() {
            return orderPay;
        }

        public void setOrderPay(int orderPay) {
            this.orderPay = orderPay;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }
    }
}
