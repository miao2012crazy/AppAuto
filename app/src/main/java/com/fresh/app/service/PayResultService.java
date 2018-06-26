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
