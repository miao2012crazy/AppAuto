package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fresh.app.R;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.databinding.FragmentRechargeBinding;
import com.fresh.app.view.IRechargeView;
import com.fresh.app.viewmodel.RechargeViewModel;

/**
 * Created by mr.miao on 2018/6/26.
 */

public class RechargeFragment extends BaseFragment implements IRechargeView{

    private FragmentRechargeBinding bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_recharge, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RechargeViewModel rechargeViewModel = new RechargeViewModel(this,bind);


    }
}
