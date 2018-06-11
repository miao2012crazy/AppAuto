package com.fresh.app;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import com.fresh.app.base.BaseActivity;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.base.IBaseView;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.ProductItemType2Bean;
import com.fresh.app.bean.SocketBean;
import com.fresh.app.commonUtil.LogUtils;
import com.fresh.app.commonUtil.SocketUtil;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.databinding.ActivityMainBinding;
import com.fresh.app.handlerevent.HandlerEvent;
import com.fresh.app.service.TimeService;
import com.fresh.app.view.IProductView;
import com.fresh.app.view.viewimpl.DebugActivity;
import com.fresh.app.viewmodel.ProductViewModel;

import org.greenrobot.eventbus.EventBus;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity implements IProductView, IBaseView {

    private RecyclerView recyclerList;
    private BindingAdapter adapter;
    private List<BindingAdapterItem> mainList;
    private Socket mSocket;
    private ActivityMainBinding binding;
    private ProductViewModel productViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(new HandlerEvent(this));
        productViewModel = new ProductViewModel(this, binding);
        initRecyclerList();
//        SocketUtil.getSocket(new SocketUtil.OnInitSocketListener() {
//            @Override
//            public void onInitSuccess(Socket socket) {
//                SocketUtil.getDataFromServer(socket);
//                mSocket = socket;
//            }
//
//            @Override
//            public void onInitFailed(String errStr) {
//
//            }
//        });
//        SocketBean updateBit = SocketUtil.getUpdateBit("00", true);
//        getSocketAndSendData(updateBit);
//        binding.tvTest.setOnClickListener(view -> {
//            if (mSocket != null) {
//                SocketUtil.sendDataToServer(mSocket, "TD02的手机");
//            }
//        });
        startActivityBase(DebugActivity.class);


//        //定时器服务
        startService(new Intent(MainActivity.this, TimeService.class));
    }


    private void getSocketAndSendData(SocketBean socketBean) {
        SocketUtil.getSocket(new SocketUtil.OnInitSocketListener() {
            @Override
            public void onInitSuccess(Socket socket) {
                byte[] binary = socketBean.getBinary("30");
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
     * 初始化列表
     */
    private void initRecyclerList() {
        mainList = new ArrayList<>();
        recyclerList = binding.layoutList.recyclerList;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
//        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
//        recyclerList.setLayoutManager(layoutmanager);
        adapter = new BindingAdapter();
        recyclerList.setLayoutManager(gridLayoutManager);
        recyclerList.setAdapter(adapter);

    }

    @Override
    public void getDataSuccessed(List<ProductItemBean> list) {
        mainList.clear();
        mainList.addAll(list);
        ProductItemType2Bean productItemType2Bean = new ProductItemType2Bean();
        mainList.add(productItemType2Bean);
        adapter.setItems(mainList);
    }


    /**
     * 初始化语音
     */
    private void initTTS() {

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


//    /**
//     * 展示选择对话框
//     */
//    @Override
//    public void showDialogForBalance() {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutDialogBalanceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_dialog_balance, null, false);
//        builder.setView(binding.getRoot());
//        final AlertDialog dialog = builder.create();
//        dialog.show();
//        Window dialogWindow = dialog.getWindow();
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        assert dialogWindow != null;
//        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
//        WindowManager m = getWindowManager();
//        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
//        WindowManager.LayoutParams params = dialogWindow.getAttributes();
//        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
//        params.height = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
//        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setAttributes(layoutParams);
//        dialogWindow.setWindowAnimations(R.style.custon_dialog);
//    }


    @Override
    protected void onResume() {
        super.onResume();
//       productViewModel.getData(new MessageEvent(1003,""));

    }
}