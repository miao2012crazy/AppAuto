package com.fresh.app.bean;

import java.util.List;

/**
 * Created by mr.miao on 2018/5/7.
 */

public class ReserveBean {
    private List<ReserveItemBean> data;

    public List<ReserveItemBean> getData() {
        return data;
    }

    public void setData(List<ReserveItemBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "data=" + data +
                '}';
    }
}
