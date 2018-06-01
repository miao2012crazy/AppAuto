package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;
import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

/**
 * Created by mr.miao on 2018/5/31.
 */

public class HomeBean extends BaseObservable implements BindingAdapterItem {
    private String btnName;

    public HomeBean(String btnName) {
        this.btnName = btnName;
    }

    public HomeBean() {
    }

    @Bindable
    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
        notifyPropertyChanged(BR.btnName);
    }

    @Override
    public int getViewType() {
        return R.layout.item_home;
    }
}
