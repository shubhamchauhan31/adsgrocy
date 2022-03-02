package com.example.payucart.model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoData {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("AdminVideo")
    @Expose
    private String adminVideo;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdminVideo() {
        return adminVideo;
    }

    public void setAdminVideo(String adminVideo) {
        this.adminVideo = adminVideo;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
