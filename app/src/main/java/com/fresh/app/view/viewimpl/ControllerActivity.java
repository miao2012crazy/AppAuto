package com.fresh.app.view.viewimpl;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.fresh.app.R;
import com.fresh.app.base.BaseActivity;
import com.fresh.app.databinding.ActivityControllerBinding;

/**
 * Created by mr.miao on 2018/8/20.
 */

public class ControllerActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityControllerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_controller);
        binding.btnStartDebug.setOnClickListener(v -> {
            startActivityBase(DebugActivity.class);
        });
        binding.startUpdateRiceBucket.setOnClickListener((v -> {
//            startActivityBase(UpdateRiceBucketActivity.class);
        }));
    }
}
