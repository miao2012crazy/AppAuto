package com.fresh.app.bean;

import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

/**
 * Created by mr.miao on 2018/5/2.
 */

public class DebugBean2 implements BindingAdapterItem{
    /**
     * 气缸名称
     */
    private String name;
    /**
     * 气缸状态 伸 / 缩 / 无状态
     */
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public DebugBean2(String name, String state) {
        this.name = name;
        this.state = state;
    }

    @Override
    public int getViewType() {
        return R.layout.item_debug2_layout;
    }
}
