package com.example.payucart.model.video;

import android.provider.MediaStore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VedioResponse {


    @SerializedName("videos")
    @Expose
    private List<VideoData> videos = null;

    public List<VideoData> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoData> videos) {
        this.videos = videos;
    }
}
