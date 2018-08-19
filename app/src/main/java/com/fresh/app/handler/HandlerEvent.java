package com.fresh.app.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.fresh.app.bean.MoneyBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.ReserveItemBean;
import com.fresh.app.bean.SocketBean;
import com.fresh.app.commonUtil.SocketUtil;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.httputil.HttpUrl;

import org.greenrobot.eventbus.EventBus;

import java.net.Socket;

/**
 * Created by mr.miao on 2018/5/9.
 */

public class HandlerEvent {

    private final Context mActivity;
    public HandlerEvent(Context context) {
        this.mActivity = context;
    }

    public void itemClick(View view, ProductItemBean productItemBean) {
        if (productItemBean == null) {
            return;
        }
        if (productItemBean.getDeviceProductStock() == 0) {
            UIUtils.showToast("库存不足");
            return;
        }
        AppConstant.PRODUCT_ITEM_BEAN=productItemBean;
        AppConstant.product_id = productItemBean.getProductId();
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
            Glide.with(imageView.getContext()).load(HttpUrl.getBaseImgUrl() + url).into(imageView);
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

    @BindingAdapter("bitmapUrlLocal")
    public static void loadBitmapLocal(ImageView imageView, Bitmap bitmap) {
        if (bitmap == null) {
            imageView.setImageBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.ic_launcher));
        } else {
            // Glide代替Volley
//            Glide.with(imageView.getContext()).load(HttpUrl.getBaseUrl() + url).into(imageView);
            imageView.setImageBitmap(bitmap);
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
                SocketUtil.sendDataToServer(socket, binary);
            }

            @Override
            public void onInitFailed(String errStr) {
                UIUtils.showToast("连接主机错误");
            }
        });
    }


    public void openFragment(View view, HomeBean homeBean) {
        UIUtils.showToast(homeBean.getId() + "");

        switch (homeBean.getId()) {
            case 0:
                //购买
                EventBus.getDefault().post(new MessageEvent(10065, "1"));
                break;

            case 1:
                //充值
                EventBus.getDefault().post(new MessageEvent(10065, "5"));


                break;

            case 2:
                //预定
                EventBus.getDefault().post(new MessageEvent(10065, "6"));

                break;

            case 3:
                //自提
                EventBus.getDefault().post(new MessageEvent(10088, ""));
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

    /**
     * 开始加工
     *
     * @param view
     * @param detailBean
     */
    public void startProcessing(View view, DetailBean detailBean) {
        UIUtils.showToast(detailBean.getBtn_name());
        if (AppConstant.RESERORDERBEAN != null) {
            EventBus.getDefault().post(new MessageEvent(10065, "4"));
            return;
        }

        //此处直接生成订单
        CustomApplaction.RICE_TYPE = detailBean.getId();
        CustomApplaction.PRODUCT_ID = detailBean.getProduct_id();
        //创建订单 返回支付二维码
        EventBus.getDefault().post(new MessageEvent(10066, detailBean.getProduct_id()));


    }


    public void fragmentReturn(View view) {
        int position = CustomApplaction.POSITION - 1;
        if (CustomApplaction.last_position != -1) {
            CustomApplaction.last_position = CustomApplaction.POSITION - 1;
        }
        EventBus.getDefault().post(new MessageEvent(10065, (position) + ""));
    }


    public void fragmentReturnMain(View view) {
        EventBus.getDefault().post(new MessageEvent(10065, 0 + ""));
    }

    public void fragmentReturnRecharge(View view) {
        CustomApplaction.POSITION=111;
        EventBus.getDefault().post(new MessageEvent(10065, 5 + ""));
    }

    /**
     * 单选
     *
     * @param view
     * @param moneyBean
     */
    public void singleItemClick(View view, MoneyBean moneyBean) {
        EventBus.getDefault().post(new MessageEvent(10099, moneyBean.getMoney_price()));
    }

    public void confirm_recharge(View view) {
        UIUtils.showToast("点击事件");
        EventBus.getDefault().post(new MessageEvent(10098, ""));
    }


    public void returnMain(View view) {
        UIUtils.showToast("点击出发");
        EventBus.getDefault().post(new MessageEvent(10065, "0"));
    }


    public void reserveItem(View view, ReserveItemBean reserveItemBean) {
        UIUtils.showToast("点击了" + reserveItemBean.getProductName());
        //选择数量提交数据到服务器
        EventBus.getDefault().post(new MessageEvent(10097, reserveItemBean.getProductId()));


    }

    public void queryHistory(View view){
        UIUtils.showToast("ceshiyixai");
    }

}
