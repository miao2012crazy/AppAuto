package com.fresh.app.commonUtil;

import android.util.Log;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.IConstant;
import com.fresh.app.constant.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executors;

import android_serialport_api.SerialPort;

/**
 * 苗
 * Created by mr.miao on 2018/4/28.
 */
public class SerialPortUtil {

    public static SerialPort serialPort = null;
    public static InputStream inputStream = null;
    public static OutputStream outputStream = null;

    public static Thread receiveThread = null;

    public static boolean flag = false;

    public static String serialData;

    /**
     * 打开串口的方法
     */
    public static void openSrialPort(String port_name) {
        Log.i("miao", "打开串口");
        try {
            File device = new File(port_name);
            Log.e("device", device.getAbsolutePath());
            serialPort = new SerialPort(device, IConstant.BAUDRATE, 0);
            //获取打开的串口中的输入输出流，以便于串口数据的收发
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
            flag = true;
            receiveSerialPort();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * /**
     * 关闭串口的方法
     * 关闭串口中的输入输出流
     * 然后将flag的值设为flag，终止接收数据线程
     */
    public static void closeSerialPort() {
        Log.i("miao", "关闭串口");
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            flag = false;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送串口数据的方法
     *
     * @param data 要发送的数据
     */
    public static void sendSerialPort(String data) {
        Log.i("miao", "发送串口数据");
        try {
            byte[] bytes1 = StringUtils.hexStringToBytes(data);
            outputStream.write(bytes1);
            outputStream.flush();
            Log.i("miao", "串口数据发送成功");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("miao", "串口数据发送失败");
        }
    }

    /**
     * 发送串口数据的方法
     *
     * @param data 要发送的数据
     */
    public static void sendSerialPort(byte[] data) {
        Log.i("miao", "发送串口数据byte数组");
        try {
            outputStream.write(data);
            outputStream.flush();
            Log.i("miao", "串口数据发送成功");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("miao", "串口数据发送失败");
        }
    }


    /**
     * 接收串口数据的方法
     */
    public static void receiveSerialPort() {
        Log.i("miao", "开启接收");

        if (receiveThread != null)
            return;

//        创建子线程接收串口数据
        receiveThread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        int count = 0;
                        while (count == 0) {
                            count = inputStream.available();
//                            Log.w("miao","没有数据");
                        }
                        byte[] bytes = new byte[count];
                        int readCount = 0; // 已经成功读取的字节的个数
                        while (readCount < count) {
                            readCount += inputStream.read(bytes, readCount, count - readCount);
                            String s = StringUtils.bytesToHexString(bytes, readCount);
                            Log.e("miao", "有数据" + s);

                            parseData(s);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        //启动接收线程
        receiveThread.start();
    }


    /**
     * 判断当前数据内容 为支付状态 还是查询状态
     * 读卡器状态     读卡器默认状态 默认状态
     * 查询0
     * 支付 1
     * 读取会员卡号 2
     * 装货 3
     * 扫描米桶 4
     * 检修卡调试页面 5
     * 配送卡  6
     *
     * @param s
     */
    private static void parseData(String s) {
        CustomApplaction.MEMBER_ID = s;
        switch (AppConstant.CARD_READER_STATE) {
            case 0:
                AppConstant.CARD_READER_STATE = 999;
                EventBus.getDefault().post(new MessageEvent(1004, s));
                break;
            case 1:
                AppConstant.CARD_READER_STATE = 999;


                break;
            case 2:
                AppConstant.CARD_READER_STATE = 999;


                break;
            case 3:



                break;
            case 4:



                break;
            case 5:
                break;
            case 6:
                break;
            default:


                break;


        }
    }
}
