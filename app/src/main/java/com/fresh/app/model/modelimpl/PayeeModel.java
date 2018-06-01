package com.fresh.app.model.modelimpl;

import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.FreshOrderBean;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.model.IPayeeModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mr.miao on 2018/5/10.
 */

public class PayeeModel implements IPayeeModel {
    @Override
    public void creatOrder(String product_id, String deviceid, final BaseLoadListener<FreshOrderBean> loadListener) {
        HttpUtils.creatOrder(product_id,deviceid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<FreshOrderBean>() {
                    @Override
                    public void onNext(FreshOrderBean freshOrderBean) {
                            loadListener.loadSuccess(freshOrderBean);
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
