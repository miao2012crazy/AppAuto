package com.fresh.app.view;

import com.fresh.app.base.IBaseView;
import com.fresh.app.bean.QueryCardBean;

/**
 * Created by mr.miao on 2018/6/product_bg_0.
 */

public interface IQueryView extends IBaseView{

    /**
     * 显示卡内余额
     */
    void getCardDataSuccessed(QueryCardBean cardBean);

}
