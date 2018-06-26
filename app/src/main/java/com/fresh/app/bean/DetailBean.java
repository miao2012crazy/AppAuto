package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;
import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by mr.miao on 2018/6/16.
 */

public class DetailBean extends  BaseObservable implements BindingAdapterItem {

    private String product_id;

    private int id;

    private String poroduct_name;

    private int imagebg;

    private String product_desc;

    private String btn_name;

    private int btn_bg;

    private boolean visiable=false;
    @Bindable
    public boolean isVisiable() {
        return visiable;
    }

    public void setVisiable(boolean visiable) {
        this.visiable = visiable;
        notifyPropertyChanged(BR.visiable);
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public DetailBean(String product_id, int id, String poroduct_name, int imagebg, String product_desc, String btn_name, int btn_bg, boolean visiable) {
        this.product_id = product_id;
        this.id = id;
        this.poroduct_name = poroduct_name;
        this.imagebg = imagebg;
        this.product_desc = product_desc;
        this.btn_name = btn_name;
        this.btn_bg = btn_bg;
        this.visiable = visiable;
    }

    @Bindable
    public int getBtn_bg() {
        return btn_bg;
    }

    public void setBtn_bg(int btn_bg) {
        this.btn_bg = btn_bg;
        notifyPropertyChanged(BR.btn_bg);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Subscribe
    public String getPoroduct_name() {
        return poroduct_name;
    }

    public void setPoroduct_name(String poroduct_name) {
        this.poroduct_name = poroduct_name;
        notifyPropertyChanged(BR.productName);
    }
    @Bindable
    public int getImagebg() {
        return imagebg;
    }

    public void setImagebg(int imagebg) {
        this.imagebg = imagebg;
        notifyPropertyChanged(BR.imagebg);
    }
    @Bindable
    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
        notifyPropertyChanged(BR.productDesc);
    }
    @Bindable
    public String getBtn_name() {
        return btn_name;
    }

    public void setBtn_name(String btn_name) {
        this.btn_name = btn_name;
        notifyPropertyChanged(BR.btnName);
    }

    @Override
    public int getViewType() {
        return R.layout.item_detail;
    }
}
