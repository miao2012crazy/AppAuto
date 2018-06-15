package com.fresh.app.handlerevent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.DebugBean;
import com.fresh.app.bean.DebugBean2;
import com.fresh.app.bean.HomeBean;
import com.fresh.app.bean.PayeeBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.SocketBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.SocketUtil;
import com.fresh.app.commonUtil.StringUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.LayoutDialogPayBinding;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.view.viewimpl.DebugControlActivity;
import com.fresh.app.view.viewimpl.MachiningActivity;
import com.fresh.app.view.viewimpl.MainProductDetailActivity;

import org.greenrobot.eventbus.EventBus;

import java.net.Socket;
import java.util.Arrays;
import java.util.List;

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
//        view.getContext().startActivity(new Intent(view.getContext(), MainProductDetailActivity.class));
        bundle.clear();
        bundle.putString("product_id", item.getProductId());
        startActivityBase(view.getContext(), MainProductDetailActivity.class, bundle);
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
                byte[] binary = socketBean.getBinary("3","5");
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
        PayeeBean lastItem = CustomApplaction.lastItem;
        if (lastItem == null) {
            item.setVisiable(true);
            CustomApplaction.lastItem = item;
        } else {
            if (lastItem == item) {
                if (item.getVisiable()) {
                    item.setVisiable(false);
                    CustomApplaction.lastItem = null;
                } else {
                    item.setVisiable(true);
                    CustomApplaction.lastItem = item;
                }

            } else {
                lastItem.setVisiable(false);
                item.setVisiable(true);
                CustomApplaction.lastItem = item;
            }
        }

    }


    public void  openFragment(View view, HomeBean homeBean){
        UIUtils.showToast(homeBean.getId()+"");

        switch (homeBean.getId()){
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
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
        CustomApplaction.socketbean=null;
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

}
