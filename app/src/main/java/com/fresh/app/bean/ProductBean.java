package com.fresh.app.bean;

import java.util.List;

/**
 * Created by mr.miao on 2018/5/7.
 */

public class ProductBean {
    private List<ProductItemBean> data;

    public List<ProductItemBean> getData() {
        return data;
    }

    public void setData(List<ProductItemBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "data=" + data +
                '}';
    }
}
