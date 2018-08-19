package com.fresh.app.view;

import com.fresh.app.bean.QueryCardBean;

/**
 * Created by mr.miao on 2018/6/1.
 */

public interface IQueryView{

    /**
     * 显示卡内余额
     */
    void getCardDataSuccessed(QueryCardBean cardBean);

    void startTimer();

    void setlayout(int position);
}
