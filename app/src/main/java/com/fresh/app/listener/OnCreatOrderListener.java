package com.fresh.app.listener;

import com.fresh.app.bean.QRBean;

/**
 * Created by mr.miao on 2018/6/25.
 */

public interface OnCreatOrderListener {
    void onCreatOrderSuccessed(QRBean qrBean);
    void onCreatOrderFailed(String err_msg);
}
