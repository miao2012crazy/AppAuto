package com.fresh.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fresh.app.commonUtil.UIUtils;

/**
 * Created by mr.miao on 2018/4/23.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView{

    protected Bundle bundle=new Bundle();


    //启动页面不带参数 直接启动
    protected void startActivityBase(Class clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    public void loadFailure(String message) {
        UIUtils.showToast("加载失败！");
    }

    @Override
    public void loadStart() {
        UIUtils.showToast("加载失败！");
    }

    @Override
    public void loadComplete() {
        UIUtils.showToast("加载失败！");
    }




    /**
     * 带参数启动
     * @param context
     * @param clazz
     * @param bundle
     */
    private void startActivityBase(Context context, Class clazz, Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
