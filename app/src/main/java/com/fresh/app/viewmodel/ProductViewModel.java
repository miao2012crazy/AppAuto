package com.fresh.app.viewmodel;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.StringUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.IConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.ActivityMainBinding;
import com.fresh.app.databinding.FragmentHomeBinding;
import com.fresh.app.databinding.FragmentProductBinding;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.model.modelimpl.ProductModelImpl;
import com.fresh.app.view.IProductView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.miao on 2018/5/7.
 */

public class ProductViewModel  implements BaseLoadListener<ProductBean>{

    public IProductView productview;
    public FragmentProductBinding viewDataBinding;
    private final ProductModelImpl iProductModel;
    private ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();
    private BindingAdapter adapter;
    private List<BindingAdapterItem> mainList;
    private RecyclerView recyclerList;

    public ProductViewModel(IProductView productView, FragmentProductBinding viewDataBinding) {
//        EventBus.getDefault().register(this);
        this.productview = productView;
        this.viewDataBinding =  viewDataBinding;
        iProductModel = new ProductModelImpl();
        iProductModel.getProductData("20180515_01",this);
    }





    @Override
    public void loadSuccess(List<ProductBean> list) {

    }

    @Override
    public void loadSuccess(ProductBean productBean) {
//        productview.getDataSuccessed(productBean.getData());
        initList(productBean);
        //开线程 将数据写入数据库
        CustomApplaction.getExecutorService().execute(() -> {
            List<ProductItemBean> data = productBean.getData();
            productItemBeanDao.insertOrReplaceInTx(data);
            LogUtils.e("数据库写入完成");
        });

    }

    private void initList(ProductBean productBean) {
        mainList = new ArrayList<>();
        recyclerList = viewDataBinding.recyclerList.recyclerList;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UIUtils.getContext(), 4);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(adapter);
        mainList.addAll(productBean.getData());
        adapter.setItems(mainList);
    }

    @Override
    public void loadFailure(String message) {
//        getData(new MessageEvent(1003,""));
    }

    @Override
    public void loadStart() {

    }

    @Override
    public void loadComplete() {

    }
}
