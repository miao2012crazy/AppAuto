package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;
import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

/**
 * Created by mr.miao on 2018/5/2.
 */

public class DebugBean2 extends BaseObservable implements BindingAdapterItem {
    private String id;
    /**
     * 气缸名称
     */
    private String name;
    /**
     * 气缸状态 伸 / 缩 / 无状态
     */
    private String state;

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
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        notifyPropertyChanged(BR.state);
    }

    public DebugBean2(String id, String name, String state) {
        this.name = name;
        this.state = state;
        this.id = id;
    }

    @Override
    public int getViewType() {
        return R.layout.item_debug2_layout;
    }
}
