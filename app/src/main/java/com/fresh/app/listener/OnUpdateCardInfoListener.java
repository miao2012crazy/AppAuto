package com.fresh.app.listener;

/**
 * Created by mr.miao on 2018/7/13.
 */

public interface OnUpdateCardInfoListener {
    void OnUpdateCardSuccessed(boolean result);
    void OnUpdateCardFailed(String err_msg);


}
