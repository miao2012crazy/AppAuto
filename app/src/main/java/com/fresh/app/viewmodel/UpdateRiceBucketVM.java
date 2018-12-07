package com.fresh.app.viewmodel;

import com.fresh.app.constant.AppConstant;
import com.fresh.app.databinding.ActivityUpdateRicebucketBinding;
import com.fresh.app.model.UpdateRiceBUcketModel;


/**
 * Created by mr.miao on 2018/8/20.
 */

public class UpdateRiceBucketVM {


    private final ActivityUpdateRicebucketBinding mBinding;


    public UpdateRiceBucketVM(ActivityUpdateRicebucketBinding binding) {
        this.mBinding=binding;
        UpdateRiceBUcketModel updateRiceBUcketModel = new UpdateRiceBUcketModel();
        updateRiceBUcketModel.getRiceBucket(AppConstant.DEVICE_ID);
    }



}
