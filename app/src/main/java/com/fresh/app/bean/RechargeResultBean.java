package com.fresh.app.bean;

/**
 * Created by mr.miao on 2018/7/31.
 */

public class RechargeResultBean {


    /**
     * result : true
     * msg : 充值结果
     * data : {"id":19,"membershipId":"000b01205e724ba5040008e4","realName":"miao","cardState":1,"buyProductId":"","buyProductPrice":50,"membershipBalance":0,"regDate":"2018-07-05 11:06:01","memberOrderId":"5cc348567bf94b3a867b8a884527ed50"}
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
         * id : 19
         * membershipId : 000b01205e724ba5040008e4
         * realName : miao
         * cardState : 1
         * buyProductId :
         * buyProductPrice : 50.0
         * membershipBalance : 0.0
         * regDate : 2018-07-05 11:06:01
         * memberOrderId : 5cc348567bf94b3a867b8a884527ed50
         */

        private int id;
        private String membershipId;
        private String realName;
        private int cardState;
        private String buyProductId;
        private double buyProductPrice;
        private double membershipBalance;
        private String regDate;
        private String memberOrderId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String membershipId) {
            this.membershipId = membershipId;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getCardState() {
            return cardState;
        }

        public void setCardState(int cardState) {
            this.cardState = cardState;
        }

        public String getBuyProductId() {
            return buyProductId;
        }

        public void setBuyProductId(String buyProductId) {
            this.buyProductId = buyProductId;
        }

        public double getBuyProductPrice() {
            return buyProductPrice;
        }

        public void setBuyProductPrice(double buyProductPrice) {
            this.buyProductPrice = buyProductPrice;
        }

        public double getMembershipBalance() {
            return membershipBalance;
        }

        public void setMembershipBalance(double membershipBalance) {
            this.membershipBalance = membershipBalance;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        public String getMemberOrderId() {
            return memberOrderId;
        }

        public void setMemberOrderId(String memberOrderId) {
            this.memberOrderId = memberOrderId;
        }
    }
}
