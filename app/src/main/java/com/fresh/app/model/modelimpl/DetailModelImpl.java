package com.fresh.app.model.modelimpl;

import android.util.Log;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseLoadListener;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.gen.ProductDetailBeanDao;
import com.fresh.app.httputil.HttpUtils;
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
    //查询数据库
   private ProductDetailBeanDao productDetailBeanDao = CustomApplaction.getInstances().getDaoSession().getProductDetailBeanDao();
    @Override
    public void getDataForModel(String product_id, final BaseLoadListener<ProductDetailBean> loadListener) {

        ProductDetailBean productDetailBean = productDetailBeanDao.queryBuilder()
                .where(ProductDetailBeanDao.Properties.ProductId.eq(product_id))
                .unique();

        if (productDetailBean == null) {
            HttpUtils.getProductDetail(product_id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<ProductDetailBean>() {
                        @Override
                        public void onNext(ProductDetailBean value) {
                            //保存数据到数据库
                            saveRaw(value);

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
//            Log.e("miao数据库数据",productDetailBean.toString());
            loadListener.loadSuccess(productDetailBean);
        }
    }

    /**
     * 保存数据到数据库
     * @param value
     */
    private void saveRaw(ProductDetailBean value) {
        productDetailBeanDao.insert(value);
//        Log.e("miao","保存了一条数据");
//        List<ProductDetailBean> productDetailBeans = productDetailBeanDao.loadAll();
//        Log.w("miao", productDetailBeans.toString());
    }
}