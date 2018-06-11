package com.fresh.app.applaction;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.util.Log;

import com.fresh.app.bean.PayeeBean;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.SocketBean;
import com.fresh.app.commonUtil.SerialPortUtil;
import com.fresh.app.commonUtil.SocketUtil;
import com.fresh.app.commonUtil.StringUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.IConstant;
import com.fresh.app.gen.DaoMaster;
import com.fresh.app.gen.DaoSession;
import com.iflytek.cloud.SpeechUtility;

import org.greenrobot.eventbus.EventBus;

import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by mr.miao on 2018/4/23.
 */

public class CustomApplaction extends Application {
    //订单id
    public static String ORDER_ID = "";
    //读卡器默认状态 默认状态 查询  state==1 支付
    public static int state=0;
    public static String PRODUCT_ID = "";
    private static Context context;
    private static Handler handler;
    private static Thread mainThread;
    private static int mainThreadId;
    private static CustomApplaction app;
    public static PayeeBean lastItem;
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);
    public static ProductItemBean product_detail_bean;
    public static SocketBean socketbean=null;
    //数据库相关
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //在此方法中需要获取handler对象,上下文环境
        context = getApplicationContext();
        //准备handler对象
        handler = new Handler();
        //hanlder应用场景(子线程往主线程中发送message)

        //获取主线程的对象,WLBApplication的onCreate运行在主线程中的代码
        mainThread = Thread.currentThread();
        //获取主线程(当前线程)id方法
        mainThreadId = android.os.Process.myTid();
        mMainThreadHandler = new Handler();
        //语音初始化
        SpeechUtility.createUtility(getApplicationContext(), "appid=5ae91f37");

        SocketUtil.initSocket(new SocketUtil.OnInitSocketListener() {
            @Override
            public void onInitSuccess(Socket socket) {
                SocketUtil.getDataFromServer(socket);
            }

            @Override
            public void onInitFailed(String errStr) {
//                UIUtils.showToast(errStr);
            }
        });

        //获取ip


        setDatabase();

//        init();


    }

    /**
     * 初始化 读卡机 打开串口 打开输入输出流
     */
    private void init() {
//        String cmd = IConstant.default_state + StringUtils.xor(IConstant.default_state);
        SerialPortUtil.openSrialPort();
//        SerialPortUtil.sendSerialPort(cmd);
    }

    /**
     * 设置数据库
     */
    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }


    public static CustomApplaction getInstances(){
        return app;
    }



    /**
     *  获取上下文对象
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取handler对象
     * @return
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     * @return
     */
    public static Thread getMainThread() {
        return mainThread;
    }

    /**
     * 获取主线程id
     * @return
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }

    private static Handler mMainThreadHandler = null;

    /**
     * 获取主线程handler对象
     * @return
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取线程池  三线程
     * @return
     */
    public static ExecutorService getExecutorService(){
        return executorService;
    }


}
