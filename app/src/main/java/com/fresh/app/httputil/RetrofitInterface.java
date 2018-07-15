package com.fresh.app.httputil;

import com.fresh.app.bean.CardHistoryBean;
import com.fresh.app.bean.FreshOrderBean;
import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.QRBean;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.bean.ReserOrderBean;
import com.fresh.app.bean.ReserveBean;
import com.fresh.app.bean.ResultBean;
import com.fresh.app.bean.RiceBucketBean;
import com.fresh.app.bean.VersionBean;

import io.reactivex.Completable;
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
    Observable<ProductItemBean> getProductDetailData(@Field("product_id")String product_id);

    @FormUrlEncoded
    @POST("product")
    Observable<ProductBean> getProductData(@Field("deviceid")String deviceid);

    @FormUrlEncoded
    @POST("order_create")
    Observable<FreshOrderBean> creatOrder(@Field("product_id")String product_id, @Field("deviceid")String deviceid);

    @FormUrlEncoded
    @POST("card_info")
    Observable<QueryCardBean> getCardInfo(@Field("card_id")String card_id);
    @FormUrlEncoded
    @POST("card_history")
    Observable<CardHistoryBean> getCardHistory(@Field("card_id") String card_id);

    @FormUrlEncoded
    @POST("order_state")
    Observable<String> getPayResultFromNet(@Field("order_id") String orderId);
    @FormUrlEncoded
    @POST("createQRCode")
    Observable<QRBean> getQRUrl(@Field("product_id") String product_id,@Field("deviceid") String device_id);

    @FormUrlEncoded
    @POST("card_recharge")
    Observable<QRBean> getRechargeQRUrl(@Field("member_id")String memberid,@Field("tel") String tel, @Field("money_num")String money_num);

    @FormUrlEncoded
    @POST("getAllProduct")
    Observable<ReserveBean> getAllProduct(@Field("deviceid") String deviceId);

    @FormUrlEncoded
    @POST("creatReserveorder")
    Observable<QRBean> creatReserOrder(@Field("device_id")String device_id,@Field("product_id") String product_id, @Field("user_tel")String user_tel,@Field("product_num") String product_num);

    @FormUrlEncoded
    @POST("getCardState")
    Observable<String> getRechargeResultFromNet(@Field("order_id") String orderId);

    @FormUrlEncoded
    @POST("takeGoods")
    Observable<ReserOrderBean> getGoodsForCode(@Field("device_id") String device_id,@Field("sms_code") String code);
    @FormUrlEncoded
    @POST("getCode")
    Observable<String> getCode(@Field("tel")String tel);

    @FormUrlEncoded
    @POST("update_card_info")
    Observable<String> updateCardInfo(@Field("code") String code, @Field("tel") String tel, @Field("msg_id") String msg_id,@Field("member_id")  String member_id, @Field("device_id") String device_id);

    @FormUrlEncoded
    @POST("reserve_order_state")
    Observable<String> getReserveState(@Field("order_id") String orderId);
    @FormUrlEncoded
    @POST("payForCard")
    Observable<ResultBean> getPayResultForCard(@Field("order_id")String order_id, @Field("card_id")String card_id);
    @FormUrlEncoded
    @POST("getVersion")
    Observable<VersionBean> getVersion(@Field("version_code") String s);

    @FormUrlEncoded
    @POST("getRiceBucket")
    Observable<RiceBucketBean> getRiceBucketList(@Field("deviceid") String deviceid);

    @FormUrlEncoded
    @POST("updateRiceBucket")
    Observable<ResultBean> updateRiceBucketState(@Field("deviceid") String deviceid,@Field("riceBucketId") String riceBucketId);
}
