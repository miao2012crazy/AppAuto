package com.fresh.app.viewmodel;

import com.fresh.app.bean.ReserOrderBean;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.ActivityMainBinding;
import com.fresh.app.listener.OnLoadReserveOrderListener;
import com.fresh.app.model.modelimpl.MainModelImpl;
import com.fresh.app.view.IMainView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by mr.miao on 2018/7/12.
 */

public class MainViewModel implements OnLoadReserveOrderListener {

    private IMainView mMainView;
    private ActivityMainBinding mActivityMainBinding;
    private final MainModelImpl mainModelImpl;

    public MainViewModel(ActivityMainBinding activityMainBinding, IMainView mainView) {
        this.mActivityMainBinding = activityMainBinding;
        this.mMainView = mainView;
        mainModelImpl = new MainModelImpl();
    }


    public void takeGoods(String code, String device_id) {
        mainModelImpl.takeGoods(code,device_id,this);
    }

    @Override
    public void onSuccessed(ReserOrderBean reserOrderBean) {
        //获取数据成功 选择产品精度》》开始加工
        AppConstant.product_id=reserOrderBean.getProductId();
        AppConstant.RESERORDERBEAN=reserOrderBean;
        EventBus.getDefault().post(new MessageEvent(10065,"2"));
    }

    @Override
    public void onFailed(String msg) {

    }
}
