package com.fresh.app.constant;

import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.ReserOrderBean;

/**
 * Created by mr.miao on 2018/5/2.
 */

public class AppConstant {
    public static ReserOrderBean RESERORDERBEAN = null;
    /**
     * 是否为调试模式 调试模式下 无法连接硬件
     */
    public static boolean isDebug = true;
    /**
     * 是否缓存
     */
    public static boolean isCache=false;


    public static String product_id="";

    /**
     * 读卡器状态     读卡器默认状态 默认状态
     * 查询0
     * 支付 1
     * 读取会员卡号 2
     * 装货 3
     * 扫描米桶 4
     * 检修卡调试页面 5
     * 配送卡  6
     */
    public static int CARD_READER_STATE=999;


    public static String DEVICE_ID="";

    public static ProductItemBean PRODUCT_ITEM_BEAN=null;
    //随机数全局变量
    public static String NONCE_STR = "";

}
