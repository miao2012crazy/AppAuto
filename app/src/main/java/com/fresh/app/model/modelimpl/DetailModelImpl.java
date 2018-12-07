package com.fresh.app.model.modelimpl;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.model.BaseModel;
import com.fresh.app.model.IDetailModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by mr.miao on 2018/5/7.
 */
public class DetailModelImpl extends BaseModel implements IDetailModel {

    @Override
    public void getDataForModel(String product_id) {

        ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();
        ProductItemBean productItemBean = productItemBeanDao.queryBuilder()
                .where(ProductItemBeanDao.Properties.ProductId.eq(product_id))
                .unique();

        if (productItemBean == null) {
            map.clear();
            map.put("product_id", product_id);
            getDataFromNet(HttpConstant.STATE_PRODUCT_DETAIL, HttpUrl.PRODUCT_DETAIL_URL, map);
        } else {
            EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_PRODUCT_DETAIL_2, productItemBean));
        }
    }

    @Override
    public void getPressureFormNet(String product_id) {
        map.clear();
        map.put("product_id", product_id);
        getDataFromNet(HttpConstant.STATE_PRESSURE, HttpUrl.GET_PRODUCT_PRESSURE,map);

    }
}