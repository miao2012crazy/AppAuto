package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;

import java.util.List;

/**
 * Created by mr.miao on 2018/6/3.
 */

public class CardHistoryBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BaseObservable{
        /**
         * buyProductId : 201805151349
         * buyProductPrice : 25.5
         * cardState : 0
         * id : product_bg_0
         * membershipBalance : 74.5
         * membershipId : 123
         * realName : 苗
         * regDate : 2018-06-03
         */

        private String buyProductId;
        private double buyProductPrice;
        private int cardState;
        private int id;
        private double membershipBalance;
        private String membershipId;
        private String realName;
        private String regDate;
        @Bindable
        public String getBuyProductId() {
            return buyProductId;
        }

        public void setBuyProductId(String buyProductId) {
            this.buyProductId = buyProductId;
            notifyPropertyChanged(BR.buyProductId);
        }
        @Bindable
        public double getBuyProductPrice() {
            return buyProductPrice;
        }

        public void setBuyProductPrice(double buyProductPrice) {
            this.buyProductPrice = buyProductPrice;
            notifyPropertyChanged(BR.buyProductPrice);
        }
        @Bindable
        public int getCardState() {
            return cardState;
        }

        public void setCardState(int cardState) {
            this.cardState = cardState;
            notifyPropertyChanged(BR.cardState);
        }

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
        public String getRealName() {
            return realName;

        }

        public void setRealName(String realName) {
            this.realName = realName;
            notifyPropertyChanged(BR.realName);
        }
        @Bindable
        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
            notifyPropertyChanged(BR.regDate);
        }
    }
}
