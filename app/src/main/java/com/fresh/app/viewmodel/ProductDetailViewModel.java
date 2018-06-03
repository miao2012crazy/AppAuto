package com.fresh.app.viewmodel;

import android.util.Log;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.ActivityDetailBinding;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.model.modelimpl.DetailModelImpl;
import com.fresh.app.view.IDetailView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by mr.miao on 2018/5/7.
 */

public class ProductDetailViewModel implements BaseLoadListener<ProductItemBean>{

    public  IDetailView mDetailView;
    public ActivityDetailBinding mActivityDetailBinding;
    private final DetailModelImpl detailModel;

    public ProductDetailViewModel(IDetailView detailView, ActivityDetailBinding activityDetailBinding) {
        EventBus.getDefault().register(this);
        this.mDetailView=detailView;
        this.mActivityDetailBinding=activityDetailBinding;
//        mActivityDetailBinding.setModel(new ProductItemBean());
        detailModel = new DetailModelImpl();
    }


    /**
     * 设置product_id
     */
    public void setProductId(String product_id){
        detailModel.getDataForModel(product_id,this);
    }


    @Override
    public void loadSuccess(List<ProductItemBean> list) {

    }

    @Override
    public void loadSuccess(ProductItemBean productDetailBean) {
        mActivityDetailBinding.setItem(productDetailBean);
        CustomApplaction.product_detail_bean=productDetailBean;
    }

    @Override
    public void loadFailure(String message) {
        mDetailView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mDetailView.loadStart();
    }

    @Override
    public void loadComplete() {
        mDetailView.loadComplete();
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(MessageEvent messageEvent) {
        switch (messageEvent.getCode()) {
            case 1002:
                Log.e("miao网络数据","请求网络数据");

                break;

            case 1003:
//                Log.e("miao查询数据库","");
//
//                //查询数据库
//                ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();
//                List<ProductItemBean> productItemBeans = productItemBeanDao.loadAll();
//                mActivityDetailBinding.setItem(productDetailBean);
//                productview.getDataSuccessed(productItemBeans);
                break;
        }
    }


}
