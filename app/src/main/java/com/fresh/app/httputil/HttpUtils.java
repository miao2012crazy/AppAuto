package com.fresh.app.httputil;

import com.fresh.app.bean.CardHistoryBean;
import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.QRBean;
import com.fresh.app.bean.QueryCardBean;
import com.fresh.app.bean.ReserOrderBean;
import com.fresh.app.bean.ReserveBean;
import com.fresh.app.bean.ResultBean;
import com.fresh.app.bean.RiceBucketBean;
import com.fresh.app.bean.VersionBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mr.miao on 2018/5/7.
 */

public class HttpUtils {


    private static final int DEFAULT_TIMEOUT = 8; //连接 超时的时间，单位：秒
    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
            readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
            writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();
    private static HttpUtils httpUtils;
    private static Retrofit retrofit;
    private static RetrofitInterface retrofitInterface;

    private synchronized static RetrofitInterface getRetrofit() {
        //初始化retrofit的配置
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(HttpUrl.getBaseUrl())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            retrofitInterface = retrofit.create(RetrofitInterface.class);
        }
        return retrofitInterface;
    }

    //获取商品详情数据
    public static Observable<ProductItemBean> getProductDetail(String product_id) {
        return getRetrofit().getProductDetailData(product_id);
    }

    //获取商品
    public static Observable<ProductBean> getProduct(String deviceid) {
        return getRetrofit().getProductData(deviceid);
    }


    /**
     * 创建订单
     *
     * @param card_id 会员卡id
     * @return 会员卡信息 QueryCardBean
     */
    public static Observable<QueryCardBean> getCardInfo(String card_id) {
        return getRetrofit().getCardInfo(card_id);
    }


    public static Observable<CardHistoryBean> getCardHistory(String card_id) {
        return getRetrofit().getCardHistory(card_id);
    }

    public static Observable<String> getPayResult(String orderId) {
        return getRetrofit().getPayResultFromNet(orderId);
    }


    public static Observable<QRBean> creatOrder(String product_id, String device_id) {
        return getRetrofit().getQRUrl(product_id, device_id);

    }

    public static Observable<QRBean> setRecharge(String memberid, String tel, String money_num) {
        return getRetrofit().getRechargeQRUrl(memberid, tel,money_num);
    }

    /**
     *
     * @param deviceId
     * @return
     */
    public static Observable<ReserveBean> getAllProduct(String deviceId) {
        return getRetrofit().getAllProduct(deviceId);
    }

    /**
     *
     * @param product_id    商品id
     * @param user_tel      预留手机号
     * @param product_num   商品数量
     * @return
     */
    public static Observable<QRBean> creatReserveOrder(String device_id, String product_id, String user_tel, String product_num) {
        return getRetrofit().creatReserOrder(device_id,product_id,user_tel,product_num);
    }


    /**
     *
     * @param order_id
     * @return
     */
    public static Observable<String> getRechargeOrderState(String order_id) {
        return getRetrofit().getRechargeResultFromNet(order_id);
    }

    public static Observable<ReserOrderBean> getGoods(String device_id, String code) {
        return getRetrofit().getGoodsForCode(device_id,code);
    }

    public static Observable<String>  getSmsCode(String tel) {
        return getRetrofit().getCode(tel);

    }

    public static Observable<String> updateCard(String code, String tel, String msg_id, String member_id, String device_id) {
        return getRetrofit().updateCardInfo(code,tel,msg_id,member_id,device_id);

    }

    public static Observable<String> getReserveOrderState(String orderId) {
        return getRetrofit().getReserveState(orderId);

    }

    public static Observable<ResultBean> payOrderUseCard(String order_id, String card_id) {
        return getRetrofit().getPayResultForCard(order_id,card_id);
    }

    public static Observable<VersionBean> getVersion(String s) {
        return getRetrofit().getVersion(s);
    }

    public static Observable<RiceBucketBean> getRiceBucket(String deviceid) {
        return getRetrofit().getRiceBucketList(deviceid);
    }

    public static Observable<ResultBean> updateRiceBucketState(String deviceid, String riceBucketId) {
        return getRetrofit().updateRiceBucketState(deviceid,riceBucketId);
    }
}
