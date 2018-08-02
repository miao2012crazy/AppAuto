package com.fresh.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by mr.miao on 2018/7/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResultBean {


    /**
     * result : true
     * msg : 数据获取成功
     * data : {"id":1,"uid":"123412132312","uname":"miao","upassword":"123456","regDate":""}
     * err_msg :
     */

    private boolean result;
    private String msg;
    private String err_msg;
    private List<ProductItemBean> productItemBean;
    /**
     * qr : {"wechat_url":"weixin://wxpay/bizpayurl?pr=MrpgWro","order_id":"19b876668379417ea7a5a580024e08c9"}
     */

    private QrBean qr;


    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ProductItemBean> getProductItemBean() {
        return productItemBean;
    }

    public void setProductItemBean(List<ProductItemBean> productItemBean) {
        this.productItemBean = productItemBean;
    }

    public QrBean getQr() {
        return qr;
    }

    public void setQr(QrBean qr) {
        this.qr = qr;
    }

}
