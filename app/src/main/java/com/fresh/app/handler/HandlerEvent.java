package com.fresh.app.handler;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.bean.DebugBean;
import com.fresh.app.bean.DebugBean2;
import com.fresh.app.bean.HomeBean;
import com.fresh.app.bean.MoneyBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.ReserveItemBean;
import com.fresh.app.bean.SocketBean;
import com.fresh.app.commonUtil.SocketUtil;
import com.fresh.app.commonUtil.StringUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.IConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.httputil.HttpConstant;
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
                EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR,"socket已连接"));
                if (!StringUtils.isEmpty(IConstant.PRESS1)&&!StringUtils.isEmpty(IConstant.PRESS2)&&!StringUtils.isEmpty(IConstant.PRESS3)){
                    byte[] binary = socketBean.getBinary(IConstant.PRESS1, IConstant.PRESS1_CHA, IConstant.PRESS2, IConstant.PRESS2_CHA, IConstant.PRESS3, IConstant.PRESS3_CHA);
                    SocketUtil.sendDataToServer(socket, binary);
                }else{
                    byte[] binary = socketBean.getBinary("90", "20", "90", "2", "90", "2");
                    SocketUtil.sendDataToServer(socket, binary);
                }
            }

            @Override
            public void onInitFailed(String errStr) {
                EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR,"socket连接失败"));
                UIUtils.showToast("socket连接主机错误");
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
     * @param productItemBean
     */
    public void startProcessing(View view, ProductItemBean productItemBean) {
        if (AppConstant.RESERORDERBEAN != null) {
            EventBus.getDefault().post(new MessageEvent(10065, "4"));
            return;
        }

//        //此处直接生成订单
//        CustomApplaction.PRODUCT_ID = productItemBean.getProductId();
//        //创建订单 返回支付二维码
//        EventBus.getDefault().post(new MessageEvent(10066, CustomApplaction.PRODUCT_ID));
        EventBus.getDefault().post(new MessageEvent(10065, "3"));

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
        EventBus.getDefault().post(new MessageEvent(10065, "0"));
    }

    public void returnMain() {
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
