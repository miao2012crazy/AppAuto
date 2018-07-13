package com.fresh.app.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.fresh.app.BR;

import java.util.List;

/**
 * Created by mr.miao on 2018/6/3.
 */

public class CardHistoryBean {

    private List<CardHistoryItemBean> data;

    public List<CardHistoryItemBean> getData() {
        return data;
    }

    public void setData(List<CardHistoryItemBean> data) {
        this.data = data;
    }
}
