package com.fresh.app.model.modelimpl;

import com.fresh.app.bean.QRBean;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.listener.OnCreatOrderListener;
import com.fresh.app.model.IRechargeModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mr.miao on 2018/6/26.
 */

public class RechargeModelImpl implements IRechargeModel{
    @Override
    public void setRecharge(String memberid, String tel, String money_num,OnCreatOrderListener onCreatOrderListener) {
        HttpUtils.setRecharge(memberid,tel,money_num).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QRBean>() {
                    @Override
                    public void onNext(QRBean qrBean) {
                        onCreatOrderListener.onCreatOrderSuccessed(qrBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("服务器"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
