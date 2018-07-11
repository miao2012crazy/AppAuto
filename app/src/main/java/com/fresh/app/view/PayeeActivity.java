package com.fresh.app.view;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.LinearLayout;

import com.fresh.app.R;
import com.fresh.app.base.BaseActivity;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.databinding.ActivityPayeeBinding;
import com.fresh.app.databinding.LayoutPaySuccessedBinding;
import com.fresh.app.handlerevent.HandlerEvent;
import com.fresh.app.viewmodel.PayeeVM;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by mr.miao on 2018/7/4.
 */

public class PayeeActivity extends BaseActivity implements IPayeeView{

    private AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
        ActivityPayeeBinding activityPayeeBinding = DataBindingUtil.setContentView(this, R.layout.activity_payee);
        PayeeVM payeeVM = new PayeeVM(activityPayeeBinding,this);
        activityPayeeBinding.setHandler(new HandlerEvent(this));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showDialogForPay(int last_position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutPaySuccessedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_pay_successed, null, false);
        builder.setView(binding.getRoot());
        dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(UIUtils.dip2px(600), LinearLayout.LayoutParams.WRAP_CONTENT);
        if (last_position==0){
//            startProgress();

        }
    }
}
