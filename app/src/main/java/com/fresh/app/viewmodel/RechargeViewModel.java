package com.fresh.app.viewmodel;

import com.fresh.app.databinding.FragmentRechargeBinding;
import com.fresh.app.model.modelimpl.RechargeModelImpl;
import com.fresh.app.view.IRechargeView;

/**
 * Created by mr.miao on 2018/6/26.
 */

public class RechargeViewModel {
    private final IRechargeView mRechargeView;
    private final FragmentRechargeBinding mBind;
    private final RechargeModelImpl rechargeModelImpl;

    public RechargeViewModel(IRechargeView rechargeView, FragmentRechargeBinding bind) {
        this.mRechargeView=rechargeView;
        this.mBind=bind;
        rechargeModelImpl = new RechargeModelImpl();
    }
}
