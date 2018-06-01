package com.fresh.app.view.viewimpl;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fresh.app.R;
import com.fresh.app.base.BaseActivity;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.DebugBean;
import com.fresh.app.bean.DebugBean2;
import com.fresh.app.databinding.ActivityDebugBinding;
import com.fresh.app.handlerevent.HandlerEvent;
import com.fresh.app.view.IDebugView;
import com.fresh.app.viewmodel.DebugViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 调试
 * Created by mr.miao on 2018/5/2.
 */

public class DebugActivity extends BaseActivity implements IDebugView{

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDebugBinding activityDebugBinding = DataBindingUtil.setContentView(this, R.layout.activity_debug);
        activityDebugBinding.setHandler(new HandlerEvent(this));
        DebugViewModel debugViewModel = new DebugViewModel(this, activityDebugBinding);
        RecyclerView recyclerList = activityDebugBinding.recycler.recyclerList;
        List<BindingAdapterItem> mList = new ArrayList<>();
        BindingAdapter bindingAdapter = new BindingAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(bindingAdapter);

        bindingAdapter.setItems(mList);

        DebugBean debugBean1 = new DebugBean("碾米电机1号", "700r/min", "9.8kpa");
        DebugBean debugBean2 = new DebugBean("碾米电机2号", "800r/min", "19.8kpa");
        DebugBean debugBean3 = new DebugBean("碾米电机3号", "800r/min", "19.8kpa");
        DebugBean debugBean4 = new DebugBean("提米电机号", "500r/min", "19.8kpa");
        DebugBean2 bean211 = new DebugBean2("1号加压", "通电");
        DebugBean2 bean212 = new DebugBean2("1号卸压", "断电");
        DebugBean2 bean221 = new DebugBean2("2号加压", "通电");
        DebugBean2 bean222 = new DebugBean2("2号卸压", "断电");
        DebugBean2 bean231 = new DebugBean2("3号加压", "通电");
        DebugBean2 bean232 = new DebugBean2("3号卸压", "断电");
        DebugBean2 bean23 = new DebugBean2("漏斗升降气缸", "伸出");
        mList.add(debugBean1);
        mList.add(debugBean2);
        mList.add(debugBean3);
        mList.add(debugBean4);
        mList.add(bean211);
        mList.add(bean212);
        mList.add(bean221);
        mList.add(bean222);
        mList.add(bean231);
        mList.add(bean232);
        mList.add(bean23);

    }


    public  class Presenter{
        public void finishDebug(View view){
            finish();
        }

        public void startDebugController(View view){
            startActivityBase(DebugControlActivity.class);
        }

    }




}
