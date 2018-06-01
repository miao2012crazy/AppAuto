package com.fresh.app.httputil;

import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.FreshOrderBean;
import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.bean.QueryCardBean;

import java.util.concurrent.TimeUnit;

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
    public static Observable<ProductDetailBean> getProductDetail(String product_id) {
        return getRetrofit().getProductDetailData(product_id);
    }

    //获取商品
    public static Observable<ProductBean> getProduct(String deviceid) {
        return getRetrofit().getProductData(deviceid);
    }

    /**
     * 创建订单
     * @param product_id 商品id
     * @param deviceid  设备id
     * @return
     */
    public static Observable<FreshOrderBean> creatOrder(String product_id, String deviceid) {
        return getRetrofit().creatOrder(product_id,deviceid);
    }
 /**
     * 创建订单
     * @param card_id 会员卡id
     * @return  会员卡信息 QueryCardBean
     */
    public static Observable<QueryCardBean> getCardInfo(String card_id) {
        return getRetrofit().getCardInfo(card_id);
    }


}
