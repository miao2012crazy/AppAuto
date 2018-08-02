package com.fresh.app.viewmodel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.FreshOrderBean;
import com.fresh.app.bean.PayeeBean;
import com.fresh.app.bean.QrBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.commonUtil.ZXingUtils;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentPayeeBinding;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.model.modelimpl.PayeeModelImpl;
import com.fresh.app.service.PayResultService;
import com.fresh.app.view.IPayeeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.fresh.app.commonUtil.UIUtils.getResources;

/**
 * Created by mr.miao on 2018/5/10.
 */
public class PayeeViewModel {
    private final IPayeeView payeeView;
    private final FragmentPayeeBinding payeeBinding;
    private final PayeeModelImpl payeeModelImpl;
    private List<BindingAdapterItem> mainList;
    private List<PayeeBean> homeBeans;
    private RecyclerView recyclerList;
    private BindingAdapter adapter;


    public PayeeViewModel(IPayeeView payeeView, FragmentPayeeBinding payeeBinding) {
        EventBus.getDefault().register(this);
        this.payeeView = payeeView;
        this.payeeBinding = payeeBinding;
        payeeModelImpl = new PayeeModelImpl();
        initRecyclerList();
        initQRCode(CustomApplaction.QR_BEAN);
    }

    /**
     * 初始化二维码
     *
     */
    private void initQRCode(QrBean qrBean) {
        CustomApplaction.ORDER_ID = qrBean.getOrder_id();
        CustomApplaction.ISRESULT = true;
        CustomApplaction.RESULT_CODE=0;
        UIUtils.getContext().startService(new Intent(UIUtils.getContext(), PayResultService.class));
        PayeeBean bindingAdapterItem = (PayeeBean) mainList.get(1);
        Bitmap qrImage = ZXingUtils.createQRImage(qrBean.getWechat_url(), 400, 400);
        bindingAdapterItem.setPay_image(qrImage);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveData(NetResponse netResponse){
        switch (netResponse.getTag()){

            case HttpConstant.STATE_PRODUCT_PAY_STATE:
               FreshOrderBean freshOrderBean= (FreshOrderBean) netResponse.getData();
                if (freshOrderBean.getData().getOrderState()==1){
                    CustomApplaction.ISRESULT=false;
                    CustomApplaction.ORDER_ID="";
                    payeeView.showDialogForPay(0);
                }else{
                    LogUtils.e("待支付");
                }
                break;
            case HttpConstant.STATE_PRODUCT_PAY_CARD:
                //会员卡支付
                //支付成功
                payeeView.showDialogForPay(0);
                break;
        }
    }


    /**
     * 初始化列表
     */
    public void initRecyclerList() {
        mainList = new ArrayList<>();
        homeBeans = new ArrayList<>();
        recyclerList = payeeBinding.recyclerList.recyclerList;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UIUtils.getContext(), 3);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(adapter);
        PayeeBean payeeBean0 = new PayeeBean("云稻会员卡", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_huiyuan), true, 0);
        PayeeBean payeeBean1 = new PayeeBean("微信扫码支付", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_wechat), true, 1);
        PayeeBean payeeBean2 = new PayeeBean("支付宝扫码支付", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_alipay), true, 2);
        homeBeans.add(payeeBean0);
        homeBeans.add(payeeBean1);
        homeBeans.add(payeeBean2);
        mainList.addAll(homeBeans);
        adapter.setItems(mainList);
    }


    public void payForOrderUseCard(String card_id) {
        CustomApplaction.ISRESULT=false;
        payeeModelImpl.payForOrderUseCard(CustomApplaction.ORDER_ID,card_id);
    }

}
