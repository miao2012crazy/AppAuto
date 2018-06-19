package com.fresh.app;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.fresh.app.base.BaseActivity;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.base.IBaseView;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.ProductItemType2Bean;
import com.fresh.app.commonUtil.FragmentFactory;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.databinding.ActivityMainBinding;
import com.fresh.app.handlerevent.HandlerEvent;
import com.fresh.app.service.TimeService;
import com.fresh.app.view.IProductView;
import com.fresh.app.view.viewimpl.DebugActivity;
import com.fresh.app.viewmodel.ProductViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IBaseView {

    private ActivityMainBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(new HandlerEvent(this));
        openFragment(new MessageEvent(10065,"0"));
//        startActivityBase(DebugActivity.class);
//        //定时器服务
        startService(new Intent(MainActivity.this, TimeService.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void openFragment(MessageEvent messageEvent){

        switch (messageEvent.getCode()){
            case 10065:
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_container, FragmentFactory.getFragment(Integer.parseInt(messageEvent.getMessage())));
                fragmentTransaction.commit();
                break;
            case 10066:
                //支付结果回传了

                break;
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
}