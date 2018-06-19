package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fresh.app.R;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.databinding.FragmentProgressBinding;
import com.fresh.app.view.IProgressView;
import com.fresh.app.viewmodel.ProgressingViewModel;

/**
 * Created by mr.miao on 2018/6/18.
 */

public class ProgressingFragment extends BaseFragment implements IProgressView {


    private FragmentProgressBinding bind;

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
    }
}
