package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fresh.app.R;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.constant.AppConstant;
import com.fresh.app.databinding.FragmentDetailBinding;
import com.fresh.app.view.IDetailView;
import com.fresh.app.viewmodel.ProductDetailViewModel;

/**
 * Created by mr.miao on 2018/6/15.
 */

public class ProductDetailFragment extends BaseFragment implements IDetailView {

    private FragmentDetailBinding bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        return bind.getRoot();


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductDetailViewModel productDetailViewModel = new ProductDetailViewModel(this, bind);
        productDetailViewModel.setProductId(AppConstant.product_id);
    }
}
