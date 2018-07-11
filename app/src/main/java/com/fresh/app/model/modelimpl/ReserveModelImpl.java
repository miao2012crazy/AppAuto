package com.fresh.app.model.modelimpl;


import android.util.Log;

import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.QRBean;
import com.fresh.app.bean.ReserOrderBean;
import com.fresh.app.bean.ReserveBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.listener.OnAllProductListener;
import com.fresh.app.listener.OnCreatReserveListener;
import com.fresh.app.model.IReserveModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mr.miao on 2018/6/28.
 */

public class ReserveModelImpl implements IReserveModel {

    @Override
    public void getAllProductInfo(String deviceId, OnAllProductListener onAllProductListener) {
        //获取所有商品（可用 可预定）
        HttpUtils.getAllProduct(deviceId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ReserveBean>() {
                    @Override
                    public void onNext(ReserveBean reserveBean) {
                        onAllProductListener.onSuccessed(reserveBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("请求失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void creatReserve(String product_id, String user_tel, String product_num, OnCreatReserveListener onCreatReserveListener) {

        //获取所有商品（可用 可预定）
        HttpUtils.creatReserveOrder("20180515_01",product_id,user_tel,product_num).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QRBean>() {
                    @Override
                    public void onNext(QRBean qrBean) {
                        onCreatReserveListener.onCreatSuccessed(qrBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
