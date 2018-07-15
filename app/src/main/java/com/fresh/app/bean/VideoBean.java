package com.fresh.app.bean;

import java.util.List;

/**
 * Created by mr.miao on 2018/7/14.
 */

public class VideoBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * deviceId : 20180515_01
         * id : 1
         * isHome : 0
         * regDate : 111
         * videoName : 111111.mp4
         * videoState : 1
         */

        private String deviceId;
        private int id;
        private int isHome;
        private String regDate;
        private String videoName;
        private int videoState;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsHome() {
            return isHome;
        }

        public void setIsHome(int isHome) {
            this.isHome = isHome;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
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
    }
}
