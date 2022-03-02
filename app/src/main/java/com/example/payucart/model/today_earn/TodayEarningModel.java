package com.example.payucart.model.today_earn;

public class TodayEarningModel {
    private int videoImage;
    private String VideoName;
    private String videoTime;
    private String videoPrice;

    public TodayEarningModel(int videoImage, String videoName, String videoTime, String videoPrice) {
        this.videoImage = videoImage;
        VideoName = videoName;
        this.videoTime = videoTime;
        this.videoPrice = videoPrice;
    }

    public int getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(int videoImage) {
        this.videoImage = videoImage;
    }

    public String getVideoName() {
        return VideoName;
    }

    public void setVideoName(String videoName) {
        VideoName = videoName;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public String getVideoPrice() {
        return videoPrice;
    }

    public void setVideoPrice(String videoPrice) {
        this.videoPrice = videoPrice;
    }
}
