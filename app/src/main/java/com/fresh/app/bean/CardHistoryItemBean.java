package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;
import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

/**
 *
 * Created by mr.miao on 2018/7/12.
 */
public class CardHistoryItemBean extends BaseObservable implements BindingAdapterItem {
    /**
     * buyProductId : 201805151349
     * buyProductPrice : 25.5
     * cardState : 0
     * id : 1
     * membershipBalance : 74.5
     * membershipId : 123
     * realName : 苗
     * regDate : 2018-06-03
     */

    private String buyProductId;
    private String buyProductPrice;
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
    public String getBuyProductPrice() {
        return buyProductPrice;
    }

    public void setBuyProductPrice(String buyProductPrice) {
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

    @Override
    public int getViewType() {
        return R.layout.item_card_history;
    }
}
