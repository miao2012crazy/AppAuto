package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;

/**
 * Created by mr.miao on 2018/6/product_bg_0.
 */

public class QueryCardBean extends BaseObservable {

    /**
     * id : product_bg_0
     * membershipBalance : 99.99
     * membershipId : 123
     * membershipTel : 18222703922
     * membershipUsable : 0
     * realName : miao
     * realNum : 220800199110110416
     * regDate : 1231
     */

    private int id;
    private double membershipBalance;
    private String membershipId;
    private String membershipTel;
    private int membershipUsable;
    private String realName;
    private String realNum;
    private String regDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public double getMembershipBalance() {
        return membershipBalance;
    }

    public void setMembershipBalance(double membershipBalance) {
        this.membershipBalance = membershipBalance;
        notifyPropertyChanged(BR.membershipBalance);
    }

    @Bindable
    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
        notifyPropertyChanged(BR.membershipId);
    }

    @Bindable
    public String getMembershipTel() {
        return membershipTel;
    }


    public void setMembershipTel(String membershipTel) {
        this.membershipTel = membershipTel;
        notifyPropertyChanged(BR.membershipTel);
    }
    @Bindable
    public int getMembershipUsable() {
        return membershipUsable;
    }

    public void setMembershipUsable(int membershipUsable) {
        this.membershipUsable = membershipUsable;
        notifyPropertyChanged(BR.membershipUsable);
    }
    @Bindable
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
        notifyPropertyChanged(BR.realName);
    }
    @Bindable
    public String getRealNum() {
        return realNum;
    }

    public void setRealNum(String realNum) {
        this.realNum = realNum;
        notifyPropertyChanged(BR.realNum);
    }
    @Bindable
    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
        notifyPropertyChanged(BR.regDate);

    }

    @Override
    public String toString() {
        return "QueryCardBean{" +
                "id=" + id +
                ", membershipBalance=" + membershipBalance +
                ", membershipId='" + membershipId + '\'' +
                ", membershipTel='" + membershipTel + '\'' +
                ", membershipUsable=" + membershipUsable +
                ", realName='" + realName + '\'' +
                ", realNum='" + realNum + '\'' +
                ", regDate='" + regDate + '\'' +
                '}';
    }
}
