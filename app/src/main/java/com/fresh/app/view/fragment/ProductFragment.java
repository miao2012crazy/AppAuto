package com.fresh.app.view.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fresh.app.R;
import com.fresh.app.base.BaseFragment;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.databinding.FragmentProductBinding;
import com.fresh.app.view.IProductView;
import com.fresh.app.viewmodel.ProductViewModel;

import java.util.List;

/**
 * Created by mr.miao on 2018/6/15.
 */

public class ProductFragment extends BaseFragment implements IProductView{

    private FragmentProductBinding bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false);
        return bind.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProductViewModel productViewModel = new ProductViewModel(this, bind);
    }


}
