package com.fresh.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.bean.FreshOrderBean;
import com.fresh.app.bean.RechargeResultBean;
import com.fresh.app.bean.ReserveResultBean;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.httputil.NovateUtil;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;

/**
 * Created by mr.miao on 2018/6/3.
 */

public class PayResultService extends Service {
    /**
     * post请求参数集合  使用前请清空
     */
    private HashMap<String,Object> map=new HashMap<>();
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

    /**
     * 预定
     */
    private void getReserveResult() {
        map.clear();
        map.put("order_id",CustomApplaction.ORDER_ID);
        getDataFromNet(HttpConstant.STATE_RESERVE_RESIULT,HttpUrl.RESERVE_RESULT_URL,map);


    }

    /**
     * 充值
     */
    private void getWechatRechargeResult() {
        map.clear();
        map.put("order_id",CustomApplaction.ORDER_ID);
        getDataFromNet(HttpConstant.STATE_RECHARGE_PAY_RESULT, HttpUrl.PAY_RECHARGE_RESULT_URL,map);

    }

    /**
     * 购买商品
     */
    private void getData() {
        if (CustomApplaction.ORDER_ID.equals("")){
            LogUtils.e("订单id为null");
            return;
        }
        map.clear();
        map.put("order_id",CustomApplaction.ORDER_ID);
        getDataFromNet(HttpConstant.STATE_PRODUCT_PAY_STATE, HttpUrl.PAY_PRODUCT_RESULT_URL,map);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timerTask.cancel();
    }



    protected void getDataFromNet(String tag, String url, HashMap<String, Object> map) {

        NovateUtil.getInstance().post(url, map, new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                Log.e("miao", "请求失败");
            }

            @Override
            public void onNext(ResponseBody responseBody) {

                try {
                    switch (CustomApplaction.RESULT_CODE){
                        case 0:
                            FreshOrderBean freshOrderBean = GsonUtil.GsonToBean(responseBody.string(), FreshOrderBean.class);

                            if (freshOrderBean.isResult()) {
                                Log.e("新网络框架", "请求成功");
                                EventBus.getDefault().post(new NetResponse(tag, freshOrderBean));
                            } else {
                                UIUtils.showToast(freshOrderBean.getMsg());
                            }

                            break;
                        case 1:
                            RechargeResultBean rechargeResultBean = GsonUtil.GsonToBean(responseBody.string(), RechargeResultBean.class);

                            if (rechargeResultBean.isResult()) {
                                Log.e("新网络框架", "请求成功");
                                EventBus.getDefault().post(new NetResponse(tag, rechargeResultBean));
                            } else {
                                UIUtils.showToast(rechargeResultBean.getMsg());
                            }
                            break; case 2:
                            ReserveResultBean reserveResultBean = GsonUtil.GsonToBean(responseBody.string(), ReserveResultBean.class);

                            if (reserveResultBean.isResult()) {
                                Log.e("新网络框架", "请求成功");
                                EventBus.getDefault().post(new NetResponse(tag, reserveResultBean));
                            } else {
                                UIUtils.showToast(reserveResultBean.getMsg());
                            }
                            break;

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
