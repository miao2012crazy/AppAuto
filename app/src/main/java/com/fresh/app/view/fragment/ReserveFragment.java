package com.fresh.app.view.fragment;

import android.app.AlertDialog;
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
import com.fresh.app.bean.QrBean;
import com.fresh.app.bean.ReserveResultBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.commonUtil.ZXingUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentReserveBinding;
import com.fresh.app.databinding.LayoutInputReserveBinding;
import com.fresh.app.databinding.LayoutLoginReserveBinding;
import com.fresh.app.databinding.LayoutReserveSuccessedBinding;
import com.fresh.app.handler.HandlerEvent;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.service.PayResultService;
import com.fresh.app.view.IReserveView;
import com.fresh.app.viewmodel.PayeeVM;
import com.fresh.app.viewmodel.ReserveViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;


/**
 * Created by mr.miao on 2018/6/28.
 */

public class ReserveFragment extends BaseFragment implements IReserveView {

    private FragmentReserveBinding bind;
    private AlertDialog dialog;
    private int num = 1;
    private ReserveViewModel reserveViewModel;
    private PayeeVM payeeVM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_reserve, container, false);
        EventBus.getDefault().register(this);
        bind.setHandler(new HandlerEvent(getActivity()));
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reserveViewModel = new ReserveViewModel(bind, this);
        payeeVM = new PayeeVM(bind,this);
        payeeVM.initRecyclerList();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receice(MessageEvent messageEvent) {
        if (messageEvent.getCode() == 10097) {
            showDialog(messageEvent.getMessage());
//            showDialogForWechatOrAli();
        }
    }

    @Subscribe
    public void receiveData(NetResponse netResponse){
        switch (netResponse.getTag()){
            case HttpConstant.STATE_RESERVE_RESIULT:
                ReserveResultBean reserveResultBean = (ReserveResultBean) netResponse.getData();
                if (reserveResultBean.getData().getOrderState()==1){
                    CustomApplaction.ISRESULT=false;
                    CustomApplaction.ORDER_ID="";
                    //支付成功
                    showDialogForPay(0);
                }
                break;
        }
    }



    /**
     * 选择数量和填写手机号
     *
     * @param product_id 商品id
     */
    private void showDialog(String product_id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInputReserveBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.layout_input_reserve, null, false);
        builder.setView(binding.getRoot());
        dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        binding.btnConfirm.setOnClickListener(v -> {
            String user_tel = binding.etTel.getText().toString();
            //点击了确定 提交数据
            reserveViewModel.creatReserveOrder(product_id,user_tel,num);
        });
        binding.avNum.setOnChangeListener(value -> num = value);
        binding.btnCancel.setOnClickListener(v -> dialog.dismiss());
    }

    /**
     * 用户登陆身份识别 wechat/ali
     */
    private void showDialogForWechatOrAli(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutLoginReserveBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.layout_login_reserve, null, false);
        builder.setView(binding.getRoot());
        dialog = builder.create();
        dialog.show();
        String APPID="wx87d3cc6a3943c5a9";
        String REDIRECT_URI= HttpUrl.getBaseUrl()+HttpUrl.REDIRECT_URI;
        try {
            String encode = URLEncoder.encode(REDIRECT_URI, "UTF-8");
            binding.ivWechat.setImageBitmap(ZXingUtils.createQRImage("https://open.weixin.qq.com/connect/qrconnect?appid="+APPID+"&redirect_uri="+encode+"&response_type=code&scope=snsapi_login&state=123123#wechat_redirect",400,400));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void initPayee(QrBean qrBean) {
        dialog.dismiss();
//        startActivityBase(getActivity(),PayeeActivity.class);
        bind.rlPayee.setVisibility(View.VISIBLE);
        bind.rlReserve.setVisibility(View.GONE);
        payeeVM.initQRCode(qrBean);
    }


    @Override
    public void getPayResult(String order_id) {
        CustomApplaction.RESULT_CODE=2;
        CustomApplaction.ORDER_ID=order_id;
        CustomApplaction.ISRESULT=true;
        getActivity().startService(new Intent(getActivity(), PayResultService.class));
    }


    @Override
    public void showDialogForPay(int last_position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutReserveSuccessedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.layout_reserve_successed, null, false);
        builder.setView(binding.getRoot());
        dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(UIUtils.dip2px(600), LinearLayout.LayoutParams.WRAP_CONTENT);
        if (last_position == 0) {
//            startProgress();
        }
        startProgress();
    }

    public void startProgress(){
        new Handler().postDelayed(() -> {
            if (dialog!=null){
                dialog.dismiss();
                EventBus.getDefault().post(new MessageEvent(10065, "0"));
            }
        }, 3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
