package com.fresh.app.applaction;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;

import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.FileNameGenerator;
import com.fresh.app.MainActivity;
import com.fresh.app.bean.PayeeBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.QrBean;
import com.fresh.app.bean.SocketBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.SocketUtil;
import com.fresh.app.commonUtil.StringUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.gen.DaoMaster;
import com.fresh.app.gen.DaoSession;
import com.iflytek.cloud.SpeechUtility;

import java.io.File;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by mr.miao on 2018/4/23.
 * 自定义applaction
 */
public class CustomApplaction extends Application implements Thread.UncaughtExceptionHandler{
    public static int RESULT_CODE = 0;
    public static QrBean QR_BEAN = null;
    public static int MONEY_CHECK_POSITION = -1;
    public static boolean ISRESULT = false;
    public static int RICE_TYPE = -1;
    public static int POSITION = -1;
    //订单id
    public static String ORDER_ID = "";
    public static String PRODUCT_ID = "";
    public static String MEMBER_ID = "";
    private static Context context;
    private static Handler handler;
    private static Thread mainThread;
    private static int mainThreadId;
    private static CustomApplaction app;
    public static PayeeBean lastItem;
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);
    public static ProductItemBean product_detail_bean;
    public static SocketBean socketbean = null;
    public static int last_position = -1;
    //数据库相关
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        //设置Thread Exception Handler
        Thread.setDefaultUncaughtExceptionHandler(this);
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
                LogUtils.e("链接主机错误");
            }
        });
        //设置数据库
        setDatabase();
        //初始化读卡器状态
//        CardReaderManage.setCardReaderState(0);

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


    public static CustomApplaction getInstances() {
        return app;
    }


    /**
     * 获取上下文对象
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取handler对象
     *
     * @return
     */
    public static Handler getHandler() {
        return handler;
    }

    /**
     * 获取主线程
     *
     * @return
     */
    public static Thread getMainThread() {
        return mainThread;
    }

    /**
     * 获取主线程id
     *
     * @return
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }

    private static Handler mMainThreadHandler = null;

    /**
     * 获取主线程handler对象
     *
     * @return
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取线程池  三线程
     *
     * @return
     */
    public static ExecutorService getExecutorService() {
        return executorService;
    }


    /**
     * -------------------视频缓存配置——————————————————————
     */
    private HttpProxyCacheServer proxy;

    /**
     * 获取缓存器
     * @param context
     * @return
     */
    public static HttpProxyCacheServer getProxy(Context context) {
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        HttpProxyCacheServer httpProxyCacheServer = new HttpProxyCacheServer.Builder(this)
                .maxCacheFilesCount(100)
                .cacheDirectory(new File(UIUtils.getInnerSDCardPath() + "/video"))
                .fileNameGenerator(new CustomFileNameGenerator())
                .build();
        return httpProxyCacheServer;
    }

    private class CustomFileNameGenerator implements FileNameGenerator {
        @Override
        public String generate(String url) {
            Uri uri = Uri.parse(url);
            String[] dataForspild_2 =getDataForspild_2(uri.toString());
            if (dataForspild_2 != null) {
                String s = dataForspild_2[dataForspild_2.length - 1];
                return s;
            }
            return url;
        }
    }


    /**
     * 数据分割 /
     */
    public static String[] getDataForspild_2(String s) {
        String[] spo = null;
        try {
            spo = StringUtils.split(s, '/');
        } catch (Exception e) {
            return null;
        }
        return spo;
    }

    /**
     * -------------------视频缓存结束——————————————————————
     */

    /**
     * 异常处理相关
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        System.out.println("uncaughtException"+ex.getMessage());
        System.exit(0);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
