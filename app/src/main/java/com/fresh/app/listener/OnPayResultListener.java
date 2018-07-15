package com.fresh.app.listener;

/**
 * Created by mr.miao on 2018/7/15.
 */

public interface OnPayResultListener {

    void  onPaySuccessed();
    void onPayFailed(String err_msg);
}
