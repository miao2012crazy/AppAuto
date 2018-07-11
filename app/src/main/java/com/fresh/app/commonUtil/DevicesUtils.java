package com.fresh.app.commonUtil;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;

/**
 * @author qiwenming
 * @creation 2015-7-20 上午10:06:12
 * @instruction 串口操作工具类
 */
public class DevicesUtils {
    protected OutputStream mOutputStream;
    private InputStream mInputStream;
    private ReadThread mReadThread;
    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
    private SerialPort mSerialPort;
    private Context context;

    /**
     * @author qiwenming
     * @creation 2015-6-18 下午4:38:54
     * @instruction 读取类
     */
    private class ReadThread extends Thread {
        private ReciverListener listener;
        public boolean isReadData = false;
        public boolean isOK = true;

        public ReadThread( ReciverListener listener) {
            this.listener = listener;
        }

        @Override
        public void run() {
            StringBuffer sb = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            //线程安全的集合
            // List<Byte> synchArrayList = Collections.synchronizedList(new ArrayList<Byte>());
            super.run();
            while (!isInterrupted()) {
                int size;
                try {
                    byte[] buffer = new byte[1024];
                    if (mInputStream == null)
                        return;
                    size = mInputStream.read(buffer);
                    Log.i("--------------", "---------------mInputStream---------------" + mInputStream.available());

                } catch (Exception e) {
                    listener.onErr(e);
                    return;
                }
            }
        }
    }



    /**
     * 获取到串口通信的一个是咧
     *
     * @param path
     * @param baudrate
     * @return
     * @throws SecurityException
     * @throws IOException
     * @throws InvalidParameterException
     */
    public SerialPort getSerialPort(String path, int baudrate)
            throws SecurityException, IOException, InvalidParameterException {
        // if (mSerialPort == null) {
		/* Check parameters */
        if ((path.length() == 0) || (baudrate == -1)) {
            throw new InvalidParameterException();
        }
		/* Open the serial port */
        mSerialPort = new SerialPort(new File(path), baudrate, 0);// 打开这个串口
        // }

        return mSerialPort;
    }

    public void closeDevice() {
        if (mReadThread != null)
            mReadThread.interrupt();
        // mApplication.closeSerialPort();
        closeSerialPort();
        // mSerialPort = null;
    }

    public void closeSerialPort() {// 关闭窗口
        if (mSerialPort != null) {
            mSerialPort.close();
            mSerialPort = null;
        }
    }

    /**
     * @author qiwenming
     * @creation 2015-7-20 上午10:16:54
     * @instruction 接受回调类
     */
    public interface ReciverListener {

        /**
         * 接受以后的处理方法
         *
         * @param receviceStr
         */
        void onReceived(String receviceStr);

        /**
         * 出错
         *
         * @param fialStr
         */
        void onFail(String fialStr);

        /**
         * 出现异常
         *
         * @param e
         */
        void onErr(Exception e);

    }

    /**
     * @author qiwenming
     * @creation 2015-7-20 下午2:34:28
     * @instruction 这个是我们用于存储读取的数据
     */
    public class RecevedData {
        public ReturnType returnType;
        /**
         * 数据
         */
        public String receviedData;
    }

    /**
     * @author qiwenming
     * @creation 2015-7-20 下午2:36:21
     * @instruction 使用辨识返回的数据的
     */
    public enum ReturnType {
        ERR, // 错误
        OK, // OK
        Exception
    }

}
