package com.fresh.app.viewmodel;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.bean.PressureBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.databinding.FragmentDetailBinding;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.model.modelimpl.DetailModelImpl;
import com.fresh.app.model.modelimpl.PayeeModelImpl;
import com.fresh.app.view.IDetailView;

/**
 * Created by mr.miao on 2018/5/7.
 */

public class ProductDetailViewModel {
    private final PayeeModelImpl payeeModelImpl;
    private ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();

    public  IDetailView mDetailView;
    public FragmentDetailBinding mActivityDetailBinding;
    private final DetailModelImpl detailModel;

    public ProductDetailViewModel(IDetailView detailView, FragmentDetailBinding fragmentDetailBinding) {
        this.mDetailView=detailView;
        this.mActivityDetailBinding=fragmentDetailBinding;
        payeeModelImpl = new PayeeModelImpl();
        detailModel = new DetailModelImpl();
        setProductId(AppConstant.product_id);
    }

    /**
     * 设置product_id
     */
    public void setProductId(String product_id){
        detailModel.getDataForModel(product_id);
        ProductItemBean productItemBean = productItemBeanDao.queryBuilder()
                .where(ProductItemBeanDao.Properties.ProductId.eq(product_id))
                .unique();

    }


    public void creatFreshOrder(String message, String deviceId) {
        payeeModelImpl.creatOrder(message,deviceId);

    }
}
