package com.fresh.app.viewmodel;

import com.fresh.app.databinding.FragmentProgressBinding;
import com.fresh.app.view.IProgressView;
import com.fresh.app.view.fragment.ProgressingFragment;

/**
 * Created by mr.miao on 2018/6/18.
 */

public class ProgressingViewModel {
    private IProgressView mProgressView;
    private FragmentProgressBinding mBind;

    public ProgressingViewModel(IProgressView progressView, FragmentProgressBinding bind) {
        this.mProgressView = progressView;
        this.mBind = bind;
    }










}
