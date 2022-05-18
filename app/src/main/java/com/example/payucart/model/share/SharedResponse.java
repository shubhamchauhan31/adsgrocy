package com.example.payucart.model.share;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SharedResponse {
    @SerializedName("data")
    @Expose
    private SharedData sharedData;

    public SharedData getSharedData() {
        return sharedData;
    }

    public void setSharedData(SharedData sharedData) {
        this.sharedData = sharedData;
    }
}
