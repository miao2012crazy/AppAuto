package com.fresh.app.base;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import com.fresh.app.commonUtil.UIUtils;

/**
 * Created by mr.miao on 2018/6/1.
 */

public class BaseFragment extends Fragment implements IBaseView{

    protected Bundle bundle=new Bundle();

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
