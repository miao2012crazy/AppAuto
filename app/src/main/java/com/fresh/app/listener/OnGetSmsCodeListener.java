package com.fresh.app.listener;

/**
 * Created by mr.miao on 2018/7/13.
 */

public interface OnGetSmsCodeListener {
    void getSmsCodeSuccessed(String msg_id);
    void getSmsCodeFailed(String err_msg);
}
