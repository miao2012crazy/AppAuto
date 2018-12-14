package com.fresh.app.viewmodel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.FreshOrderBean;
import com.fresh.app.bean.PayeeBean;
import com.fresh.app.bean.UserLoginMsgBean;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.StringUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.commonUtil.ZXingUtils;
import com.fresh.app.constant.AppConstant;
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
        initQRCode();
    }


    /**
     * 初始化二维码
     *
     */
    private void initQRCode() {

        String uuid = StringUtils.getUUID();
        AppConstant.NONCE_STR=uuid;
        UserLoginMsgBean.DataBean userLoginMsgBean = new UserLoginMsgBean.DataBean(AppConstant.DEVICE_ID, uuid);
        String s = GsonUtil.GsonString(userLoginMsgBean);
        LogUtils.e(s);
        CustomApplaction.ISRESULT = true;
        CustomApplaction.RESULT_CODE=3;
        UIUtils.getContext().startService(new Intent(UIUtils.getContext(), PayResultService.class));
        PayeeBean bindingAdapterItem = (PayeeBean) mainList.get(1);
        Bitmap qrImage = ZXingUtils.createQRImage(s, 500, 400);
        bindingAdapterItem.setPay_image(qrImage);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveData(NetResponse netResponse){
        switch (netResponse.getTag()){
            case HttpConstant.STATE_LOGIN_RESIULT:
                UIUtils.getContext().stopService(new Intent(UIUtils.getContext(), PayResultService.class));
                UserLoginMsgBean userLoginMsgBean= (UserLoginMsgBean) netResponse.getData();
                LogUtils.e(userLoginMsgBean.getData().toString());
                payeeView.showDialogForPay(1);
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
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(adapter);
        PayeeBean payeeBean0 = new PayeeBean("云稻会员卡", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_huiyuan), true, 0);
        PayeeBean payeeBean2 = new PayeeBean("华鲜汇App扫码", BitmapFactory.decodeResource(getResources(), R.drawable.ic_wechat_gz), true, 2);
        homeBeans.add(payeeBean0);
        homeBeans.add(payeeBean2);
        mainList.addAll(homeBeans);
        adapter.setItems(mainList);
    }


    public void payForOrderUseCard(String card_id) {
        CustomApplaction.ISRESULT=false;
        payeeModelImpl.payForOrderUseCard(CustomApplaction.ORDER_ID,card_id);
    }

}
