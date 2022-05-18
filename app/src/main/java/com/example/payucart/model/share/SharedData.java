package com.example.payucart.model.share;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SharedData {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
