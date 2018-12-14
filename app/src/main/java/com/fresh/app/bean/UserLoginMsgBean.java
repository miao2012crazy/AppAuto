package com.fresh.app.bean;

/**
 * Created by mr.miao on 2018/12/13.
 */

public class UserLoginMsgBean {

    /**
     * result : true
     * msg : 登陆成功
     * data : {"id":3,"memberId":"000d015c00fc8a130e013416e0f8","deviceId":"20180515_01","nonceStr":"abc1234567","regDate":"2018-12-13 14:48:35"}
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
         * id : 3
         * memberId : 000d015c00fc8a130e013416e0f8
         * deviceId : 20180515_01
         * nonceStr : abc1234567
         * regDate : 2018-12-13 14:48:35
         */

        private int id;
        private String memberId;
        private String deviceId;
        private String nonceStr;
        private String regDate;

        public DataBean(String deviceId, String nonceStr) {
            this.deviceId = deviceId;
            this.nonceStr = nonceStr;
        }

        public DataBean() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", memberId='" + memberId + '\'' +
                    ", deviceId='" + deviceId + '\'' +
                    ", nonceStr='" + nonceStr + '\'' +
                    ", regDate='" + regDate + '\'' +
                    '}';
        }
    }
}
