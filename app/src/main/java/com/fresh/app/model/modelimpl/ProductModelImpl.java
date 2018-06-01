package com.fresh.app.model.modelimpl;

import android.util.Log;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.model.IProductModel;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by mr.miao on 2018/5/7.
 */
public class ProductModelImpl implements IProductModel {


    @Override
    public void getProductData(String deviceid,final BaseLoadListener<ProductBean> loadListener) {
        HttpUtils.getProduct(deviceid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ProductBean>() {
                    @Override
                    public void onNext(ProductBean productBean) {
                        Log.e("miao",productBean.toString());
                        loadListener.loadSuccess(productBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("miao",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("miao","请求结束");
                    }
                });
    }




}
