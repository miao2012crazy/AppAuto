package com.fresh.app.httputil;

import com.fresh.app.commonUtil.UIUtils;
import com.tamic.novate.Novate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mr.miao on 2018/7/30.
 */

public class NovateUtil {

    private static Novate novate;

    public static Novate getInstance() {
        if (novate != null) {
            return novate;
        }
        //连接时间 可以忽略
        Map<String, String> headers = new HashMap<>();
        headers.put("Cache-Control","max-age=1000*60");
        novate = new Novate.Builder(UIUtils.getContext())
                .baseUrl(HttpUrl.getBaseUrl())
//                .addHeader(headers)
                .addCache(true)
                .connectTimeout(8)  //连接时间 可以忽略
                .addLog(true)
                .build();
        return novate;
    }


}
