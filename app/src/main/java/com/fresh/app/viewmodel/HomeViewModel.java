package com.fresh.app.viewmodel;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.HomeBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.databinding.ActivityHomeBinding;
import com.fresh.app.view.IHomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/5/31.
 */

public class HomeViewModel {
    private final ActivityHomeBinding mBinding;
    private final IHomeView mHomeView;
    private RecyclerView recyclerList;
    private List<BindingAdapterItem> mainList;
    private BindingAdapter adapter;
    private List<HomeBean> homeBeans;

    public HomeViewModel(IHomeView homeView, ActivityHomeBinding binding) {
        this.mBinding=binding;
        this.mHomeView=homeView;
    }


    /**
     * 初始化列表
     */
    public void initRecyclerList() {
        mainList = new ArrayList<>();
        homeBeans = new ArrayList<>();

        recyclerList = mBinding.listHome.recyclerList;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UIUtils.getContext(), 2);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(adapter);

        HomeBean homeBean = new HomeBean("购买");
        HomeBean homeBean1 = new HomeBean("充值");
        HomeBean homeBean2 = new HomeBean("预定");
        homeBeans.add(homeBean);
        homeBeans.add(homeBean1);
        homeBeans.add(homeBean2);
        mainList.addAll(homeBeans);
        adapter.setItems(mainList);
    }

}
