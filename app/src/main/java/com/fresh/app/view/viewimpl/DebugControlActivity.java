package com.fresh.app.view.viewimpl;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.fresh.app.R;
import com.fresh.app.base.BaseActivity;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.DebugBean2;
import com.fresh.app.databinding.ActivityDebugBinding;
import com.fresh.app.handlerevent.HandlerEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 调试控制
 * Created by mr.miao on 2018/5/2.
 */
public class DebugControlActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<BindingAdapterItem> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDebugBinding activityDebugBinding=DataBindingUtil.setContentView(this, R.layout.activity_debug);
        activityDebugBinding.setHandler(new HandlerEvent(this));
        recyclerView= activityDebugBinding.recycler.recyclerList;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        mList = new ArrayList<>();
        BindingAdapter bindingAdapter = new BindingAdapter();
        DebugBean2 bean211 = new DebugBean2("上仓门", "通电");
        DebugBean2 bean212 = new DebugBean2("下仓门", "断电");
        DebugBean2 bean221 = new DebugBean2("内仓门", "通电");
        DebugBean2 bean222 = new DebugBean2("出米照明灯", "通电");
        DebugBean2 bean231 = new DebugBean2("仓内照明灯1", "通电");
        DebugBean2 bean232 = new DebugBean2("仓内照明灯2", "断电");
        DebugBean2 bean23 = new DebugBean2("仓内照明灯3", "断电");

        mList.add(bean211);
        mList.add(bean212);
        mList.add(bean221);
        mList.add(bean222);
        mList.add(bean231);
        mList.add(bean232);
        mList.add(bean23);
        recyclerView.setAdapter(bindingAdapter);
        bindingAdapter.setItems(mList);
        Button btnFinish = activityDebugBinding.btnFinish;
        btnFinish.setText("返回");
        activityDebugBinding.btnOpen.setVisibility(View.GONE);

    }
}
