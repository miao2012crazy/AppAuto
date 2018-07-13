package com.fresh.app.model.modelimpl;

import android.util.Log;

import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.CardHistoryBean;
import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.listener.OnCardHistoryListener;
import com.fresh.app.listener.OnGetSmsCodeListener;
import com.fresh.app.listener.OnLoadCardInfoListener;
import com.fresh.app.listener.OnUpdateCardInfoListener;
import com.fresh.app.model.IQueryModel;

import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.HTTP;

/**
 * Created by mr.miao on 2018/6/1.
 */

public class QueryCardModel implements IQueryModel{


    @Override
    public void getCardInfo(String card_id, OnLoadCardInfoListener onLoadCardInfoListener) {
        HttpUtils.getCardInfo(card_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<QueryCardBean>() {
                    @Override
                    public void onNext(QueryCardBean queryCardBean) {
                        LogUtils.e(queryCardBean.toString());
                        onLoadCardInfoListener.onSuccessed(queryCardBean);
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

    @Override
    public void getSmsCode(String tel, OnGetSmsCodeListener onGetSmsCodeListener) {
        HttpUtils.getSmsCode(tel).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                               @Override
                               public void onNext(String msg_id) {
                                   onGetSmsCodeListener.getSmsCodeSuccessed(msg_id);
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

    @Override
    public void updateCardInfo(String code, String msg_id, String member_id, String tel, String device_id, OnUpdateCardInfoListener onUpdateCardInfoListener) {
        HttpUtils.updateCard(code,tel,msg_id,member_id,device_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                               @Override
                               public void onNext(String result) {
                                   try{
                                   boolean b = Boolean.parseBoolean(result);
                                       onUpdateCardInfoListener.OnUpdateCardSuccessed(b);
                                   }catch (Exception ex){
                                        onUpdateCardInfoListener.OnUpdateCardFailed("更新失败");
                                   }

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
