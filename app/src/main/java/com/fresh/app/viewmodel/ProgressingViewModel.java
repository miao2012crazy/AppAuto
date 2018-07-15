package com.fresh.app.viewmodel;

import com.fresh.app.applaction.CustomApplaction;
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
    }

    private void initRiceBucket() {
        progressingModel.getRiceBucket("20180515_01");
    }


    public void updateRiceBucket(String riceBucketId) {
        progressingModel.updateRiceBucket("20180515_01",riceBucketId);
    }
}
