package com.fresh.app.view.viewimpl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseActivity;
import com.fresh.app.base.BindingAdapter;
import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.bean.PayeeBean;
import com.fresh.app.bean.ProductDetailBean;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.databinding.ActivityPayeeBinding;
import com.fresh.app.databinding.LayoutDialogPayBinding;
import com.fresh.app.handlerevent.HandlerEvent;
import com.fresh.app.view.IPayeeView;
import com.fresh.app.viewmodel.PayeeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付
 * Created by mr.miao on 2018/4/24.
 */
public class PayeeActivity extends BaseActivity implements IPayeeView {
    private int[] image_pay_way = {R.mipmap.ic_huiyuan, R.mipmap.ic_alipay, R.mipmap.ic_wechat};
    //绑定
    private ActivityPayeeBinding payeeBinding;
    private RecyclerView recyclerView;
    private BindingAdapter adapter;
    private HandlerEvent handler;
    private PayeeViewModel payeeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payee);
        payeeBinding = DataBindingUtil.setContentView(this, R.layout.activity_payee);
        payeeViewModel = new PayeeViewModel(this, payeeBinding);
        handler = new HandlerEvent(this);
        payeeBinding.setHandler(handler);
        payeeBinding.setPresenter(new Presenter());

        recyclerView = payeeBinding.layoutList.recyclerList;
        adapter = new BindingAdapter();
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<BindingAdapterItem> mList = new ArrayList<>();
        PayeeBean payeeBean = new PayeeBean("云稻会员卡", R.mipmap.ic_huiyuan, false, 0);
        PayeeBean payeeBean1 = new PayeeBean("微信扫码", R.mipmap.ic_wechat, false, 1);
        PayeeBean payeeBean2 = new PayeeBean("支付宝扫码", R.mipmap.ic_alipay, false, 2);
        mList.add(payeeBean);
        mList.add(payeeBean1);
        mList.add(payeeBean2);
        adapter.setItems(mList);
        ProductDetailBean product_detail_bean = CustomApplaction.product_detail_bean;
        payeeBinding.order.setItem(product_detail_bean);
    }

    /**
     * 展示选择对话框
     *
     * @param last_position
     */
    @Override
    public void showDialogForPay(int last_position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View inflate = UIUtils.inflate(R.layout.layout_dialog_pay);
        LayoutDialogPayBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_dialog_pay,null, false);
        builder.setView(binding.getRoot());
        binding.setHandler(handler);
        final AlertDialog dialog = builder.create();
        ImageView iv_pay = binding.ivPay;
        TextView tv_option_2 = binding.tvOption2;

        switch (last_position) {
            case 0:
                tv_option_2.setText("请刷会员卡！");
                break;
            case 1:
                tv_option_2.setText("请使用微信扫描下方二维码！");
                break;
            case 2:
                tv_option_2.setText("请使用支付宝扫描下方二维码！");
                break;
        }
        iv_pay.setBackgroundResource(image_pay_way[last_position]);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                startActivityBase(MachiningActivity.class);
            }
        });
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        assert dialogWindow != null;
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
        params.height = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(layoutParams);
        dialogWindow.setWindowAnimations(R.style.custon_dialog);
    }


    /**
     * 选择支付事件绑定
     */
    public class Presenter{
        /**
         * 确认支付
         * @param view
         */
        public void itemPayConfirm(View view){
            PayeeBean lastItem = CustomApplaction.lastItem;
            if (lastItem==null){
                UIUtils.showToast("还没有选择支付方式");
                return;
            }
            payeeViewModel.creatOrder(CustomApplaction.PRODUCT_ID);

        }

    }


}
