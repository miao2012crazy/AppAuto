package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.bean.PressureBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.QrBean;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.IConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentDetailBinding;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.view.IDetailView;
import com.fresh.app.viewmodel.ProductDetailViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by mr.miao on 2018/6/15.
 */

public class ProductDetailFragment extends BaseFragment implements IDetailView {

    private FragmentDetailBinding bind;
    private ProductDetailViewModel productDetailViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        EventBus.getDefault().register(this);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productDetailViewModel = new ProductDetailViewModel(this, bind);
    }

    /**
     * 收到数据
     * @param netResponse
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void receiveData(NetResponse netResponse){
        if (netResponse.getTag().equals(HttpConstant.STATE_PRODUCT_DETAIL)){
        }else{
        }
        switch (netResponse.getTag()){
            case HttpConstant.STATE_PRODUCT_DETAIL:
                CustomApplaction.product_detail_bean = GsonUtil.GsonToBean((String) netResponse.getData(), ProductItemBean.class);
                break;
            case HttpConstant.STATE_PRODUCT_DETAIL_2:
                CustomApplaction.product_detail_bean= (ProductItemBean) netResponse.getData();
                break;
            case HttpConstant.STATE_CREAT_ORDER:
                CustomApplaction.QR_BEAN= GsonUtil.GsonToBean((String) netResponse.getData(),QrBean.class);
                EventBus.getDefault().post(new MessageEvent(10065, "3"));
                break;
            case HttpConstant.STATE_PRESSURE:
                PressureBean pressureBean = GsonUtil.GsonToBean((String) netResponse.getData(), PressureBean.class);
                IConstant.PRESS1=pressureBean.getPressure10();
                IConstant.PRESS2=pressureBean.getPressure2();
                IConstant.PRESS3=pressureBean.getPressure3();
                IConstant.PRESS1_CHA=pressureBean.getPressureCha1();
                IConstant.PRESS2_CHA=pressureBean.getPressureCha2();
                IConstant.PRESS3_CHA=pressureBean.getPressureCha3();
                productDetailViewModel.setProductId(AppConstant.product_id,pressureBean);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(MessageEvent messageEvent) {
        switch (messageEvent.getCode()) {
            case 1002:
//                Log.e("miao网络数据","请求网络数据");
                break;
            case 10066:
                String message = messageEvent.getMessage();
                //创建订单
                productDetailViewModel.creatFreshOrder(message,AppConstant.DEVICE_ID);
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
