package com.fresh.app.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.commonUtil.CardReaderManage;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentPayeeBinding;
import com.fresh.app.databinding.LayoutPaySuccessedBinding;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.service.PayResultService;
import com.fresh.app.view.IPayeeView;
import com.fresh.app.viewmodel.PayeeViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mr.miao on 2018/6/17.
 */
public class PayeeFragment extends BaseFragment implements IPayeeView {

    private FragmentPayeeBinding bind;
    private AlertDialog dialog;
    private PayeeViewModel payeeViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_payee, container, false);
        return bind.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
        payeeViewModel = new PayeeViewModel(this, bind);
        if (!AppConstant.isDebug) {
            CardReaderManage.setCardReaderState(1);
        }
    }


    @Override
    public void showDialogForPay(int last_position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutPaySuccessedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.layout_pay_successed, null, false);
        builder.setView(binding.getRoot());
        dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(UIUtils.dip2px(600), LinearLayout.LayoutParams.WRAP_CONTENT);
        if (last_position == 0) {
            startProgress();
        }
        dialog.setOnDismissListener(dialog -> {
            //接收到舱门关闭 获得了 重量




        });

    }

    public void startProgress() {
        new Handler().postDelayed(() -> {
            if (dialog != null) {
                dialog.dismiss();

                EventBus.getDefault().post(new MessageEvent(10065, "4"));
            }
        }, 3000);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receiveCardId(MessageEvent messageEvent) {
        if (messageEvent.getCode() == 10033) {
            //检测到会员卡
            //会员卡扣款
            payeeViewModel.payForOrderUseCard(messageEvent.getMessage());
        }
    }



    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receiveCardId(NetResponse netResponse) {
        switch (netResponse.getTag()){
            case HttpConstant.PROGRESS_DIALOG:
                break;


        }
    }





    @Override
    public void onPause() {
        super.onPause();
        CustomApplaction.ISRESULT = false;
        if (!AppConstant.isDebug) {
            CardReaderManage.setCardReaderState(0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }


    @Override
    public void onResume() {
        super.onResume();
    }
}
