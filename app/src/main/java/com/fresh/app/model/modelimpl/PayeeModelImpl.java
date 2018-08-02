package com.fresh.app.model.modelimpl;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.model.BaseModel;
import com.fresh.app.model.IPayeeModel;

/**
 * Created by mr.miao on 2018/5/10.
 */

public class PayeeModelImpl extends BaseModel implements IPayeeModel {
    @Override
    public boolean updateLocalData(String product_id) {
        ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();
        ProductItemBean productItemBean = productItemBeanDao.queryBuilder()
                .where(ProductItemBeanDao.Properties.ProductId.eq(product_id))
                .unique();
        int productStock = productItemBean.getDeviceProductStock();
        if (productStock<1){
            UIUtils.showToast("库存不足，您可以预定！");
            return false;
        }
        productItemBean.setDeviceProductStock(productStock-1);
        productItemBeanDao.update(productItemBean);
        return true;
    }

    @Override
    public void creatOrder(String product_id, String deviceid) {
        map.clear();
        map.put("product_id",product_id);
        map.put("deviceid",deviceid);
        getDataFromNet(HttpConstant.STATE_CREAT_ORDER, HttpUrl.CREAT_QR_URL,map);
    }

    @Override
    public void payForOrderUseCard(String order_id, String card_id) {
        map.clear();
        map.put("card_id",card_id);
        map.put("order_id",order_id);
        getDataFromNet(HttpConstant.STATE_PRODUCT_PAY_CARD,HttpUrl.PAY_PRODUCT_CARD_RESULT_URL,map);
    }
}
