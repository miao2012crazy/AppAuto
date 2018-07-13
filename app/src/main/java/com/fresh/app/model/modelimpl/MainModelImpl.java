package com.fresh.app.model.modelimpl;

import com.fresh.app.bean.ReserOrderBean;
import com.fresh.app.bean.ReserveBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.listener.OnCreatReserveListener;
import com.fresh.app.listener.OnLoadReserveOrderListener;
import com.fresh.app.model.IMainModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mr.miao on 2018/7/12.
 */

public class MainModelImpl implements IMainModel {


    @Override
    public void takeGoods(String code,String device_id, OnLoadReserveOrderListener onLoadReserveOrderListener) {
        //获取所有商品（可用 可预定）
        HttpUtils.getGoods(device_id,code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ReserOrderBean>() {
                    @Override
                    public void onNext(ReserOrderBean reserOrderBean) {
                        onLoadReserveOrderListener.onSuccessed(reserOrderBean);
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
}
