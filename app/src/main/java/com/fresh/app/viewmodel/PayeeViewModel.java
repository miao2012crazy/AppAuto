package com.fresh.app.viewmodel;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fresh.app.R;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.PayeeBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.databinding.FragmentPayeeBinding;
import com.fresh.app.model.modelimpl.PayeeModel;
import com.fresh.app.view.IPayeeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/5/10.
 */
public class PayeeViewModel {
    private final IPayeeView payeeView;
    private final FragmentPayeeBinding payeeBinding;
    private final PayeeModel payeeModel;
    private String product_id;
    private List<BindingAdapterItem> mainList;
    private List<PayeeBean> homeBeans;
    private RecyclerView recyclerList;
    private BindingAdapter adapter;


    public PayeeViewModel(IPayeeView payeeView, FragmentPayeeBinding payeeBinding) {
        this.payeeView = payeeView;
        this.payeeBinding = payeeBinding;
        payeeModel = new PayeeModel();
        initRecyclerList();

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
        PayeeBean payeeBean0 = new PayeeBean("云稻会员卡",R.mipmap.ic_huiyuan,true,0);
        PayeeBean payeeBean1 = new PayeeBean("微信扫码支付",R.mipmap.ic_wechat,true,1);
        PayeeBean payeeBean2 = new PayeeBean("支付宝扫码支付",R.mipmap.ic_alipay,true,2);

        homeBeans.add(payeeBean0);
        homeBeans.add(payeeBean1);
        homeBeans.add(payeeBean2);
        mainList.addAll(homeBeans);
        adapter.setItems(mainList);
    }





}
