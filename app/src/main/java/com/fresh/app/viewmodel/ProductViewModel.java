package com.fresh.app.viewmodel;

import android.util.Log;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.StringUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.IConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.ActivityMainBinding;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.model.modelimpl.ProductModelImpl;
import com.fresh.app.view.IProductView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by mr.miao on 2018/5/7.
 */

public class ProductViewModel  implements BaseLoadListener<ProductBean>{

    public IProductView productview;
    public ActivityMainBinding mActivityMainBinding;
    private final ProductModelImpl iProductModel;
    private ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();

    public ProductViewModel(IProductView productView, ActivityMainBinding activityMainBinding) {
        EventBus.getDefault().register(this);
        this.productview = productView;
        this.mActivityMainBinding = activityMainBinding;
        iProductModel = new ProductModelImpl();
    }





    @Override
    public void loadSuccess(List<ProductBean> list) {

    }

    @Override
    public void loadSuccess(ProductBean productBean) {
        productview.getDataSuccessed(productBean.getData());
        //开线程 将数据写入数据库
        CustomApplaction.getExecutorService().execute(() -> {
            List<ProductItemBean> data = productBean.getData();
            productItemBeanDao.insertOrReplaceInTx(data);
            LogUtils.e("数据库写入完成");
        });


    }

    @Override
    public void loadFailure(String message) {
        getData(new MessageEvent(1003,""));
    }

    @Override
    public void loadStart() {

    }

    @Override
    public void loadComplete() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(MessageEvent messageEvent) {
        switch (messageEvent.getCode()) {
            case 1001:
                UIUtils.showToast("收到 socket 信息：：：" + messageEvent.getMessage());
                break;

            case 1002:
                Log.e("miao网络数据","请求网络数据");
                iProductModel.getProductData("20180515_01",this);
                break;

            case 1003:
                Log.e("miao查询数据库","");
                //查询数据库
                ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();
                List<ProductItemBean> productItemBeans = productItemBeanDao.loadAll();
                productview.getDataSuccessed(productItemBeans);

                break;
            default:
                LogUtils.e("无任何绑定");
                break;
        }
    }

}
