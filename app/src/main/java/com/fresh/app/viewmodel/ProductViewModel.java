package com.fresh.app.viewmodel;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.FragmentProductBinding;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.httputil.HttpConstant;
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

public class ProductViewModel{

    private IProductView productview;
    private FragmentProductBinding viewDataBinding;
    private ProductModelImpl iProductModel;
    private ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();
    private BindingAdapter adapter;
    private List<BindingAdapterItem> mainList;
    private RecyclerView recyclerList;

    public ProductViewModel(IProductView productView, FragmentProductBinding viewDataBinding) {
        EventBus.getDefault().register(this);
        this.productview = productView;
        this.viewDataBinding =  viewDataBinding;
        iProductModel = new ProductModelImpl();
        iProductModel.getProductData(AppConstant.DEVICE_ID);
    }


    private void initList(List<ProductItemBean> productItemBean) {
        mainList = new ArrayList<>();
        recyclerList = viewDataBinding.recyclerList.recyclerList;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(UIUtils.getContext(), 4);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(adapter);
        mainList.addAll(productItemBean);
        adapter.setItems(mainList);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveData(NetResponse netResponse){
        if (!netResponse.getTag().equals(HttpConstant.STATE_PRODUCT)){
            return;
        }
        String json = (String) netResponse.getData();
        List<ProductItemBean> productItemBeans = GsonUtil.jsonToList(json, ProductItemBean.class);
        initList(productItemBeans);
        //开线程 将数据写入数据库
        CustomApplaction.getExecutorService().execute(() -> {
            productItemBeanDao.insertOrReplaceInTx(productItemBeans);
            LogUtils.e("数据库写入完成");
        });
    }

}
