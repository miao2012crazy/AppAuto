package com.fresh.app.viewmodel;

import android.graphics.Bitmap;
import android.view.View;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.bean.QrBean;
import com.fresh.app.bean.RechargeResultBean;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.ZXingUtils;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentRechargeBinding;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.model.modelimpl.RechargeModelImpl;
import com.fresh.app.view.IRechargeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mr.miao on 2018/6/26.
 */

public class RechargeViewModel {
    private final IRechargeView mRechargeView;
    private final FragmentRechargeBinding mBind;
    private final RechargeModelImpl rechargeModelImpl;

    public RechargeViewModel(IRechargeView rechargeView, FragmentRechargeBinding bind) {
        EventBus.getDefault().register(this);
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
        rechargeModelImpl.setRecharge(memberId,tel,moneyNum);
    }

    private void initQR(QrBean qrBean) {
        Bitmap qrImage = ZXingUtils.createQRImage(qrBean.getWechat_url(), 400, 400);
        Bitmap qrImage1 = ZXingUtils.createQRImage(qrBean.getAlipay_url(), 400, 400);
        mBind.qrLayout.imageView2.setImageBitmap(qrImage);
        mBind.qrLayout.imageView3.setImageBitmap(qrImage1);
        mBind.qrLayout.rlQrLayout.setVisibility(View.VISIBLE);
        mBind.recyclerList.llSelect.setVisibility(View.GONE);
        mBind.qrLayout.btnReturn.setOnClickListener(v -> {
            mBind.qrLayout.rlQrLayout.setVisibility(View.GONE);
            mBind.recyclerList.llSelect.setVisibility(View.VISIBLE);
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void  receiveData(NetResponse netResponse){
        switch (netResponse.getTag()){
            case HttpConstant.STATE_RECHARGE_CREAT:
                QrBean qrBean = GsonUtil.GsonToBean((String) netResponse.getData(), QrBean.class);
                mRechargeView.getPayResult(qrBean.getOrder_id());
                initQR(qrBean);
                break;
            case HttpConstant.STATE_RECHARGE_PAY_RESULT:
                RechargeResultBean rechargeResultBean =(RechargeResultBean) netResponse.getData();
                if (rechargeResultBean.getData().getCardState()==1){
                    //充值成功
                    mBind.llPaySuccess.setVisibility(View.VISIBLE);
                    CustomApplaction.ISRESULT=false;
                    CustomApplaction.ORDER_ID="";
                }
                break;
        }
    }

}
