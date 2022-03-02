package com.example.payucart.model.slider;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SliderImageModel {
    @SerializedName("images")
    @Expose
    private List<SliderImageBody> images = null;

    public List<SliderImageBody> getImages() {
        return images;
    }

    public void setImages(List<SliderImageBody> images) {
        this.images = images;
    }
}
