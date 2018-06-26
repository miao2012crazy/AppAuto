package com.fresh.app.view.fragment;

import android.app.AlertDialog;
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
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.FragmentPayeeBinding;
import com.fresh.app.databinding.LayoutPaySuccessedBinding;
import com.fresh.app.view.IPayeeView;
import com.fresh.app.viewmodel.PayeeViewModel;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by mr.miao on 2018/6/17.
 */
public class PayeeFragment extends BaseFragment implements IPayeeView {

    private FragmentPayeeBinding bind;
    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_payee, container, false);
        return bind.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PayeeViewModel payeeViewModel = new PayeeViewModel(this, bind);
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
        if (last_position==0){
            startProgress();
        }
    }

    public void startProgress(){
        new Handler().postDelayed(() -> {
            if (dialog!=null){
                dialog.dismiss();
                EventBus.getDefault().post(new MessageEvent(10065, "4"));
            }
        }, 3000);
    }


    @Override
    public void onPause() {
        super.onPause();
        CustomApplaction.ISRESULT=false;
    }
}
