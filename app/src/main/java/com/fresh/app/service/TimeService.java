package com.fresh.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mr.miao on 2018/5/22.
 */

public class TimeService extends Service {

    private Timer timer = new Timer();
    private boolean isFirst=false;

    private TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            boolean zero = UIUtils.isZero();
            Log.e("miao","定时器执行"+zero);
            if (zero){
                EventBus.getDefault().post(new MessageEvent(1002,"到时间了 发送网络请求吧"));
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isFirst){
            //开机10秒自动启动定时器  每次关联 从新请求网络
            EventBus.getDefault().post(new MessageEvent(1002,"自动获取网络数据"));
            isFirst=false;
        }
        timer.schedule(timerTask,10000,1000*60*30);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
