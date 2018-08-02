package com.fresh.app.viewmodel;

import com.fresh.app.bean.ReserOrderBean;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.ActivityMainBinding;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.model.modelimpl.MainModelImpl;
import com.fresh.app.view.IMainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mr.miao on 2018/7/12.
 */

public class MainViewModel  {

    private IMainView mMainView;
    private ActivityMainBinding mActivityMainBinding;
    private final MainModelImpl mainModelImpl;

    public MainViewModel(ActivityMainBinding activityMainBinding, IMainView mainView) {
        this.mActivityMainBinding = activityMainBinding;
        this.mMainView = mainView;
        mainModelImpl = new MainModelImpl();
    }


    public void takeGoods(String code, String device_id) {
        mainModelImpl.takeGoods(code,device_id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveData(NetResponse netResponse){
        switch (netResponse.getTag()){
            case HttpConstant.STATE_TAKEGOODS:
                ReserOrderBean reserOrderBean = GsonUtil.GsonToBean((String) netResponse.getData(), ReserOrderBean.class);
                AppConstant.product_id=reserOrderBean.getProductId();
                AppConstant.RESERORDERBEAN=reserOrderBean;
                EventBus.getDefault().post(new MessageEvent(10065,"2"));


                break;

        }




    }
}
