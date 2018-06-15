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
    private int id;
    private int imagebg;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public int getImagebg() {
        return imagebg;
    }

    public void setImagebg(int imagebg) {
        this.imagebg = imagebg;
        notifyPropertyChanged(BR.imagebg);
    }


    public HomeBean(String btnName, int id, int imagebg) {
        this.btnName = btnName;
        this.id = id;
        this.imagebg = imagebg;
    }

    public HomeBean(String btnName, int imagebg) {
        this.btnName = btnName;
        this.imagebg = imagebg;
    }

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
