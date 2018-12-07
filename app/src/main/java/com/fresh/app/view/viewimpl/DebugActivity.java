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
import com.fresh.app.constant.IConstant;
import com.fresh.app.databinding.ActivityDebugBinding;
import com.fresh.app.handler.HandlerEvent;
import com.fresh.app.view.IDebugView;
import com.fresh.app.viewmodel.DebugViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 调试
 * Created by mr.miao on 2018/5/2.
 */

public class DebugActivity extends BaseActivity implements IDebugView {

    private ActivityDebugBinding activityDebugBinding;
    private HandlerEvent handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDebugBinding = DataBindingUtil.setContentView(this, R.layout.activity_debug);
        handler = new HandlerEvent(this);
        activityDebugBinding.setHandler(handler);
        DebugViewModel debugViewModel = new DebugViewModel(this, activityDebugBinding);
        RecyclerView recyclerList = activityDebugBinding.recycler.recyclerList;
        List<BindingAdapterItem> mList = new ArrayList<>();
        BindingAdapter bindingAdapter = new BindingAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(bindingAdapter);

        DebugBean bean0_0 = new DebugBean("00", "提米电机", "", "");
        DebugBean bean0_1 = new DebugBean("01", "提米加压气缸", "", "");
        DebugBean2 bean0_2 = new DebugBean2("02", "提米卸压气缸", "");
        DebugBean2 bean0_3 = new DebugBean2("03", "碾米电机1", "");
        DebugBean bean0_4 = new DebugBean("04", "碾米电机2", "", "");
        DebugBean bean0_5 = new DebugBean("05", "碾米电机3", "", "");
        DebugBean2 bean0_6 = new DebugBean2("06", "碾米加压电磁阀1", "");
        DebugBean2 bean0_7 = new DebugBean2("07", "碾米加压电磁阀2", "");
        DebugBean2 bean1_0 = new DebugBean2("10", "碾米加压电磁阀3", "");
        DebugBean2 bean1_1 = new DebugBean2("11", "碾米卸压电磁阀1", "");
        DebugBean2 bean1_2 = new DebugBean2("12", "碾米卸压电磁阀2", "");
        DebugBean2 bean1_3 = new DebugBean2("13", "碾米卸压电磁阀3", "");
        DebugBean2 bean1_4 = new DebugBean2("14", "出米卸压", "");
        DebugBean2 bean1_5 = new DebugBean2("15", "出米加压", "");
        DebugBean bean1_6 = new DebugBean("16", "吸糠风机启动", "", "");
        DebugBean2 bean1_7 = new DebugBean2("17", "备用", "");
        DebugBean2 bean2_0 = new DebugBean2("20", "内舱门1", "");
        DebugBean2 bean2_1 = new DebugBean2("21", "内舱门2", "");
        DebugBean2 bean2_2 = new DebugBean2("22", "外舱门1", "");
        DebugBean2 bean2_3 = new DebugBean2("23", "出米照明灯启动", "");
        DebugBean2 bean2_4 = new DebugBean2("24", "舱内照明灯1启动", "");
        DebugBean2 bean2_5 = new DebugBean2("25", "舱内照明灯2启动", "");
        DebugBean2 bean2_6 = new DebugBean2("26", "出米按钮", "");
        DebugBean2 bean2_7 = new DebugBean2("27", "装米模式运行中", "");
        DebugBean2 bean3_0 = new DebugBean2("30", "买米模式运行中", "");
        DebugBean2 bean3_1 = new DebugBean2("31", "故障模式", "");
        DebugBean2 bean3_2 = new DebugBean2("32", "装米模式空米桶到达", "");
        DebugBean2 bean3_3 = new DebugBean2("33", "买米模式米桶到达", "");
        DebugBean2 bean3_4 = new DebugBean2("34", "手动模式运行中", "");
        DebugBean2 bean3_5 = new DebugBean2("35", "网络运行中", "");
        DebugBean2 bean3_6 = new DebugBean2("36", "外舱门2号", "");
        DebugBean2 bean3_7 = new DebugBean2("37", "备用", "");
        DebugBean2 bean4_0 = new DebugBean2("40", "碾米完成检测信号", "");
        DebugBean2 bean4_1 = new DebugBean2("41", "上加米模式", "");
        DebugBean2 bean4_2 = new DebugBean2("42", "下加米模式", "");
        DebugBean2 bean4_3 = new DebugBean2("43", "上舱门关闭-行程开关", "");
        DebugBean2 bean4_4 = new DebugBean2("44", "下舱门关闭-行程开关", "");
        DebugBean2 bean4_5 = new DebugBean2("45", "内舱门关闭-行程开关", "");
        DebugBean2 bean4_6 = new DebugBean2("46", "碾米电机1运行超时", "");
        DebugBean2 bean4_7 = new DebugBean2("47", "碾米电机2运行超时", "");
        DebugBean2 bean5_0 = new DebugBean2("50", "碾米电机3运行超时", "");
        DebugBean2 bean5_1 = new DebugBean2("51", "备用", "");
        DebugBean2 bean5_2 = new DebugBean2("52", "备用", "");
        DebugBean2 bean5_3 = new DebugBean2("53", "备用", "");
        DebugBean2 bean5_4 = new DebugBean2("54", "备用", "");
        DebugBean2 bean5_5 = new DebugBean2("55", "备用", "");
        DebugBean2 bean5_6 = new DebugBean2("56", "备用", "");
        DebugBean2 bean5_7 = new DebugBean2("57", "备用", "");
        mList.add(bean3_4);
        mList.add(bean0_0);
        mList.add(bean0_1);
        mList.add(bean0_2);
        mList.add(bean0_3);
        mList.add(bean0_4);
        mList.add(bean0_5);
        mList.add(bean0_6);
        mList.add(bean0_7);
        mList.add(bean1_0);
        mList.add(bean1_1);
        mList.add(bean1_2);
        mList.add(bean1_3);
        mList.add(bean1_4);
        mList.add(bean1_5);
        mList.add(bean1_6);
        mList.add(bean1_7);
        mList.add(bean2_0);
        mList.add(bean2_1);
        mList.add(bean2_2);
        mList.add(bean2_3);
        mList.add(bean2_4);
        mList.add(bean2_5);
        mList.add(bean2_6);
        mList.add(bean2_7);
        mList.add(bean3_0);
        mList.add(bean3_1);
        mList.add(bean3_2);
        mList.add(bean3_3);
        mList.add(bean3_5);
        mList.add(bean3_6);
        mList.add(bean3_7);
        mList.add(bean4_0);
        mList.add(bean4_1);
        mList.add(bean4_2);
        mList.add(bean4_3);
        mList.add(bean4_4);
        mList.add(bean4_5);
        mList.add(bean4_6);
        mList.add(bean4_7);
        mList.add(bean5_0);
        mList.add(bean5_1);
        mList.add(bean5_2);
        mList.add(bean5_3);
        mList.add(bean5_4);
        mList.add(bean5_5);
        mList.add(bean5_6);
        mList.add(bean5_7);

        bindingAdapter.setItems(mList);
        DebugBean bean02 = new DebugBean("34", "", "", "");
        handler.start(null,bean02);
        initBtnPress();
    }

    /**
     * 初始化压力
     */
    private void initBtnPress() {
        activityDebugBinding.btnPress.setOnClickListener(v -> {
            String press1 = activityDebugBinding.etPress1.getText().toString();
            String press2 = activityDebugBinding.etPress2.getText().toString();
            String press3 = activityDebugBinding.etPress3.getText().toString();
            IConstant.PRESS1=press1;
            IConstant.PRESS2=press2;
            IConstant.PRESS3=press3;
            DebugBean2 bean5_7 = new DebugBean2("57", "备用", "");
            handler.start(new View(DebugActivity.this),bean5_7);
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DebugBean bean02 = new DebugBean("34", "", "", "");
        HandlerEvent handlerEvent = new HandlerEvent(this);
        handlerEvent.stop(new View(this),bean02);

        IConstant.PRESS1="";
        IConstant.PRESS2="";
        IConstant.PRESS3="";
    }
}
