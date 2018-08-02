package com.fresh.app.bean;

/**
 * Created by mr.miao on 2018/7/15.
 */

public class RiceBucketBean {


    /**
     * id : 1
     * riceBucketId : 000d015c00ac8a130e013416e0a8
     * deviceId : 20180515_01
     * productId : 201805151349
     * riceDesc : 天津小站稻
     * regDate :
     * riceBucketState : 0
     */

    private int id;
    private String riceBucketId;
    private String deviceId;
    private String productId;
    private String riceDesc;
    private String regDate;
    private int riceBucketState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRiceBucketId() {
        return riceBucketId;
    }

    public void setRiceBucketId(String riceBucketId) {
        this.riceBucketId = riceBucketId;
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

    public String getRiceDesc() {
        return riceDesc;
    }

    public void setRiceDesc(String riceDesc) {
        this.riceDesc = riceDesc;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getRiceBucketState() {
        return riceBucketState;
    }

    public void setRiceBucketState(int riceBucketState) {
        this.riceBucketState = riceBucketState;
    }
}
