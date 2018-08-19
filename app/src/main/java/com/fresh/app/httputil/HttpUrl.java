package com.fresh.app.httputil;

import com.fresh.app.constant.AppConstant;

/**
 * Created by Administrator on 2017/9/26.
 */

public class HttpUrl {

    public  static String SOCKET_URL="192.168.1.3";
    private static String baseUrl="";


    public static String getBaseUrl() {
        if (AppConstant.isDebug){
//            return "http://192.168.1.101:8080/";
            return "http://39.105.77.235/app/";
        }
        return baseUrl;
    }

    /**
     * 图片路径
     * @return
     */
    public static String getBaseImgUrl() {
        return getBaseUrl() + "static/appImage/";
    }

    /**
     * 设备产品
     */
    public static String PRODUCT_URL=getBaseUrl()+"product";
    /**
     * 产品详情
     */
    public static final String PRODUCT_DETAIL_URL ="product_detail" ;
    /**
     *创建微信支付二维码
     */
    public static String CREAT_QR_URL="createQRCode";
    /**
     * 产品订单支付
     */
    public  static  String PAY_PRODUCT_RESULT_URL="order_state";
    /**
     * 会员卡支付
     */
    public static String PAY_PRODUCT_CARD_RESULT_URL="payForCard";
    /**
     * 充值
     */
    public static String RECHARGE_CREAT_URL="card_recharge";
    /**
     * 充值结果
     */
    public static String PAY_RECHARGE_RESULT_URL="getCardState";
    /**
     * 获取全部商品
     */
    public static String ALL_PRODUCT_URL="getAllProduct";

    /**
     * 创建预定订单
     */
    public static String RESERVE_CREAT_URL="creatReserveorder";


    /**
     * 预定 支付结果
     */
    public static String RESERVE_RESULT_URL="reserve_order_state";

    /**
     * 提货
     */
    public static String TAKEGOODS_URL="takeGoods";

    /**
     * 会员卡信息
     */
    public static String CARD_INFO_URL="card_info";
    /**
     *会员卡消费历史
     */
    public static String CARD_HISTORY_URL="card_history";
    /**
     * 短信验证码
     */
    public static String GET_SMSCODE_URL="getCode";
    /**
     * 更新用户卡的信息
     */
    public static String UPDATE_CARD_INFO="update_card_info";

    /**
     * 获取版本信息
     */
    public static String GET_VERSION_URL="getVersion";
    /**
     * 获取米桶编号
     */
    public static String RICEBUCKET_URL="getRiceBucket";
    /**
     * 更新米桶编号
     */
    public static String URDATE_RICEBUCKET_URL="updateRiceBucket";

    /**
     * 获取设备视频信息
     */
    public static String STATE_GET_VIDEO="getVideo";

    /**
     * 设备ID验证
     */
    public static String STATE_CHECK="checkDeviceId";

}


