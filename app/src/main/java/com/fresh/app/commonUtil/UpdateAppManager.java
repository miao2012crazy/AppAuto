package com.fresh.app.commonUtil;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AlertDialog;


import com.fresh.app.bean.VersionBean;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.httputil.HttpUtils;
import com.fresh.app.receiver.PackageUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/28.
 */
public class UpdateAppManager {
    // 外存sdcard存放路径
    private static final String FILE_PATH = Environment.getExternalStorageDirectory() + "/" + "AutoUpdate" + "/";
    // 下载应用存放全路径
    private static final String FILE_NAME = FILE_PATH + "AutoUpdate.apk";
    // 准备安装新版本应用标记
    private static final int INSTALL_TOKEN = 1;
    //Log日志打印标签
    private static final String TAG = "Update_log";
    private Context context;
    //获取新版APK的默认地址
    public static String apk_path_base = HttpUrl.getBaseUrl()+"static/version/";
    public static String apk_path = "";
    // 下载应用的进度条
    private ProgressDialog progressDialog;


    public UpdateAppManager(Context context) {
        this.context = context;
    }


    public  void checkVersion(){
        int versionCode = UIUtils.getVersionCode();

        HttpUtils.getVersion(versionCode+"").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<VersionBean>() {
                    @Override
                    public void onNext(VersionBean versionBean) {
                        if (versionBean.isResult()){
                            apk_path=apk_path_base+versionBean.getFile_path();
                            //需要升级 下载升级包
                            showDownloadDialog();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("服务器"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    /**
     * 显示下载进度对话框
     */
    public void showDownloadDialog() {

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("正在升级...");

        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCanceledOnTouchOutside(false);
        new downloadAsyncTask().execute();
    }

    /**
     * 下载新版本应用
     */
    @SuppressLint("StaticFieldLeak")
    private class downloadAsyncTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected Integer doInBackground(Void... params) {


            URL url;
            HttpURLConnection connection = null;
            InputStream in = null;
            FileOutputStream out = null;
            try {
                url = new URL(apk_path);
                connection = (HttpURLConnection) url.openConnection();

                in = connection.getInputStream();
                long fileLength = connection.getContentLength();
                File file_path = new File(FILE_PATH);
                if (!file_path.exists()) {
                    file_path.mkdir();
                }

                out = new FileOutputStream(new File(FILE_NAME));//为指定的文件路径创建文件输出流
                byte[] buffer = new byte[1024 * 1024];
                int len = 0;
                long readLength = 0;


                while ((len = in.read(buffer)) != -1) {

                    out.write(buffer, 0, len);//从buffer的第0位开始读取len长度的字节到输出流
                    readLength += len;

                    int curProgress = (int) (((float) readLength / fileLength) * 100);


                    publishProgress(curProgress);

                    if (readLength >= fileLength) {

                        break;
                    }
                }

                out.flush();
                return INSTALL_TOKEN;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (connection != null) {
                    connection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            progressDialog.dismiss();//关闭进度条

            int resultCode = PackageUtils.installSilent(context, FILE_NAME);
            if (resultCode != PackageUtils.INSTALL_SUCCEEDED) {
                UIUtils.showToast("升级失败");
            }


            //安装应用
//            installApp();
        }
    }

    /**
     * 安装新版本应用
     */
    private void installApp() {
        File appFile = new File(FILE_NAME);
        //烧应用
        boolean install = ApkController.install(appFile.getPath(), UIUtils.getContext());


        if (install){
            UIUtils.showToast("已安装！");
        }else{
            UIUtils.showToast("未安装！");
        }
    }
}