package com.fresh.app.listener;

import com.fresh.app.bean.CardHistoryBean;
import com.fresh.app.bean.QueryCardBean;

/**
 * Created by mr.miao on 2018/7/13.
 */

public interface OnLoadCardInfoListener {
    void onSuccessed(QueryCardBean queryCardBean);
    void onFailed(String err_msg);
}
