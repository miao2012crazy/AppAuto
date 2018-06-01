package com.fresh.app.view.viewimpl;

import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.fresh.app.R;
import com.fresh.app.applaction.CustomApplaction;
import com.fresh.app.base.BaseActivity;
import com.fresh.app.base.IBaseView;
import com.fresh.app.commonUtil.UIUtils;
import com.fresh.app.databinding.ActivityDetailBinding;
import com.fresh.app.view.IDetailView;
import com.fresh.app.viewmodel.ProductDetailViewModel;

/**
 * 商品详情
 * Created by mr.miao on 2018/4/23.
 */
public class MainProductDetailActivity extends BaseActivity implements IBaseView, IDetailView {

    private Button btnSelectAccuracy;
    private String product_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        product_id = getIntent().getStringExtra("product_id");

        ProductDetailViewModel productDetailViewModel = new ProductDetailViewModel(this, activityDetailBinding);
        productDetailViewModel.setProductId(product_id);
        btnSelectAccuracy = (Button) findViewById(R.id.btn_select_accuracy);
        btnSelectAccuracy.setOnClickListener(view -> showDialog());
    }

    /**
     * 展示选择对话框
     */
    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = UIUtils.inflate(R.layout.layout_dialog_select);
        builder.setView(inflate);
        final AlertDialog dialog = builder.create();
        TextView tv_option_1 = (TextView) inflate.findViewById(R.id.tv_option_1);
        TextView tv_option_2 = (TextView) inflate.findViewById(R.id.tv_option_2);
        TextView tv_option_3 = (TextView) inflate.findViewById(R.id.tv_option_3);
        tv_option_1.setOnClickListener(view -> {
            CustomApplaction.PRODUCT_ID=product_id;
            startActivityBase(PayeeActivity.class);
            dialog.dismiss();
        });

        tv_option_2.setOnClickListener(view -> {
            CustomApplaction.PRODUCT_ID=product_id;
            startActivityBase(PayeeActivity.class);
            dialog.dismiss();
        });

        tv_option_3.setOnClickListener(view -> {
            CustomApplaction.PRODUCT_ID=product_id;

            startActivityBase(PayeeActivity.class);
            dialog.dismiss();
        });

        dialog.show();
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();

        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.width = (int) (d.getWidth() * 1.0); // 宽度设置为屏幕的0.6
        params.alpha=0.8f;
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setAttributes(layoutParams);
        dialogWindow.setWindowAnimations(R.style.custon_dialog);
    }
}
