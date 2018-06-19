package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fresh.app.R;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.databinding.FragmentPayeeBinding;
import com.fresh.app.view.IPayeeView;
import com.fresh.app.viewmodel.PayeeViewModel;

/**
 * Created by mr.miao on 2018/6/17.
 */

public class PayeeFragment extends BaseFragment implements IPayeeView {

    private FragmentPayeeBinding bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_payee, container, false);
        return bind.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PayeeViewModel payeeViewModel = new PayeeViewModel(this, bind);
    }


    @Override
    public void showDialogForPay(int position) {

    }
}
