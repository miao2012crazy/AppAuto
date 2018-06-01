package com.fresh.app.constant;

/**
 * 串口通信常量类
 * Created by mr.miao on 2018/4/28.
 */
public class IConstant {


    //端口
    public static final String PORT = "usbserail";

    //波特率
    public static final int BAUDRATE = 19200;

    //读取产品信息
    public static final String read_pproduct="0004001014";

    //设定寻卡模式 开机默认自动寻卡 并直接保存 状态 设置 整个设备只初始化一次
    public static final String default_state="0x0005001E09".replace("0x","");

    //临时关闭自动寻卡
    public static final String close_find="0005 00 11 00".replace(" ","");

    //临时关闭自动寻卡
    public static final String open_find="0005 00 11 01".replace(" ","");

    //读取钱包
    public static  final String read_walte="0x000C 00 24 00 01 FF FF FF FF FF FF".replace(" ","").replace("0x","");

    /**
     * 充值钱包 充值金额  不带金额  需要在后面增加四字节 钱包增加值  低位在前 比如 充值 100元  64 00 00 00
     */
    public static final String recharge_walte="0x0010 00 25 00 01 FF FF FF FF FF FF".replace(" ","").replace("0x","");

    /**
     * 钱包扣款 不带金额  四字节 需要在后面增加四字节 钱包扣款值  低位在前 比如 扣款 20元  14 00 00 00
     */
    public static final  String reduce_walte="0x0010 00 26 00 01 FF FF FF FF FF FF ".replace(" ","").replace("0x","");


    /**
     * 初始化钱包 加充值金额
     */
    public  static final String reset_walte="0x0010 00 23 00 01 FF FF FF FF FF FF 04 03 02 01".replace(" ","").replace("0x","");


}
