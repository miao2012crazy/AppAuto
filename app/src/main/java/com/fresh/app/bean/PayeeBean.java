package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;
import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

/**
 * Created by mr.miao on 2018/4/27.
 */

public class PayeeBean extends BaseObservable implements BindingAdapterItem {
    private String pay_name;
    private int pay_image;
    private boolean visiable;
    private int itemtype;

    public PayeeBean(String pay_name, int pay_image, boolean visiable, int itemtype) {
        this.pay_name = pay_name;
        this.pay_image = pay_image;
        this.visiable = visiable;
        this.itemtype = itemtype;
    }

    public int getItemtype() {
        return itemtype;
    }

    public void setItemtype(int itemtype) {
        this.itemtype = itemtype;
    }
    @Bindable
    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
        notifyPropertyChanged(BR.pay_name);
    }
    @Bindable
    public int getPay_image() {
        return pay_image;
    }

    public void setPay_image(int pay_image) {
        this.pay_image = pay_image;
        notifyPropertyChanged(BR.pay_image);
    }

    @Bindable
    public boolean getVisiable() {
        return visiable;
    }


    public void setVisiable(boolean visiable) {
        this.visiable = visiable;
        notifyPropertyChanged(BR.visiable);
    }

    @Override
    public int getViewType() {
        return R.layout.item_pay_way;
    }
}
