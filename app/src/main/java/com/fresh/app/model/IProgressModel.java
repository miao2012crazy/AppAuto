package com.fresh.app.model;

/**
 * Created by mr.miao on 2018/7/15.
 */

public interface IProgressModel {

    void getRiceBucket(String deviceid);

    void updateRiceBucket(String deviceid, String riceBucketId);
}
