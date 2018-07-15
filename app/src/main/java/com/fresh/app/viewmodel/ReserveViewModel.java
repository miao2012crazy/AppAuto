package com.fresh.app.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.QRBean;
import com.fresh.app.bean.ReserveBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.databinding.FragmentReserveBinding;
import com.fresh.app.listener.OnAllProductListener;
import com.fresh.app.listener.OnCreatReserveListener;
import com.fresh.app.model.modelimpl.ReserveModelImpl;
import com.fresh.app.view.IReserveView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/6/28.
 */

public class ReserveViewModel implements OnAllProductListener, OnCreatReserveListener {
    private final FragmentReserveBinding mBind;
    private final IReserveView mReserveView;
    private List<BindingAdapterItem> mainList;
    private RecyclerView recyclerList;
    private BindingAdapter adapter;
    private final ReserveModelImpl reserveModelImpl;

    public ReserveViewModel(FragmentReserveBinding bind, IReserveView iReserveView) {
        this.mBind = bind;
        this.mReserveView = iReserveView;
        reserveModelImpl = new ReserveModelImpl();
        reserveModelImpl.getAllProductInfo("", this);
        recyclerList = bind.recyclerList.recyclerList;
    }

    private void initList(ReserveBean reserveBean) {
        mainList = new ArrayList<>();
        LinearLayoutManager ms= new LinearLayoutManager(UIUtils.getContext());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(ms);
        recyclerList.setAdapter(adapter);
        mainList.addAll(reserveBean.getData());
        adapter.setItems(mainList);
    }

    @Override
    public void onSuccessed(ReserveBean reserveBean) {
        initList(reserveBean);
    }

    @Override
    public void onFailed(String err_msg) {

    }

    public void creatReserveOrder(String product_id, String user_tel, int num) {
        reserveModelImpl.creatReserve(product_id,user_tel,num+"",this);
    }

    @Override
    public void onCreatSuccessed(QRBean qrBean) {
        //已获取到二维码  打开支付页 准备支付
        UIUtils.showToast("微信支付"+qrBean.getWechat_url());
        mReserveView.initPayee(qrBean);
    }


    @Override
    public void onCreatFailed(String err_msg) {

    }
}
