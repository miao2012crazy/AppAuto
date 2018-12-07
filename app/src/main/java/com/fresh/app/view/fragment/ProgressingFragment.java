package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;
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
import com.fresh.app.constant.IConstant;
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

/**
 * Created by mr.miao on 2018/6/18.
 */

public class ProgressingFragment extends BaseFragment implements IProgressView, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {


    private FragmentProgressBinding bind;
    private HttpProxyCacheServer proxy;

    private String riceBucketId = "";
    private ProgressingViewModel progressingViewModel;
    private HandlerEvent handlerEvent;
    private boolean isShow = false;


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
        IConstant.ISCHECKED=true;
        IConstant.ISSHOW=true;
        progressingViewModel = new ProgressingViewModel(this, bind);
        handlerEvent = new HandlerEvent(getActivity());
        initCountDown();
    }

    /**
     * 倒计时启动
     */
    public void initCountDown() {
        CountDownView mTimerHour = bind.countdownTimerHour;
        mTimerHour.initTime(0, 5, 0);
        mTimerHour.start();
        mTimerHour.setOnTimeCompleteListener(() -> {
            //定时器结束 买米模式清除
            DebugBean bean0_1 = new DebugBean("33", "买米模式米桶到达", "", "");
            handlerEvent.stop(getView(), bean0_1);
            DebugBean bean0_0 = new DebugBean("30", "", "", "");
            handlerEvent.stop(getView(), bean0_0);
            handlerEvent.returnMain(getView());
        });
    }

    /**
     * 初始化视频
     */
    private void initVideo(String video_url) {
        proxy = CustomApplaction.getProxy(UIUtils.getContext());
        String proxyUrl = proxy.getProxyUrl(HttpUrl.getBaseUrl() + "/app/static/video/" + video_url);
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



    @Subscribe
    public void receiveData(NetResponse netResponse) {
        switch (netResponse.getTag()) {
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
                        DebugBean bean0_0 = new DebugBean("30", "买米模式启动", "", "");
                        handlerEvent.start(getView(), bean0_0);
                        EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR,"信号发送 买米模式启动"));
                        return;
                    }
                }
                if (riceBucketId.equals("")) {
                    UIUtils.showToast("没有米了！");
                    //TODO 退款  并回到主页面
                    return;
                }
                break;

            case HttpConstant.STATE_UPDATE_RICEBUCKET:
                //数据上传成功
                UIUtils.showToast("更新完成");
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void queryRiceBucket(MessageEvent messageEvent) {
        switch (messageEvent.getCode()) {
            case 10034:
                boolean equals = riceBucketId.equals(messageEvent.getMessage());
                LogUtils.e(equals + "");
                if (equals) {
                    AppConstant.CARD_READER_STATE = 999;
                    DebugBean bean0_0 = new DebugBean("33", "买米模式米桶到达", "", "");
                    handlerEvent.start(getView(), bean0_0);
                    //上传米桶编号
                    progressingViewModel.updateRiceBucket(riceBucketId);
                }
                break;
            case 10022:
                //流程结束 清楚买米模式
                DebugBean bean0_1 = new DebugBean("33", "买米模式米桶到达", "", "");
                handlerEvent.stop(getView(), bean0_1);
                DebugBean bean0_0 = new DebugBean("30", "", "", "");
                handlerEvent.stop(getView(), bean0_0);
                handlerEvent.returnMain();
                break;
            case 10035:
                if (!isShow) {
                    isShow = true;
                    EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR, "加工完成 请摆放好盛米器具，按下出米按钮！"));
                }
                break;
            case 10023:
                EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR, "休眠10s"));
                CustomApplaction.getExecutorService().execute(() -> {
                    try {
                        Thread.sleep(10000);//休眠10秒
                        EventBus.getDefault().post(new MessageEvent(10022, ""));
                    } catch (InterruptedException e) {
                        EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR, e.getMessage()));
                        e.printStackTrace();
                    }
                });
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        CardReaderManage.setCardReaderState(0);
    }
}
