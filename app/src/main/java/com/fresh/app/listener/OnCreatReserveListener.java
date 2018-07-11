package com.fresh.app.listener;

import com.fresh.app.bean.QRBean;
import com.fresh.app.bean.ReserOrderBean;

/**
 * Created by mr.miao on 2018/7/4.
 */

public interface OnCreatReserveListener {
    void onCreatSuccessed(QRBean qrBean);
    void onCreatFailed(String err_msg);
}
