package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;
import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

/**
 * Created by mr.miao on 2018/5/2.
 */

public class DebugBean extends BaseObservable implements BindingAdapterItem{
    //名称
    private String name;
    //转速
    private String speed;
    //压力
    private String pressure;
    //id
    private String id;
    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
        notifyPropertyChanged(BR.speed);
    }
    @Bindable
    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
        notifyPropertyChanged(BR.pressure);
    }

    public DebugBean(String id,String name, String speed, String pressure) {
        this.name = name;
        this.speed = speed;
        this.pressure = pressure;
        this.id=id;
    }

    @Override
    public int getViewType() {
        return R.layout.item_debug_layout;
    }
}
