package com.fresh.app.bean;

import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

/**
 * Created by mr.miao on 2018/5/2.
 */

public class DebugBean implements BindingAdapterItem{
    //名称
    private String name;
    //转速
    private String speed;
    //压力
    private String pressure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public DebugBean(String name, String speed, String pressure) {
        this.name = name;
        this.speed = speed;
        this.pressure = pressure;
    }

    @Override
    public int getViewType() {
        return R.layout.item_debug_layout;
    }
}
