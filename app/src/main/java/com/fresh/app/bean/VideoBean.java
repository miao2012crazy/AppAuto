package com.fresh.app.bean;

import java.util.List;

/**
 * Created by mr.miao on 2018/7/14.
 */

public class VideoBean {


    /**
     * id : 1
     * videoName : 111111.mp4
     * videoState : 1
     * regDate : 111
     * isHome : 0
     * deviceId : 20180515_01
     */

    private int id;
    private String videoName;
    private int videoState;
    private String regDate;
    private int isHome;
    private String deviceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public int getVideoState() {
        return videoState;
    }

    public void setVideoState(int videoState) {
        this.videoState = videoState;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getIsHome() {
        return isHome;
    }

    public void setIsHome(int isHome) {
        this.isHome = isHome;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
