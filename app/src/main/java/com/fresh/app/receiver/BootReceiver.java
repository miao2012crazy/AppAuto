package com.fresh.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.fresh.app.MainActivity;

/**
 * Created by mr.miao on 2018/4/27.
 */

public class BootReceiver extends BroadcastReceiver {
    public static final String UPDATE_ACTION = "android.intent.action.PACKAGE_REPLACED";

    // APP包名ID
    public static final String PACKAGE_NAME = "com.fresh.app";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(UPDATE_ACTION)) {
            String packageName = intent.getData().getEncodedSchemeSpecificPart();
            if (packageName.equals(PACKAGE_NAME)) {

                Log.d("TAG", "更新安装成功....." + packageName);
                Toast.makeText(context, "更新安装成功" + packageName, Toast.LENGTH_LONG).show();

                // 重新启动APP
                Intent intentToStart = context.getPackageManager().getLaunchIntentForPackage(packageName);
                context.startActivity(intentToStart);
            }
        }
    }
}
