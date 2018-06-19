package com.fresh.app.handlerevent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.bean.DebugBean;
import com.fresh.app.bean.DebugBean2;
import com.fresh.app.bean.DetailBean;
import com.fresh.app.bean.HomeBean;
import com.fresh.app.bean.PayeeBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.SocketBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.SocketUtil;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.httputil.HttpUrl;

import org.greenrobot.eventbus.EventBus;

import java.net.Socket;
import java.util.Arrays;

/**
 * Created by mr.miao on 2018/5/9.
 */

public class HandlerEvent {

    private final Context mActivity;
    private Bundle bundle = new Bundle();


    public HandlerEvent(Context context) {
        this.mActivity = context;
    }

    public void itemClick(View view, ProductItemBean item) {
        AppConstant.product_id = item.getProductId();
        EventBus.getDefault().post(new MessageEvent(10065, "2"));
    }

    public void toReserve(View view) {
        UIUtils.showToast("预约");
//        startActivityBase(view.getContext(),MainProductDetailActivity.class);
    }


    @BindingAdapter("imageUrl")
    public static void loadImg(ImageView imageView, String url) {
        if (url == null) {
            imageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            // Glide代替Volley
            Glide.with(imageView.getContext()).load(HttpUrl.getBaseUrl() + url).into(imageView);
        }

    }


    @BindingAdapter("imageUrlLocal")
    public static void loadImgLocal(ImageView imageView, int resId) {
        if (resId == 0) {
            imageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            // Glide代替Volley
//            Glide.with(imageView.getContext()).load(HttpUrl.getBaseUrl() + url).into(imageView);
            imageView.setImageResource(resId);
        }

    }

    /**
     * @param view
     * @param debugBean
     */
    public void start(View view, DebugBean debugBean) {
        SocketBean updateBit = SocketUtil.getUpdateBit(debugBean.getId(), true);
        getSocketAndSendData(updateBit);
    }


    public void stop(View view, DebugBean debugBean) {
        SocketBean updateBit = SocketUtil.getUpdateBit(debugBean.getId(), false);
        getSocketAndSendData(updateBit);
    }


    public void start(View view, DebugBean2 debugBean) {
        SocketBean updateBit = SocketUtil.getUpdateBit(debugBean.getId(), true);
        getSocketAndSendData(updateBit);
    }

    public void stop(View view, DebugBean2 debugBean) {
        SocketBean updateBit = SocketUtil.getUpdateBit(debugBean.getId(), false);
        getSocketAndSendData(updateBit);
    }


    private void getSocketAndSendData(SocketBean socketBean) {
        SocketUtil.getSocket(new SocketUtil.OnInitSocketListener() {
            @Override
            public void onInitSuccess(Socket socket) {
                byte[] binary = socketBean.getBinary("3", "5");
                LogUtils.e(Arrays.toString(binary));
                SocketUtil.sendDataToServer(socket, binary);
            }

            @Override
            public void onInitFailed(String errStr) {
                UIUtils.showToast("连接主机错误");
            }
        });
    }

    /**
     * 支付方式选择
     *
     * @param view item
     * @param item 支付方式
     */
    public void itemPayWayClick(View view, PayeeBean item) {
        switch (item.getItemtype()) {
            case 0:
                UIUtils.showToast("会员卡");
                EventBus.getDefault().post(new MessageEvent(10065,"4"));

                break;
            case product_bg_0:
                UIUtils.showToast("微信");
                EventBus.getDefault().post(new MessageEvent(10065,"4"));


                break;
            case 2:
                UIUtils.showToast("支付宝");
                EventBus.getDefault().post(new MessageEvent(10065,"4"));

                break;
            default:
                break;
        }


    }


    public void openFragment(View view, HomeBean homeBean) {
        UIUtils.showToast(homeBean.getId() + "");

        switch (homeBean.getId()) {
            case 0:
                //购买
                EventBus.getDefault().post(new MessageEvent(10065, "product_bg_0"));
                break;

            case product_bg_0:
                //充值
//                EventBus.getDefault().post(new MessageEvent(10065,"product_bg_0"));


                break;

            case 2:
                //预定


                break;

            case 3:
                //自提

                break;

        }


    }


    /**
     * 退出调试
     *
     * @param view
     */
    public void finishDebug(View view) {
        Activity mActivity = (Activity) this.mActivity;
        mActivity.finish();
    }

    /**
     * 复位
     *
     * @param view
     */
    public void startDebugController(View view) {
        CustomApplaction.socketbean = null;
        SocketBean updateBit = SocketUtil.getUpdateBit("99", true);
        getSocketAndSendData(updateBit);
    }


    //启动页面不带参数 直接启动
    private void startActivityBase(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    /**
     * 带参数启动
     *
     * @param context
     * @param clazz
     * @param bundle
     */
    private void startActivityBase(Context context, Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 开始加工
     *
     * @param view
     * @param detailBean
     */
    public void startProcessing(View view, DetailBean detailBean) {
        switch (detailBean.getId()) {
            case 0:
                //糙米
                EventBus.getDefault().post(new MessageEvent(10065, "3"));
                break;
            case product_bg_0:
                //胚芽米
                EventBus.getDefault().post(new MessageEvent(10065, "3"));
                break;
            case 2:
                //精磨米
                EventBus.getDefault().post(new MessageEvent(10065, "3"));
                break;


        }
    }


}
