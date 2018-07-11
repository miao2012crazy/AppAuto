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
import com.fresh.app.bean.PayeeBean;
import com.fresh.app.bean.QRBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.commonUtil.ZXingUtils;
import com.fresh.app.databinding.ActivityPayeeBinding;
import com.fresh.app.model.modelimpl.PayeeModelImpl;
import com.fresh.app.service.PayResultService;
import com.fresh.app.view.IPayeeView;
import com.fresh.app.view.PayeeActivity;

import java.util.ArrayList;
import java.util.List;

import static com.fresh.app.commonUtil.UIUtils.getResources;

/**
 * Created by mr.miao on 2018/7/4.
 */

public class PayeeVM {
    private ActivityPayeeBinding mActivityPayeeBinding;
    private IPayeeView IPayeeView;
    private RecyclerView recyclerList;
    private List<BindingAdapterItem> mainList;
    private List<PayeeBean> homeBeans;
    private BindingAdapter adapter;


    public PayeeVM(ActivityPayeeBinding activityPayeeBinding, IPayeeView payeeView) {
        this.mActivityPayeeBinding = activityPayeeBinding;
        this.IPayeeView = payeeView;
        PayeeModelImpl payeeModel = new PayeeModelImpl();
        initRecyclerList();
    }

    /**
     * 初始化列表
     */
    public void initRecyclerList() {
        mainList = new ArrayList<>();
        homeBeans = new ArrayList<>();
        recyclerList = mActivityPayeeBinding.recyclerList.recyclerList;
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
        initQRCode(CustomApplaction.QR_BEAN);
    }
    /**
     * 初始化二维码
     *
     */
    private void initQRCode(QRBean qrBean) {
//        payeeModelImpl.creatOrder(productId, "20180515_01", this);
        CustomApplaction.ORDER_ID = qrBean.getOrder_id();
        CustomApplaction.ISRESULT = true;
        UIUtils.getContext().startService(new Intent(UIUtils.getContext(), PayResultService.class));
        PayeeBean bindingAdapterItem = (PayeeBean) mainList.get(1);
        Bitmap qrImage = ZXingUtils.createQRImage(qrBean.getWechat_url(), 400, 400);
        bindingAdapterItem.setPay_image(qrImage);
    }

}
