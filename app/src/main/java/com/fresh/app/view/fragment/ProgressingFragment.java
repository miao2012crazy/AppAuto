package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;
import com.fresh.app.MainActivity;
import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.bean.DebugBean;
import com.fresh.app.bean.RiceBucketBean;
import com.fresh.app.bean.VideoBean;
import com.fresh.app.commonUtil.CardReaderManage;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentProgressBinding;
import com.fresh.app.handler.HandlerEvent;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.ui.CountDownView;
import com.fresh.app.view.IProgressView;
import com.fresh.app.viewmodel.ProgressingViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mr.miao on 2018/6/18.
 */

public class ProgressingFragment extends BaseFragment implements IProgressView, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {


    private FragmentProgressBinding bind;
    private HttpProxyCacheServer proxy;

    private String riceBucketId = "";
    private ProgressingViewModel progressingViewModel;
    private HandlerEvent handlerEvent;
    //是否检测碾米完成信号
    private boolean is_checked = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_progress, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        progressingViewModel = new ProgressingViewModel(this, bind);

//        //TODO 以下为测试代码
//        CardReaderManage.setCardReaderState(4);
        handlerEvent = new HandlerEvent(getActivity());
        DebugBean bean0_0 = new DebugBean("30", "买米模式", "", "");
//        handlerEvent.start(view, bean0_0);

        initCountDown();
    }

    /**
     * 倒计时启动
     */
    public void initCountDown() {
        CountDownView mTimerHour = bind.countdownTimerHour;
        mTimerHour.initTime(0,5,0);
        mTimerHour.start();
        mTimerHour.setOnTimeCompleteListener(() -> {
            EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR,"倒计时结束！"));
           UIUtils.showToast("倒计时结束");
        });
    }

    /**
     * 初始化视频
     */
    private void initVideo(String video_url) {
        proxy = CustomApplaction.getProxy(UIUtils.getContext());
        String proxyUrl = proxy.getProxyUrl(HttpUrl.getBaseUrl() + "/app/static/video/"+video_url);
        VideoView videoview = bind.video;
        videoview.setVideoPath(proxyUrl);
        videoview.requestFocus();
        videoview.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        if (!AppConstant.isDebug){
            CardReaderManage.setCardReaderState(0);
        }
    }




    @Subscribe
    public void receiveData(NetResponse netResponse) {
        switch (netResponse.getTag()){
            case HttpConstant.STATE_GET_VIDEO:
                List<VideoBean> videoBeans = GsonUtil.jsonToList((String) netResponse.getData(), VideoBean.class);
                initVideo(videoBeans.get(0).getVideoName());

                break;
            case HttpConstant.STATE_RICEBUCKET:
                List<RiceBucketBean> riceBucketBeans = GsonUtil.jsonToList((String) netResponse.getData(), RiceBucketBean.class);
                CardReaderManage.setCardReaderState(4);
                for (int i = 0; i < riceBucketBeans.size(); i++) {
                    RiceBucketBean item = riceBucketBeans.get(i);
                    if (item.getProductId().equals(AppConstant.PRODUCT_ITEM_BEAN.getProductId()) && item.getRiceBucketState() == 0) {
                        riceBucketId = item.getRiceBucketId();
                        return;
                    }
                }
                if (riceBucketId.equals("")) {
                    UIUtils.showToast("没有米了！");
                    return;
                }
                //TODO 提米电机启动
                UIUtils.showToast("提米电机启动");
                break;

            case HttpConstant.STATE_UPDATE_RICEBUCKET:
                //数据上传成功
                UIUtils.showToast("更新完成");
                break;


        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void queryRiceBucket(MessageEvent messageEvent) {
        if (messageEvent.getCode() == 10034) {
            //TODO 测试代码
            riceBucketId = "000d015c009a8b130e013416e09f";

            boolean equals = riceBucketId.equals(messageEvent.getMessage());
            LogUtils.e(equals + "");
            if (equals) {
                AppConstant.CARD_READER_STATE = 999;
                LogUtils.e("匹配！" + messageEvent.getMessage());

                //TODO 测试代码
                DebugBean bean0_0 = new DebugBean("33", "买米模式米桶到达", "", "");
                handlerEvent.start(getView(), bean0_0);


                //TODO
                UIUtils.showToast("买米模式米桶到达");
                //上传米桶编号
                progressingViewModel.updateRiceBucket(riceBucketId);
                //开始加工
                UIUtils.showToast("开始加工");

                //10秒 自动检测 是否加工完成
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        boolean zero = UIUtils.isZero();
                        Log.e("miao", "开始检测完成信号" + zero);
                        is_checked = true;
                    }
                };
                Timer timer = new Timer();
                timer.schedule(timerTask, 10000);

            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiceSocket(MessageEvent msg) {
        if (msg.getCode() == 10035) {
            //提米电机
            String substring00 = msg.getMessage().substring(0, 1);
            //碾米电机1 号
            String substring03 = msg.getMessage().substring(3, 4);
            //碾米电机2 号
            String substring04 = msg.getMessage().substring(4, 5);
            //碾米电机3 号
            String substring05 = msg.getMessage().substring(5, 6);
            //买米模式运行中
            String substring30 = msg.getMessage().substring(16, 17);
            //出米完成检测信号
            String substring40 = msg.getMessage().substring(24, 25);


            //接收到下位机的数据 进行数据解析
            if (substring00.equals("1")) {
                Log.e("miao111", "碾米电机已经启动了！");


            }

            if (substring30.equals("1")) {
                Log.e("miao111", "正在加工！");
            }
            if(is_checked){
                Log.e("miao111", (substring03+substring04+substring05).equals("000")+"");
                Log.e("miao111", substring03+substring04+substring05);

                if ((substring03+substring04+substring05).equals("000")) {
                    Log.e("miao111", "加工完成！");
                    //TODO 测试代码
                    DebugBean bean0_0 = new DebugBean("30", "买米模式米桶到达", "", "");
                    handlerEvent.stop(getView(), bean0_0);
                }
            }



        }
    }


}
