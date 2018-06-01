package com.fresh.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mr.miao on 2018/5/16.
 */

public class SocketService extends Service {

    private Socket socket;


    /**心跳任务，不断重复调用自己*/
    private Runnable heartBeatRunnable = new Runnable() {

        @Override
        public void run() {

        }
    };



    @Override
    public void onCreate() {
        super.onCreate();
        //第一次创建时执行
    }

    /**
     * 初始化socket连接
     */
    private void initSocket() {
        UIUtils.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("10.13.20.137", 8888);
                    Log.e("miao","socket连接初始化");
                    getDataFromServer();
                } catch (Exception ex) {

                }

            }
        });

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (socket == null || !socket.isConnected()) {
            initSocket();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 接收数据
     */
    public void getDataFromServer() {
        if (socket == null) {
            return;
        }

        if (!socket.isConnected()) {
            Log.e("miao", "socket未连接");
            initSocket();
            return;
        }
        try {
            // 步骤1：创建输入流对象InputStream
            InputStream is = socket.getInputStream();
            // 步骤2：创建输入流读取器对象 并传入输入流对象
            // 该对象作用：获取服务器返回的数据
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据
            while (true) {
                String data = br.readLine();
                Log.e("miao接收到数据", data);
                EventBus.getDefault().post(new MessageEvent(1001, data));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 发送数据到服务器
     *
     * @param data
     */
    public void sendDataToServer(String data) {
        if (socket == null) {
            return;
        }

        if (!socket.isConnected()) {
            Log.e("miao", "socket未连接");
            return;
        }

        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write((data + "\n").getBytes("utf-8"));
            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
            // 步骤3：发送数据到服务端 刷新缓冲区
            outputStream.flush();
            UIUtils.showToast("数据发送");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
