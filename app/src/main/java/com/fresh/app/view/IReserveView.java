package com.fresh.app.view;

import com.fresh.app.bean.QrBean;

/**
 * Created by mr.miao on 2018/6/28.
 */

public interface IReserveView {
    void initPayee(QrBean qrBean);
    void getPayResult(String order_id);
    void showDialogForPay(int position);

}
