package com.fresh.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseActivity;
import com.fresh.app.bean.DeviceBean;
import com.fresh.app.commonUtil.AudioUtils;
import com.fresh.app.commonUtil.FileUtil;
import com.fresh.app.commonUtil.FragmentFactory;
import com.fresh.app.commonUtil.GsonUtil;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.SharedPreferencesUtils;
import com.fresh.app.commonUtil.StringUtils;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.commonUtil.UpdateAppManager;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.constant.MessageEvent;
import com.fresh.app.constant.NetResponse;
import com.fresh.app.databinding.ActivityMainBinding;
import com.fresh.app.databinding.LayoutDialogSetDeviceIdBinding;
import com.fresh.app.databinding.LayoutDialogTakeGoodsBinding;
import com.fresh.app.databinding.LayoutErrorBinding;
import com.fresh.app.handler.HandlerEvent;
import com.fresh.app.httputil.HttpConstant;
import com.fresh.app.service.CustomIntentService;
import com.fresh.app.service.CustomPushService;
import com.fresh.app.service.TimeService;
import com.fresh.app.ui.CustomProgressBar;
import com.fresh.app.view.IMainView;
import com.fresh.app.view.viewimpl.ControllerActivity;
import com.fresh.app.view.viewimpl.DebugActivity;
import com.fresh.app.viewmodel.MainViewModel;
import com.igexin.sdk.PushManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.BreakIterator;

public class MainActivity extends BaseActivity implements IMainView {

    private ActivityMainBinding binding;
    private ImageView[] imgArr;
    private TextView[] tvArr;
    private AlertDialog dialog;
    private MainViewModel mainViewModel;
    private CustomProgressBar customProgressBar;
    private AlertDialog dialog1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setHandler(new HandlerEvent(this));
        mainViewModel = new MainViewModel(binding, this);
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
        openFragment(new MessageEvent(10065, "0"));
//        //定时器服务
        startService(new Intent(MainActivity.this, TimeService.class));
//        startActivityBase(DebugActivity.class);
        initDeviceId();
        EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_SPEAK,"欢迎使用云稻自助平台！"));
    }

    /**
     * 初始化设备id
     */
    private void initDeviceId() {
        try{
        String device_id = (String) SharedPreferencesUtils.getParam(UIUtils.getContext(), "DEVICE_ID", "");
        if (StringUtils.isEmpty(device_id)) {
            String path = Environment.getExternalStorageDirectory() + "/" + "AppDeviceId" + "/deviceId.txt";
            FileUtil.createIfNotExist(path);
             device_id = FileUtil.readString(path, "utf-8");
            setDeviceId();
        } else {
            AppConstant.DEVICE_ID = device_id;
        }
        }catch (Exception ex){
            EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_ERROR,ex.getMessage()));
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void openFragment(MessageEvent messageEvent) {
        switch (messageEvent.getCode()) {
            case 10065:
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
                int position = Integer.parseInt(messageEvent.getMessage());
                if (CustomApplaction.POSITION == position) {
                    return;
                }
                CustomApplaction.POSITION = position;
                setNavigation(position);
                fragmentTransaction.replace(R.id.fl_container, FragmentFactory.getFragment(position));
                fragmentTransaction.commit();
                break;
            case 10088:
                //自提
                showDialogForTakeGoods();
                EventBus.getDefault().post(new NetResponse(HttpConstant.STATE_SPEAK,"请输入4位提货码！"));

                break;
            case 1004:
                openFragment(new MessageEvent(10065, "7"));
                break;
            case 10909:
                startActivityBase(ControllerActivity.class);
                break;
            case 10023:
                if (dialog!=null){
                    dialog.dismiss();
                }
                break;

        }

    }


    /**
     * 设置导航条
     *
     * @param position
     */
    private void setNavigation(int position) {
        position -= 1;
        switch (position) {
            case -1:
                binding.bottom.llBottom.setVisibility(View.GONE);
                binding.btnReturn.setVisibility(View.GONE);
                return;
            case 0:
            case 1:
            case 2:
                binding.bottom.llBottom.setVisibility(View.VISIBLE);
                binding.btnReturn.setVisibility(View.VISIBLE);
                break;
            case 3:
                binding.btnReturn.setVisibility(View.GONE);
                if (AppConstant.RESERORDERBEAN == null) {
                    binding.bottom.llBottom.setVisibility(View.VISIBLE);
                } else {
                    binding.bottom.llBottom.setVisibility(View.GONE);
                }
                break;
            case 4:
                binding.bottom.llBottom.setVisibility(View.GONE);
                binding.btnReturn.setVisibility(View.GONE);
                break;
            case 5:
                binding.bottom.llBottom.setVisibility(View.GONE);
                break;

            default:
                binding.bottom.llBottom.setVisibility(View.GONE);
                binding.btnReturn.setVisibility(View.GONE);
                break;
        }

        if (CustomApplaction.last_position != -1 && position < 5) {
            imgArr[CustomApplaction.last_position].setBackgroundResource(R.drawable.ic_circle_normal);
            tvArr[CustomApplaction.last_position].setTextSize(30);
            tvArr[CustomApplaction.last_position].setTextColor(Color.parseColor("#e7e3e3"));
        }
        if (position < 5) {
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

        dialog.getWindow().setLayout(UIUtils.dip2px(800), UIUtils.dip2px(800));
        binding.btnConfirm.setOnClickListener(v -> {
            String code = binding.etCode.getText().toString();
            if (!TextUtils.isEmpty(code)) {
                mainViewModel.takeGoods(code, AppConstant.DEVICE_ID);
                dialog.dismiss();
            }

        });
        binding.btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reveiveData(NetResponse netResponse) {
        switch (netResponse.getTag()) {
            case HttpConstant.PROGRESS_DIALOG:
                if (customProgressBar == null) {
                    customProgressBar = new CustomProgressBar(this);
                }
                try {
                    customProgressBar.show();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                break;
            case HttpConstant.PROGRESS_DIALOG_DISMISS:
                if (customProgressBar != null) {
                    customProgressBar.dismiss();
                }

                break;
            case HttpConstant.STATE_ERROR:
                String tag = (String) netResponse.getData();
                showErrorDialog(tag);
                break;


            case HttpConstant.STATE_CHECK:
                //校验成功
                DeviceBean deviceBean = GsonUtil.GsonToBean((String) netResponse.getData(), DeviceBean.class);
                if (deviceBean != null) {
                    SharedPreferencesUtils.setParam(UIUtils.getContext(), "DEVICE_ID", deviceBean.getDeviceId());
                }
                if (dialog1 != null) {
                    dialog1.dismiss();
                }
                break;

            case HttpConstant.STATE_SPEAK:
                CustomApplaction.getExecutorService().execute(() -> {
                    String data = (String) netResponse.getData();
                    AudioUtils.getInstance().speakText(data);
                });

                break;
        }
    }


    public void showErrorDialog(String message) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutErrorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_error, null, false);
        builder.setView(binding.getRoot());
        dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding.tvErrorContent.setText(message);
        binding.btnConfirm.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }

    public void setDeviceId() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutDialogSetDeviceIdBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_dialog_set_device_id, null, false);
        builder.setView(binding.getRoot());
        dialog1 = builder.create();
        dialog1.show();
        dialog1.setCanceledOnTouchOutside(false);
        Window window = dialog1.getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.getWindow().setLayout(UIUtils.dip2px(800), UIUtils.dip2px(800));
        binding.btnConfirm.setOnClickListener(v -> {
            String device_id = binding.etCode.getText().toString();
            //设备id验证
            mainViewModel.checkDeviceId(device_id);
        });
    }

    /**
     * 处理服务器推送消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveDataForNet(NetResponse netResponse){
        switch (netResponse.getTag()){
            case HttpConstant.STATE_CLIENT_BIND:
                //个推clientid 绑定
                String client_id = (String)netResponse.getData();
                mainViewModel.bindClientId(client_id);
                break;
            case HttpConstant.STATE_BIND:
                //绑定成功
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
//        initDeviceId();
        PushManager.getInstance().initialize(this.getApplicationContext(), CustomPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), CustomIntentService.class);
        UpdateAppManager updateAppManager = new UpdateAppManager(this);
        updateAppManager.checkVersion();
    }
}