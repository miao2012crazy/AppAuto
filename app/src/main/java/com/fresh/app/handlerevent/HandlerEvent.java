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
import com.fresh.app.bean.PayeeBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.LayoutDialogPayBinding;
import com.fresh.app.httputil.HttpUrl;
import com.fresh.app.view.viewimpl.DebugControlActivity;
import com.fresh.app.view.viewimpl.MachiningActivity;
import com.fresh.app.view.viewimpl.MainProductDetailActivity;

import org.greenrobot.eventbus.EventBus;

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
     * 支付方式选择
     *
     * @param view item
     * @param item 支付方式
     */
    public void itemPayWayClick(View view, PayeeBean item) {
        PayeeBean lastItem = CustomApplaction.lastItem;
       if (lastItem==null){
           item.setVisiable(true);
           CustomApplaction.lastItem=item;
       }else{
           if (lastItem==item){
               if (item.getVisiable()){
                   item.setVisiable(false);
                   CustomApplaction.lastItem=null;
               }else{
                   item.setVisiable(true);
                   CustomApplaction.lastItem=item;
               }

           }else{
               lastItem.setVisiable(false);
               item.setVisiable(true);
               CustomApplaction.lastItem=item;
           }
       }

    }

    /**
     * 退出调试
     * @param view
     */
    public void finishDebug(View view){
        Activity mActivity = (Activity) this.mActivity;
        mActivity.finish();
    }
   /**
     * 退出调试
     * @param view
     */
   public void startDebugController(View view){
            startActivityBase(mActivity, DebugControlActivity.class);
    }




    //启动页面不带参数 直接启动
    private void startActivityBase(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    /**
     * 带参数启动
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
