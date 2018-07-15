package com.fresh.app.bean;

/**
 * Created by mr.miao on 2018/7/15.
 */

public class ResultBean {


    /**
     * result : Successed
     */

    private String result;
    private String err_msg;

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "ResultBean{" +
                "result='" + result + '\'' +
                ", err_msg='" + err_msg + '\'' +
                '}';
    }
}
