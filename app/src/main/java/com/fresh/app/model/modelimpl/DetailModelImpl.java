package com.fresh.app.model.modelimpl;

import android.util.Log;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.QRBean;
import com.fresh.app.gen.ProductDetailBeanDao;
import com.fresh.app.gen.ProductItemBeanDao;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.listener.OnCreatOrderListener;
import com.fresh.app.model.IDetailModel;

import org.greenrobot.greendao.query.Query;

import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mr.miao on 2018/5/7.
 */
public class DetailModelImpl implements IDetailModel {

    @Override
    public void getDataForModel(String product_id, final BaseLoadListener<ProductItemBean> loadListener) {

        ProductItemBeanDao productItemBeanDao = CustomApplaction.getInstances().getDaoSession().getProductItemBeanDao();
        ProductItemBean productItemBean = productItemBeanDao.queryBuilder()
                .where(ProductItemBeanDao.Properties.ProductId.eq(product_id))
                .unique();

        if (productItemBean==null){
            HttpUtils.getProductDetail(product_id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<ProductItemBean>() {
                        @Override
                        public void onNext(ProductItemBean value) {
                            //保存数据到数据库
//                            saveRaw(value);
                            loadListener.loadSuccess(value);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else{
            loadListener.loadSuccess(productItemBean);
        }
    }



}