package com.fresh.app.model;

import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.viewmodel.ProductViewModel;

import java.util.List;

/**
 * Created by mr.miao on 2018/5/7.
 */

public interface IProductModel {
    
    void getProductData(String deviceid,BaseLoadListener<ProductBean> loadListener);


}
