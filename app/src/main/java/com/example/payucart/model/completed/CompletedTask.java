package com.example.payucart.model.completed;

public class CompletedTask {
    private int videoImage;
    private String VideoName;
    private String videoTime;

    public CompletedTask(int videoImage, String videoName, String videoTime) {
        this.videoImage = videoImage;
        VideoName = videoName;
        this.videoTime = videoTime;
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


}
