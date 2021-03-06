package com.fresh.app.view.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.MoneyBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentRechargeBinding;
import com.fresh.app.handler.HandlerEvent;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.service.PayResultService;
import com.fresh.app.view.IRechargeView;
import com.fresh.app.viewmodel.RechargeViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/6/26.
 */

public class RechargeFragment extends BaseFragment implements IRechargeView {

    private FragmentRechargeBinding bind;
    private RecyclerView recyclerList;
    private List<BindingAdapterItem> mainList;
    private List<MoneyBean> moneyBeans;
    private BindingAdapter adapter;
    private RechargeViewModel rechargeViewModel;
    private AlertDialog dialog;
    private String tel="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_recharge, container, false);
        EventBus.getDefault().register(this);
        bind.setHandler(new HandlerEvent(getActivity()));
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppConstant.CARD_READER_STATE=2;
        rechargeViewModel = new RechargeViewModel(this, bind);
        initRecyclerList();
        //TODO 由于按钮的点击事件不触发 先放这里用用
        initBtnClick();
    }

    private void initBtnClick() {
        bind.recyclerList.btnReturn.setOnClickListener(v -> EventBus.getDefault().post(new MessageEvent(10065, "0")));
        bind.recyclerList.btnConfirm.setOnClickListener(v -> {
            String s = bind.recyclerList.etMoney.getText().toString();
            EventBus.getDefault().post(new MessageEvent(10098, s));
        });
    }


    /**
     * 初始化列表
     */
    public void initRecyclerList() {
        recyclerList = bind.recyclerList.moneyList.recyclerList;
        mainList = new ArrayList<>();
        moneyBeans = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UIUtils.getContext(), 2);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(adapter);
        MoneyBean moneyBean0 = new MoneyBean(0, String.valueOf(50), false);
        MoneyBean moneyBean1 = new MoneyBean(1, String.valueOf(100), false);
        MoneyBean moneyBean2 = new MoneyBean(2, String.valueOf(200), false);
        MoneyBean moneyBean3 = new MoneyBean(3, String.valueOf(500), false);
        moneyBeans.add(moneyBean0);
        moneyBeans.add(moneyBean1);
        moneyBeans.add(moneyBean2);
        moneyBeans.add(moneyBean3);
        mainList.addAll(moneyBeans);
        adapter.setItems(mainList);
    }


    @Subscribe
    public void onrecieve(MessageEvent messageEvent) {
        switch (messageEvent.getCode()) {
            case 10098:
                tel = bind.recyclerList.etTel.getText().toString();
                String memberId = CustomApplaction.MEMBER_ID;
                String money = messageEvent.getMessage();
                if (tel.equals("") && memberId.equals("")) {
                    EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR,"请刷卡或者输入绑定手机号！"));

                    return;
                }

                if (money.equals("")) {
                    EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR,"请输入或选择充值金额！"));

                    return;
                }
                rechargeViewModel.setRecharge(tel, memberId, money);
                break;
            case 10099:
                //设置金额
                bind.recyclerList.etMoney.setText(messageEvent.getMessage());
                break;
            case 10103:
                //读取到会员卡
                String money1 = messageEvent.getMessage();
                if (money1.equals("")) {
                    EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR,"请输入或选择充值金额！"));

                    return;
                }
                //TODO 未完成
//                rechargeViewModel.setRecharge(tel, memberId, money);
                break;
        }


    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }




    @Override
    public void getPayResult(String order_id) {
        CustomApplaction.RESULT_CODE = 1;
        CustomApplaction.ORDER_ID = order_id;
        CustomApplaction.ISRESULT = true;
        UIUtils.getContext().startService(new Intent(getActivity(), PayResultService.class));
    }
}
