package com.fresh.app.viewmodel;

import android.graphics.Bitmap;
import android.view.View;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.bean.QRBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.commonUtil.ZXingUtils;
import com.fresh.app.databinding.FragmentRechargeBinding;
import com.fresh.app.listener.OnCreatOrderListener;
import com.fresh.app.model.modelimpl.RechargeModelImpl;
import com.fresh.app.view.IRechargeView;

/**
 * Created by mr.miao on 2018/6/26.
 */

public class RechargeViewModel implements OnCreatOrderListener {
    private final IRechargeView mRechargeView;
    private final FragmentRechargeBinding mBind;
    private final RechargeModelImpl rechargeModelImpl;

    public RechargeViewModel(IRechargeView rechargeView, FragmentRechargeBinding bind) {
        this.mRechargeView=rechargeView;
        this.mBind=bind;
        rechargeModelImpl = new RechargeModelImpl();
    }

    /**
     *
     * @param tel           电话号
     * @param memberId      会员卡id
     * @param moneyNum      充值金额
     */
    public void setRecharge(String tel,String memberId,String moneyNum) {
        rechargeModelImpl.setRecharge(memberId,tel,moneyNum,this);
    }

    @Override
    public void onCreatOrderSuccessed(QRBean qrBean) {
        //二维码生成成功   启动service 获取支付结果
        mRechargeView.getPayResult(qrBean.getOrder_id());
        Bitmap qrImage = ZXingUtils.createQRImage(qrBean.getWechat_url(), 400, 400);
        mBind.qrLayout.imageView2.setImageBitmap(qrImage);
        mBind.qrLayout.rlQrLayout.setVisibility(View.VISIBLE);
        mBind.recyclerList.llSelect.setVisibility(View.GONE);
        mBind.qrLayout.btnReturn.setOnClickListener(v -> {
            mBind.qrLayout.rlQrLayout.setVisibility(View.GONE);
            mBind.recyclerList.llSelect.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onCreatOrderFailed(String err_msg) {
        //二维码生成失败
        UIUtils.showToast("二维码生成失败"+err_msg);
    }
}
