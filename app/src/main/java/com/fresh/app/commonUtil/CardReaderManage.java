package com.fresh.app.commonUtil;
import com.fresh.app.constant.AppConstant;

/**
 * Created by mr.miao on 2018/7/14.
 */

public class CardReaderManage {

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
    public static void setCardReaderState(int state){
        SerialPortUtil.closeSerialPort();
        AppConstant.CARD_READER_STATE=state;
        if (state==4){
            SerialPortUtil.openSrialPort("/dev/ttymxc2");
        }else if (state==3){
            SerialPortUtil.openSrialPort("/dev/ttymxc3");
        }else {
            SerialPortUtil.openSrialPort("/dev/ttymxc1");
        }
    }
}
