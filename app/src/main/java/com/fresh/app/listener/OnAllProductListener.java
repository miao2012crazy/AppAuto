package com.fresh.app.listener;

import com.fresh.app.bean.ProductBean;
import com.fresh.app.bean.ProductItemBean;
import com.fresh.app.bean.ReserveBean;

import java.util.List;

/**
 * Created by mr.miao on 2018/6/28.
 */

public interface OnAllProductListener {
    void onSuccessed(ReserveBean reserveBean);
    void onFailed(String err_msg);
}
