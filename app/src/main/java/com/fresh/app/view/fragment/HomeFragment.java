package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fresh.app.R;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.databinding.FragmentHomeBinding;
import com.fresh.app.view.IHomeView;
import com.fresh.app.viewmodel.HomeViewModel;

/**
 * Created by mr.miao on 2018/6/15.
 */

public class HomeFragment extends BaseFragment implements IHomeView{

    private FragmentHomeBinding bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        EventBus.getDefault().register(this);
        HomeViewModel homeViewModel = new HomeViewModel(this,bind);
    }
}
