package com.fresh.app.model.modelimpl;

import android.util.Log;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.FreshOrderBean;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.QRBean;
import com.fresh.app.bean.ResultBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.gen.ProductDetailBeanDao;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.listener.OnCreatOrderListener;
import com.fresh.app.listener.OnPayResultListener;
import com.fresh.app.model.IPayeeModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mr.miao on 2018/5/10.
 */

public class PayeeModelImpl implements IPayeeModel {
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
    public void creatOrder(String product_id, String deviceid, OnCreatOrderListener onCreatOrderListener) {
        HttpUtils.creatOrder(product_id,deviceid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QRBean>() {
                    @Override
                    public void onNext(QRBean qrBean) {
                        onCreatOrderListener.onCreatOrderSuccessed(qrBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("服务器bug"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void payForOrderUseCard(String order_id, String card_id, OnPayResultListener onPayResultListener) {
        HttpUtils.payOrderUseCard(order_id,card_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean>() {
                    @Override
                    public void onNext(ResultBean resultBean) {
                        if (resultBean.getResult().equals("Successed")){
                            onPayResultListener.onPaySuccessed();
                        }else {
                            onPayResultListener.onPayFailed(resultBean.getErr_msg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("服务器bug"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
