package com.fresh.app.viewmodel;

import com.fresh.app.constant.AppConstant;
import com.fresh.app.databinding.FragmentProgressBinding;
import com.fresh.app.model.modelimpl.ProgressingModel;
import com.fresh.app.view.IProgressView;

/**
 * Created by mr.miao on 2018/6/18.
 */

public class ProgressingViewModel {
    private IProgressView mProgressView;
    private FragmentProgressBinding mBind;
    private final ProgressingModel progressingModel;

    public ProgressingViewModel(IProgressView progressView, FragmentProgressBinding bind) {
        this.mProgressView = progressView;
        this.mBind = bind;
        progressingModel = new ProgressingModel();
        initRiceBucket();
//        getVideo();
    }

    private void initRiceBucket() {
        progressingModel.getRiceBucket(AppConstant.DEVICE_ID);
    }


    public void updateRiceBucket(String riceBucketId) {
        progressingModel.updateRiceBucket(AppConstant.DEVICE_ID,riceBucketId);
    }

    public void  getVideo(){
        progressingModel.getVideoData(AppConstant.DEVICE_ID);
    }

}
