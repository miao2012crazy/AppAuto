package com.fresh.app.viewmodel;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fresh.app.R;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.HomeBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.databinding.FragmentHomeBinding;
import com.fresh.app.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/5/31.
 */

public class HomeViewModel {
    private final FragmentHomeBinding mBinding;
    private final IHomeView mHomeView;
    private RecyclerView recyclerList;
    private List<BindingAdapterItem> mainList;
    private BindingAdapter adapter;
    private List<HomeBean> homeBeans;

    public HomeViewModel(IHomeView homeView, ViewDataBinding binding) {
        this.mBinding=(FragmentHomeBinding)binding;
        this.mHomeView=homeView;
        initRecyclerList();
    }


    /**
     * 初始化列表
     */
    public void initRecyclerList() {
        mainList = new ArrayList<>();
        homeBeans = new ArrayList<>();

        recyclerList = mBinding.recyclerList.recyclerList;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UIUtils.getContext(), 2);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(adapter);

        HomeBean homeBean = new HomeBean("购买",0, R.mipmap.ic_buy);
        HomeBean homeBean1 = new HomeBean("充值",1, R.mipmap.ic_pay);
        HomeBean homeBean2 = new HomeBean("预定",2, R.mipmap.ic_subscribe);
        HomeBean homeBean3 = new HomeBean("自提",3, R.mipmap.ic_ziti);
        homeBeans.add(homeBean);
        homeBeans.add(homeBean1);
        homeBeans.add(homeBean2);
        homeBeans.add(homeBean3);
        mainList.addAll(homeBeans);
        adapter.setItems(mainList);
    }

}
