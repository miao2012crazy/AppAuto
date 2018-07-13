package com.fresh.app.listener;

import com.fresh.app.bean.ReserOrderBean;

/**
 * Created by mr.miao on 2018/7/12.
 */

public interface OnLoadReserveOrderListener {
    void onSuccessed(ReserOrderBean reserOrderBean);
    void onFailed(String msg);
}
