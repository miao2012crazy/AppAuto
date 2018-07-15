package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
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
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.databinding.FragmentProgressBinding;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.view.IProgressView;
import com.fresh.app.viewmodel.ProgressingViewModel;

import java.io.File;

/**
 * Created by mr.miao on 2018/6/18.
 */

public class ProgressingFragment extends BaseFragment implements IProgressView, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {


    private FragmentProgressBinding bind;
    private HttpProxyCacheServer proxy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_progress, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProgressingViewModel progressingViewModel = new ProgressingViewModel(this,bind);
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
        proxy.getProxyUrl(HttpUrl.getBaseUrl()+"video/111111.mp4");
        VideoView videoview = bind.video;
        videoview.setOnCompletionListener(this);
        videoview.setOnPreparedListener(this);
        videoview.setOnErrorListener(this);




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
}
