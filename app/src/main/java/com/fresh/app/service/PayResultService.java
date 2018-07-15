package com.fresh.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.httputil.HttpUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mr.miao on 2018/6/3.
 */

public class PayResultService extends Service {

    private Timer timer = new Timer();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer.schedule(timerTask,0,2000);
    }


    private TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            if (CustomApplaction.ISRESULT){
                getResultFromNet();
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void getResultFromNet() {
        switch (CustomApplaction.RESULT_CODE){
            case 0:
                //普通支付
                getData();

                break;
            case 1:
                getWechatRechargeResult();


                //充值
                break;
            case 2:
                //预定支付
                getReserveResult();


                break;
        }





    }

    private void getReserveResult() {
        HttpUtils.getReserveOrderState(CustomApplaction.ORDER_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e("miao","支付查询"+CustomApplaction.ORDER_ID+"支付结果："+s);
                        if (s.equals("1")){
                            CustomApplaction.ISRESULT=false;
                            CustomApplaction.ORDER_ID="";
                            EventBus.getDefault().post(new MessageEvent(10102,"支付成功！"));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("服务器一场"+e.getMessage());
                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 充值
     */
    private void getWechatRechargeResult() {
        HttpUtils.getRechargeOrderState(CustomApplaction.ORDER_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e("miao","支付查询"+CustomApplaction.ORDER_ID+"支付结果："+s);
                        if (s.equals("1")){
                            CustomApplaction.ISRESULT=false;
                            CustomApplaction.ORDER_ID="";
                            EventBus.getDefault().post(new MessageEvent(10100,"充值成功！"));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("服务器一场"+e.getMessage());
                    }
                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void getData() {
        if (CustomApplaction.ORDER_ID.equals("")){
            LogUtils.e("订单id为\"\"");
            return;
        }


        HttpUtils.getPayResult(CustomApplaction.ORDER_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        Log.e("miao","支付查询"+CustomApplaction.ORDER_ID+"支付结果："+s);
                        if (s.equals("1")){
                            CustomApplaction.ISRESULT=false;
                            CustomApplaction.ORDER_ID="";
                            EventBus.getDefault().post(new MessageEvent(1009,"支付成功！"));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timerTask.cancel();
    }
}
