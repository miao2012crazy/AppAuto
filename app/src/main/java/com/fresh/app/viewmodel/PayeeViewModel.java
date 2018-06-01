package com.fresh.app.viewmodel;

import android.databinding.BaseObservable;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.base.BaseViewModel;
import com.fresh.app.bean.FreshOrderBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.ActivityPayeeBinding;
import com.fresh.app.model.modelimpl.PayeeModel;
import com.fresh.app.view.IPayeeView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 *
 * Created by mr.miao on 2018/5/10.
 */
public class PayeeViewModel implements BaseLoadListener<FreshOrderBean> {
    private final IPayeeView payeeView;
    private final ActivityPayeeBinding payeeBinding;
    private final PayeeModel payeeModel;

    public PayeeViewModel(IPayeeView payeeView, ActivityPayeeBinding payeeBinding) {
        this.payeeView=payeeView;
        this.payeeBinding=payeeBinding;
        payeeModel = new PayeeModel();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void toPay(MessageEvent msg){
        if (msg.getCode()==6666){
            UIUtils.showToast("zhi1111111111111fu");
        }
    }


    public void creatOrder(String productId) {
        payeeModel.creatOrder(productId,"20180515_01",this);
    }

    @Override
    public void loadSuccess(List<FreshOrderBean> list) {

    }

    @Override
    public void loadSuccess(FreshOrderBean freshOrderBean) {
        if (freshOrderBean.getOrderId()!=null){
            UIUtils.showToast("11111111");
        }
       payeeView.showDialogForPay(CustomApplaction.lastItem.getItemtype());
    }

    @Override
    public void loadFailure(String message) {

    }

    @Override
    public void loadStart() {

    }

    @Override
    public void loadComplete() {

    }
}
