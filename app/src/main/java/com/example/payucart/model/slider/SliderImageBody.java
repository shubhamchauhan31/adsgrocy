package com.example.payucart.model.slider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SliderImageBody {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("bannerImage")
    @Expose
    private String bannerImage;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
