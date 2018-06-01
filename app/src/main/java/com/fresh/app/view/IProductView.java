package com.fresh.app.view;

import com.fresh.app.base.BindingAdapterItem;
import com.fresh.app.base.IBaseView;
import com.fresh.app.bean.ProductItemBean;

import java.util.List;

/**
 * Created by mr.miao on 2018/5/7.
 */

public interface IProductView extends IBaseView{
    void getDataSuccessed( List<ProductItemBean> mainList);


}
