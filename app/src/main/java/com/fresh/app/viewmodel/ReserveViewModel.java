package com.fresh.app.viewmodel;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.QrBean;
import com.fresh.app.bean.ReserveItemBean;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentReserveBinding;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.model.modelimpl.ReserveModelImpl;
import com.fresh.app.view.IReserveView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/6/28.
 */

public class ReserveViewModel {
    private final FragmentReserveBinding mBind;
    private final IReserveView mReserveView;
    private List<BindingAdapterItem> mainList;
    private RecyclerView recyclerList;
    private BindingAdapter adapter;
    private final ReserveModelImpl reserveModelImpl;

    public ReserveViewModel(FragmentReserveBinding bind, IReserveView iReserveView) {
        EventBus.getDefault().register(this);
        this.mBind = bind;
        this.mReserveView = iReserveView;
        reserveModelImpl = new ReserveModelImpl();
        reserveModelImpl.getAllProductInfo("");
        recyclerList = bind.recyclerList.recyclerList;

    }

    private void initList(List<ReserveItemBean> reserveItemBeans) {
        mainList = new ArrayList<>();
        LinearLayoutManager ms= new LinearLayoutManager(UIUtils.getContext());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(ms);
        recyclerList.setAdapter(adapter);
        mainList.addAll(reserveItemBeans);
        adapter.setItems(mainList);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void  receiveData(NetResponse netResponse){
        switch (netResponse.getTag()){
            case HttpConstant.RESERVE_ALL_PRODUCT:
                List<ReserveItemBean> reserveItemBeans = GsonUtil.jsonToList((String) netResponse.getData(), ReserveItemBean.class);
                initList(reserveItemBeans);
                break;
            case HttpConstant.STATE_RESERVE_CREAT:
                QrBean qrBean = GsonUtil.GsonToBean((String) netResponse.getData(), QrBean.class);
                mReserveView.initPayee(qrBean);
                break;
        }
    }


    public void creatReserveOrder(String product_id, String user_tel, int num) {
        reserveModelImpl.creatReserve(product_id,user_tel,num+"");
    }

}
