package com.fresh.app.model.modelimpl;

import android.util.Log;

import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.CardHistoryBean;
import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.listener.OnCardHistoryListener;
import com.fresh.app.model.IQueryModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mr.miao on 2018/6/product_bg_0.
 */

public class QueryCardModel implements IQueryModel{


    @Override
    public void getCardInfo(String card_id, BaseLoadListener<QueryCardBean> loadListener) {
        HttpUtils.getCardInfo(card_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QueryCardBean>() {
                    @Override
                    public void onNext(QueryCardBean queryCardBean) {
                        LogUtils.e(queryCardBean.toString());
                        loadListener.loadSuccess(queryCardBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCardHistory(String card_id, OnCardHistoryListener loadListener) {
        HttpUtils.getCardHistory(card_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<CardHistoryBean>() {
                               @Override
                               public void onNext(CardHistoryBean cardHistoryBean) {
                                   loadListener.getCardHistorySuccess(cardHistoryBean.getData());
                               }

                               @Override
                               public void onError(Throwable e) {

                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );
    }

}
