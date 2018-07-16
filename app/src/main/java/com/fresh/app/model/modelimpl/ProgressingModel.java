package com.fresh.app.model.modelimpl;

import android.util.Log;

import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.ResultBean;
import com.fresh.app.bean.RiceBucketBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.model.IProgressModel;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mr.miao on 2018/7/15.
 *
 */
public class ProgressingModel implements IProgressModel {


    @Override
    public void getRiceBucket(String deviceid) {
        HttpUtils.getRiceBucket(deviceid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RiceBucketBean>() {
                    @Override
                    public void onNext(RiceBucketBean riceBucketBean) {
                        EventBus.getDefault().post(riceBucketBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("miao","服务器异常");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("miao","请求结束");
                    }
                });
    }

    @Override
    public void updateRiceBucket(String deviceid, String riceBucketId) {
        HttpUtils.updateRiceBucketState(deviceid,riceBucketId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ResultBean>() {
                    @Override
                    public void onNext(ResultBean ResultBean) {
                        LogUtils.e(ResultBean.toString());
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
