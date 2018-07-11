package com.fresh.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseActivity;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.base.IBaseView;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.ProductItemType2Bean;
import com.fresh.app.commonUtil.FragmentFactory;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.ActivityMainBinding;
import com.fresh.app.databinding.LayoutDialogTakeGoodsBinding;
import com.fresh.app.databinding.LayoutPaySuccessedBinding;
import com.fresh.app.handlerevent.HandlerEvent;
import com.fresh.app.service.TimeService;
import com.fresh.app.view.IMainView;
import com.fresh.app.view.IProductView;
import com.fresh.app.view.viewimpl.DebugActivity;
import com.fresh.app.viewmodel.ProductViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IBaseView,IMainView {

    private ActivityMainBinding binding;

    private ImageView[] imgArr;
    private TextView[] tvArr;
    private AlertDialog dialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(new HandlerEvent(this));
//        binding.bottom.
        ImageView iv0 = binding.bottom.iv0;
        ImageView iv1 = binding.bottom.iv1;
        ImageView iv2 = binding.bottom.iv2;
        ImageView iv3 = binding.bottom.iv3;
        ImageView iv4 = binding.bottom.iv4;

        TextView tv0 = binding.bottom.tv0;
        TextView tv1 = binding.bottom.tv1;
        TextView tv2 = binding.bottom.tv2;
        TextView tv3 = binding.bottom.tv3;
        TextView tv4 = binding.bottom.tv4;


        imgArr = new ImageView[]{iv0, iv1, iv2, iv3, iv4};
        tvArr = new TextView[]{tv0, tv1, tv2, tv3, tv4};
//        imgArr={binding.bottom.,binding.bottom.iv1};
        openFragment(new MessageEvent(10065, "0"));

//        startActivityBase(DebugActivity.class);
//        //定时器服务
        startService(new Intent(MainActivity.this, TimeService.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void openFragment(MessageEvent messageEvent) {
        switch (messageEvent.getCode()) {
            case 10065:
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                int position = Integer.parseInt(messageEvent.getMessage());
                CustomApplaction.POSITION = position;
                setNavigation(position);
                fragmentTransaction.replace(R.id.fl_container, FragmentFactory.getFragment(position));
                fragmentTransaction.commit();
                break;
            case 10088:
                //自提
                showDialogForTakeGoods();
                break;
        }

    }



    /**
     * 设置导航条
     *
     * @param position
     */
    private void setNavigation(int position) {
        LogUtils.e(position + "");
        position -= 1;
        switch (position) {
            case -1:
                binding.bottom.llBottom.setVisibility(View.GONE);
                binding.btnReturn.setVisibility(View.GONE);
                return;
            case 0:
            case 1:
            case 2:
            case 3:
                binding.bottom.llBottom.setVisibility(View.VISIBLE);
                binding.btnReturn.setVisibility(View.VISIBLE);
                break;
            case 4:
                binding.bottom.llBottom.setVisibility(View.GONE);
                binding.btnReturn.setVisibility(View.GONE);
                break;
            case 5:
                binding.bottom.llBottom.setVisibility(View.GONE);
                break;
            default:
                break;
        }

        if (CustomApplaction.last_position != -1&&position<5) {
            imgArr[CustomApplaction.last_position].setBackgroundResource(R.drawable.ic_circle_normal);
            tvArr[CustomApplaction.last_position].setTextSize(30);
            tvArr[CustomApplaction.last_position].setTextColor(Color.parseColor("#e7e3e3"));
        }
        if (position<5){
            imgArr[position].setBackgroundResource(R.drawable.ic_circle_checked);
            tvArr[position].setTextSize(34);
            tvArr[position].setTextColor(Color.parseColor("#ffffff"));
            CustomApplaction.last_position = position;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //屏蔽返回键、菜单键、home键、音量键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_HOME:
            case KeyEvent.KEYCODE_MENU:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_MUTE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void showDialogForTakeGoods() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutDialogTakeGoodsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_dialog_take_goods, null, false);
        builder.setView(binding.getRoot());
        dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(UIUtils.dip2px(700), UIUtils.dip2px(500));

    }
}