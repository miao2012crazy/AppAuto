package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;
import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.bean.RiceBucketBean;
import com.fresh.app.commonUtil.CardReaderManage;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.FragmentProgressBinding;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.view.IProgressView;
import com.fresh.app.viewmodel.ProgressingViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

/**
 * Created by mr.miao on 2018/6/18.
 */

public class ProgressingFragment extends BaseFragment implements IProgressView, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {


    private FragmentProgressBinding bind;
    private HttpProxyCacheServer proxy;
    private List<RiceBucketBean.DataBean> riceBucketBeanData;

    private String riceBucketId = "";
    private ProgressingViewModel progressingViewModel;

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
        initVideo();

    }


    private void initVideo() {
        proxy = CustomApplaction.getProxy(UIUtils.getContext());
//        String innerSDCardPath = UIUtils.getInnerSDCardPath();
//        String strFile = innerSDCardPath + "/video/";
//        boolean b = UIUtils.fileIsExists(strFile);
//        File file = new File(innerSDCardPath + "/video/");
//        File[] files = file.listFiles();
//        try {
//            for (int i=0;i<files.length;i++){
//                String fileSuffix = FileUtils.getFileSuffix(files[i].getName());
//                if (fileSuffix.equals("download")){
//                    boolean b1 = FileUtils.deleteFile(files[i]);
//                    Log.e("miao111",b1+"");
//                }
//                Log.e("miao111",files[i].getName());
//            }
//
//
//            long fileSizes = FileUtils.getFileSizes(file);
//            String s = FileUtils.FormetFileSize(fileSizes);
//            Log.e("miao111", s);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (!b) {
//            File file1 = new File(innerSDCardPath + "/video/");
//            if (!file.exists()) {
//                boolean mkdir = file1.mkdir();
//            }
//        }
        String proxyUrl = proxy.getProxyUrl(HttpUrl.getBaseUrl() + "app/static/1.mp4");
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
        CardReaderManage.setCardReaderState(0);
    }

    @Subscribe
    public void receive(RiceBucketBean riceBucketBean) {
        LogUtils.e("接收到数据+" + riceBucketBean);
        riceBucketBeanData = riceBucketBean.getData();
        CardReaderManage.setCardReaderState(4);
        for (int i = 0; i < riceBucketBeanData.size(); i++) {
            RiceBucketBean.DataBean item = riceBucketBeanData.get(i);
            if (item.getProductId().equals(AppConstant.product_id) && item.getRiceBucketState() == 0) {
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


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void queryRiceBucket(MessageEvent messageEvent) {
        if (messageEvent.getCode() == 10034) {
            boolean equals = riceBucketId.equals(messageEvent.getMessage());
            LogUtils.e(equals+"");
            if (equals) {
                AppConstant.CARD_READER_STATE = 999;
                LogUtils.e("匹配！"+messageEvent.getMessage());
                //TODO
                UIUtils.showToast("提米电机停止");
                //上传米桶编号
                progressingViewModel.updateRiceBucket(riceBucketId);
                //开始加工
                UIUtils.showToast("开始加工");
            }
        }
    }


}
