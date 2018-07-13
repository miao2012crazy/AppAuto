package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fresh.app.R;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.FragmentQueryBinding;
import com.fresh.app.handlerevent.HandlerEvent;
import com.fresh.app.view.IQueryView;
import com.fresh.app.viewmodel.QueryCardViewModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by mr.miao on 2018/6/1.
 */

public class QueryFragment extends BaseFragment implements IQueryView {

    private FragmentQueryBinding bind;
    private QueryCardViewModel queryCardViewModel;
    private LinearLayout ll_center;
    private RelativeLayout rl_attach;
    private RecyclerView recyclerView;
    private LinearLayout ll_list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_query, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        EventBus.getDefault().register(this);
        bind.setHandler(new HandlerEvent(getActivity()));
        ll_center = bind.layoutCenter.llCenter;
        rl_attach = bind.layoutAttach.rlAttach;
        recyclerView = bind.recyclerList.recyclerList;
        ll_list = bind.llList;
//  bind = DataBindingUtil.bind(view);
        queryCardViewModel = new QueryCardViewModel(this, bind);
//        EventBus.getDefault().post(new MessageEvent(1004,"000b01205e724ba5040008e4"));
        bind.layoutCenter.ivQueryHistory.setOnClickListener(v -> {
            setlayout(1);
        });
        bind.layoutCenter.ivRecharge.setOnClickListener(v -> {
            setlayout(2);
        });
        bind.layoutCenter.ivTel.setOnClickListener(v -> {
            setlayout(3);
        });
        bind.btnReturn.setOnClickListener(v -> {
            setlayout(0);
        });
        setlayout(0);
        queryCardViewModel.initHistory();
        bind.tvReturn.setOnClickListener(v -> {
            EventBus.getDefault().post(new MessageEvent(10065,"0"));
        });
    }


    @Override
    public void getCardDataSuccessed(QueryCardBean cardBean) {
//        bind.setItem(cardBean);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        EventBus.getDefault().unregister(this);
    }

    /**
     * 三元表达式剔除重复代码 switch结构
     *
     * @param position
     */
    @Override
    public void setlayout(int position) {
        bind.btnReturn.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
        ll_center.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        rl_attach.setVisibility(position == 3 ? View.VISIBLE : View.GONE);
        ll_list.setVisibility(position == 1 ? View.VISIBLE : View.GONE);
        if (position == 2) {
            EventBus.getDefault().post(new MessageEvent(10065, "5"));
        }
    }

    @Override
    public void startTimer() {
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                bind.layoutAttach.tvGetCode.setEnabled(false);
                bind.layoutAttach.tvGetCode.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                bind.layoutAttach.tvGetCode.setEnabled(true);
                bind.layoutAttach.tvGetCode.setText("重新获取");
            }
        }.start();

    }
}
