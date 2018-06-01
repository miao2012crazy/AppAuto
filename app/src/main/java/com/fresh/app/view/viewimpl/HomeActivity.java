package com.fresh.app.view.viewimpl;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fresh.app.R;
import com.fresh.app.base.BaseActivity;
import com.fresh.app.databinding.ActivityHomeBinding;
import com.fresh.app.view.IHomeView;
import com.fresh.app.viewmodel.HomeViewModel;

/**
 * Created by mr.miao on 2018/5/31.
 */

public class HomeActivity extends BaseActivity implements IHomeView{

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        HomeViewModel homeViewModel = new HomeViewModel(this,binding);
        //初始化列表
        homeViewModel.initRecyclerList();
    }


}
