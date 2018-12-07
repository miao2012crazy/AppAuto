package com.fresh.app.view.viewimpl;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.fresh.app.MainActivity;
import com.fresh.app.R;
import com.fresh.app.base.BaseActivity;
import com.fresh.app.bean.DebugBean;
import com.fresh.app.bean.RiceBucketBean;
import com.fresh.app.commonUtil.CardReaderManage;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.ActivityUpdateRicebucketBinding;
import com.fresh.app.handler.HandlerEvent;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.viewmodel.UpdateRiceBucketVM;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mr.miao on 2018/8/20.
 */

public class UpdateRiceBucketActivity extends BaseActivity {

    private List<RiceBucketBean> mlist = new ArrayList<>();
    private HandlerEvent handlerEvent;
    private TimerTask timerTask;
    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);



        ActivityUpdateRicebucketBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_update_ricebucket);
        binding.btnStart.setOnClickListener(v -> {

            DebugBean bean3_4 = new DebugBean("34", "shoudong", "", "");
            handlerEvent.start(new View(this), bean3_4);

            DebugBean bean0_0 = new DebugBean("00", "提米机转动", "", "");
            handlerEvent.start(new View(this), bean0_0);
            //
//            10秒 自动检测 是否加工完成
                    timerTask = new TimerTask() {
                @Override
                public void run() {
                    boolean zero = UIUtils.isZero();
                    Log.e("miao", "装米完成" + zero);
//                    handlerEvent.stop(new View(UpdateRiceBucketActivity.this), bean0_0);
//                    handlerEvent.stop(new View(UpdateRiceBucketActivity.this), bean3_4);
//                handlerEvent.returnMain(new View(UpdateRiceBucketActivity.this));
                }
            };
            timer = new Timer();

            timer.schedule(timerTask, 10000);

            //装米完成
//            finish();
        });
        CardReaderManage.setCardReaderState(3);
        UpdateRiceBucketVM updateRiceBucketVM = new UpdateRiceBucketVM(binding);
        handlerEvent = new HandlerEvent(this);
        DebugBean bean2_7 = new DebugBean("27", "shoudong", "", "");
        handlerEvent.start(new View(this), bean2_7);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveData(NetResponse netResponse) {
        switch (netResponse.getTag()) {
            case HttpConstant.UPDATE_STATE_RICEBUCKET:
                List<RiceBucketBean> riceBucketBeans = GsonUtil.jsonToList((String) netResponse.getData(), RiceBucketBean.class);
                CardReaderManage.setCardReaderState(3);
                for (int i = 0; i < riceBucketBeans.size(); i++) {
                    RiceBucketBean item = riceBucketBeans.get(i);
                    if (item.getRiceBucketState() == 0) {
                        mlist.add(item);
                        Log.e("miaomiao",mlist.size()+"");


                    }
                }

                startUpdate();
                break;

            case HttpConstant.STATE_RICEBUCKET_ID:
                String riceBucketId = (String) netResponse.getData();
                Log.e("miaomiao",riceBucketId);
                for (RiceBucketBean item : mlist) {
                    if (riceBucketId.equals(item.getRiceBucketId())) {
                        DebugBean bean0_0 = new DebugBean("32", "装米模式空米桶到达", "", "");
                        handlerEvent.start(new View(this), bean0_0);
                        handlerEvent.stop(new View(this), bean0_0);

                        mlist.remove(item);
                        if (mlist.size() == 0) {
                            EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR, "全部更换完成"));
                            startActivityBase(MainActivity.class);
                        }


                    }
                }


        }
    }

    private void startUpdate() {
        if (mlist.size() == 0) {
            EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR, "没有空米桶！"));
            return;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DebugBean bean0_0 = new DebugBean("32", "装米模式空米桶到达", "", "");
        handlerEvent.stop(new View(this), bean0_0);

        DebugBean bean2_7 = new DebugBean("27", "shoudong", "", "");
        handlerEvent.stop(new View(this), bean2_7);
    }
}
