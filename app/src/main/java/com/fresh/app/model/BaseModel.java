package com.fresh.app.model;

import android.util.Log;

import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.httputil.NovateUtil;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Throwable;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;

/**
 * Created by mr.miao on 2018/7/30.
 */

public class BaseModel {
    /**
     * post请求参数集合  使用前请清空
     */
    protected HashMap<String,Object> map=new HashMap<>();

    protected void getDataFromNet(String tag, String url, HashMap<String, Object> map){

        NovateUtil.getInstance().post(url, map, new BaseSubscriber<ResponseBody>() {
            @Override
            public void onStart() {
                super.onStart();
                EventBus.getDefault().post(new NetResponse(HttpConstant.PROGRESS_DIALOG,""));
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                EventBus.getDefault().post(new NetResponse(HttpConstant.PROGRESS_DIALOG_DISMISS,""));
            }

            @Override
            public void onError(Throwable e) {
                EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR,"网络连接失败！"));
            }

            @Override
            public void onNext(ResponseBody responseBody) {

                try {
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    boolean result = jsonObject.getBoolean("result");
                    if (result){
                        String data = jsonObject.getString("data");
                        EventBus.getDefault().post(new NetResponse(tag,data));
                    }else{
                        EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR,jsonObject.getString("msg")));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
