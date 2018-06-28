package com.fresh.app.bean;

import android.databinding.BaseObservable;

import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

/**
 * Created by mr.miao on 2018/6/27.
 */

public class MoneyBean extends BaseObservable implements BindingAdapterItem{
    @Override
    public int getViewType() {
        return R.layout.item_money;
    }

    private int id;


    private String money_price;

    private boolean isChecked=false;

    public MoneyBean(int id, String money_price, boolean isChecked) {
        this.id = id;
        this.money_price = money_price;
        this.isChecked = isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney_price() {
        return money_price;
    }

    public void setMoney_price(String money_price) {
        this.money_price = money_price;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "MoneyBean{" +
                "id=" + id +
                ", money_price='" + money_price + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
