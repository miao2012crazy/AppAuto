package com.fresh.app.bean;

import android.databinding.BaseObservable;

import com.fresh.app.R;
import com.fresh.app.base.BindingAdapterItem;

/**
 * Created by mr.miao on 2018/5/9.
 */

public class ProductItemType2Bean extends BaseObservable implements BindingAdapterItem {
    @Override
    public int getViewType() {
        return R.layout.item_main_owner;
    }

}
