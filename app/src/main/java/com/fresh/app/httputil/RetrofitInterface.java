package com.fresh.app.httputil;

import com.fresh.app.bean.FreshOrderBean;
import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.bean.QueryCardBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by mr.miao on 2018/5/7.
 */

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("product_detail")
    Observable<ProductDetailBean> getProductDetailData(@Field("product_id")String product_id);

    @FormUrlEncoded
    @POST("product")
    Observable<ProductBean> getProductData(@Field("deviceid")String deviceid);

    @FormUrlEncoded
    @POST("order_create")
    Observable<FreshOrderBean> creatOrder(@Field("product_id")String product_id, @Field("deviceid")String deviceid);

    @FormUrlEncoded
    @POST("card_info")
    Observable<QueryCardBean> getCardInfo(@Field("card_id")String card_id);
}
