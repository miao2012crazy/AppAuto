package com.fresh.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fresh.app.base.BaseActivity;

/**
 * Created by mr.miao on 2018/6/1.
 */

public class Main2Activity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this,R.layout.activity_main_2);
    }
}
