package com.fresh.app.model;

import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.ProductDetailBean;

/**
 * Created by mr.miao on 2018/5/7.
 */
public interface IDetailModel {
    void getDataForModel(String product_id,BaseLoadListener<ProductDetailBean> loadListener);
}
