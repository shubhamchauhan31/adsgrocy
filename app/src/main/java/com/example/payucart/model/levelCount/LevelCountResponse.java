package com.example.payucart.model.levelCount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LevelCountResponse {
    @SerializedName("data")
    @Expose
    private LevelCountData levelCountData;

    public LevelCountData getLevelCountData() {
        return levelCountData;
    }

    public void setLevelCountData(LevelCountData levelCountData) {
        this.levelCountData = levelCountData;
    }
}
