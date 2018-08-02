package com.fresh.app.commonUtil;

import android.util.Log;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.bean.SocketBean;
import com.fresh.app.constant.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by mr.miao on 2018/5/14.
 */

public class SocketUtil {

    private static Socket socket;
    private static SocketBean socketBean;

    public static void initSocket(final OnInitSocketListener onInitSocketListener) {
        new Thread(() -> {
            try {
                socket = new Socket("192.168.1.10", 2000);
                onInitSocketListener.onInitSuccess(socket);
            } catch (IOException e) {
                e.printStackTrace();
                onInitSocketListener.onInitFailed("主机错误");
            }
        }).start();
    }


    /**
     * 初始化socket
     */
    public static void getSocket(OnInitSocketListener onInitSocketListener) {
        if (socket != null && socket.isConnected()) {
            onInitSocketListener.onInitSuccess(socket);
        } else {
            initSocket(onInitSocketListener);
        }
    }


    public interface OnInitSocketListener {
        void onInitSuccess(Socket socket);

        void onInitFailed(String errStr);
    }


    /**
     * 接收数据
     */
    public static void getDataFromServer(final Socket socket) {
        if (socket == null) {
            return;
        }

        if (!socket.isConnected()) {
            Log.e("miao", "socket未连接");
            return;
        }
        new Thread(() -> {
            String data = "";
            try {
                // 步骤1：创建输入流对象InputStream
                InputStream is = socket.getInputStream();
                // 步骤2：创建输入流读取器对象 并传入输入流对象
                // 该对象作用：获取服务器返回的数据
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据

                while (true) {
                    byte[] bytes = new byte[8];
                    int i = is.read(bytes);
                    parseData(bytes);
                }




            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }).start();

    }

    private static void parseData(byte[] bytes) {
        if (bytes[0]==0&&bytes[1]==0&&bytes[2]==0&&bytes[3]==0&&bytes[4]==0&&bytes[5]==0&&bytes[6]==0&&bytes[7]==0){
            return;
        }
        StringBuilder binary = new StringBuilder(StringUtils.binary(bytes, 2));

        if (binary.length()<64){
            for (int i=binary.length();i<64;i++){
                binary.insert(0, "0");
            }
        }




        StringBuilder reverse = binary.reverse();
        String substring0 = String.valueOf(reverse).substring(0, 8);
        String substring1 = String.valueOf(reverse).substring(8, 16);
        String substring2 = String.valueOf(reverse).substring(16, 24);
        String substring3 = String.valueOf(reverse).substring(24, 32);
        String substring4 = String.valueOf(reverse).substring(32, 40);
        String substring5 = String.valueOf(reverse).substring(40, 48);
        String substring6 = String.valueOf(reverse).substring(48, 56);
        String substring7 = String.valueOf(reverse).substring(56, 64);
        String result=substring7+substring6+substring5+substring4+substring3+substring2+substring1+substring0;


        Log.e("miao",Arrays.toString(bytes)+"\n"+"miao数据解析"+substring7+" "+substring6+" "+substring5+" "+substring4+" "+substring3+" "+substring2
        +" "+substring1+" "+ substring0);
        Log.e("miao11",binary.length()+"");
        EventBus.getDefault().post(new MessageEvent(10035,result));

    }




    /**
     * 发送数据到服务器
     *
     * @param socket
     * @param data
     */
    public static void sendDataToServer(Socket socket, byte[] data) {
        if (socket == null) {
            return;
        }

        if (!socket.isConnected()) {
            Log.e("miao", "socket未连接");
            return;
        }

        try {

            Log.e("miao", "发送数据"+Arrays.toString(data));
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data);
            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
            // 步骤3：发送数据到服务端 刷新缓冲区
            outputStream.flush();


//            UIUtils.showToast("数据发送"+Arrays.toString(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取socketbean 下面代码谁都别动
     */
    /**
     * 获取socketbean
     *
     * @param id
     */
    public static SocketBean getUpdateBit(String id,boolean bool) {
        socketBean=  CustomApplaction.socketbean;
        if (socketBean==null){
            socketBean = new SocketBean();
            CustomApplaction.socketbean=socketBean;
        }
        switch (id) {
            case "00":
                socketBean.setB0(bool);
                return socketBean;

            case "01":
                socketBean.setB0_1(bool);
                return socketBean;
            case "02":
                socketBean.setB0_2(bool);
                return socketBean;
            case "03":
                socketBean.setB0_3(bool);
                return socketBean;
            case "04":
                socketBean.setB0_4(bool);
                return socketBean;
            case "05":
                socketBean.setB0_5(bool);
                return socketBean;
            case "06":
                socketBean.setB0_6(bool);
                return socketBean;
            case "07":
                socketBean.setB0_7(bool);
                return socketBean;
            case "10":
                socketBean.setB1_0(bool);
                return socketBean;
            case "11":
                socketBean.setB1_1(bool);
                return socketBean;
            case "12":
                socketBean.setB1_2(bool);
                return socketBean;
            case "13":
                socketBean.setB1_3(bool);
                return socketBean;
            case "14":
                socketBean.setB1_4(bool);
                return socketBean;
            case "15":
                socketBean.setB1_5(bool);
                return socketBean;
            case "16":
                socketBean.setB1_6(bool);
                return socketBean;
            case "17":
                socketBean.setB1_7(bool);
                return socketBean;
            case "20":
                socketBean.setB2_0(bool);
                return socketBean;

            case "21":
                socketBean.setB2_1(bool);
                return socketBean;
            case "22":
                socketBean.setB2_2(bool);
                return socketBean;
            case "23":
                socketBean.setB2_3(bool);
                return socketBean;
            case "24":
                socketBean.setB2_4(bool);
                return socketBean;
            case "25":
                socketBean.setB2_5(bool);
                return socketBean;
            case "26":
                socketBean.setB2_6(bool);
                return socketBean;
            case "27":
                socketBean.setB2_7(bool);
                return socketBean;
            case "30":
                socketBean.setB3_0(bool);
                return socketBean;
            case "31":
                socketBean.setB3_1(bool);
                return socketBean;
            case "32":

                socketBean.setB3_2(bool);
                return socketBean;
            case "33":

                socketBean.setB3_3(bool);
                return socketBean;
            case "34":

                socketBean.setB3_4(bool);
                return socketBean;
            case "35":

                socketBean.setB3_5(bool);
                return socketBean;
            case "36":

                socketBean.setB3_6(bool);
                return socketBean;
            case "37":

                socketBean.setB3_7(bool);
                return socketBean;
            case "40":

                socketBean.setB4_0(bool);
                return socketBean;
            case "41":

                socketBean.setB4_1(bool);
                return socketBean;
            case "42":

                socketBean.setB4_2(bool);
                return socketBean;
            case "43":

                socketBean.setB4_3(bool);
                return socketBean;
            case "44":

                socketBean.setB4_4(bool);
                return socketBean;
            case "45":

                socketBean.setB4_5(bool);
                return socketBean;
            case "46":

                socketBean.setB4_6(bool);
                return socketBean;
            case "47":

                socketBean.setB4_7(bool);
                return socketBean;
            case "50":

                socketBean.setB5_0(bool);
                return socketBean;
            case "51":

                socketBean.setB5_1(bool);
                return socketBean;
            case "52":
                socketBean.setB5_2(bool);
                return socketBean;
            case "53":
                socketBean.setB5_3(bool);
                return socketBean;
            case "54":
                socketBean.setB5_4(bool);
                return socketBean;
            case "55":
                socketBean.setB5_5(bool);
                return socketBean;
            case "56":
                socketBean.setB5_6(bool);
                return socketBean;
            case "57":
                socketBean.setB5_7(bool);
                return socketBean;
            default:
                return socketBean;

        }
    }


}
