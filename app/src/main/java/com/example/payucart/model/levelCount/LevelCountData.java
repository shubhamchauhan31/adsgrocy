package com.example.payucart.model.levelCount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LevelCountData {
    @SerializedName("referUserCount")
    @Expose
    private Integer referUserCount;

    @SerializedName("referUserEarning")
    @Expose

    private Integer referUserEarning;
    @SerializedName("_id")
    @Expose

    private String id;
    @SerializedName("userLevel")
    @Expose
    private Integer userLevel;

    public Integer getReferUserCount() {
        return referUserCount;
    }

    public void setReferUserCount(Integer referUserCount) {
        this.referUserCount = referUserCount;
    }

    public Integer getReferUserEarning() {
        return referUserEarning;
    }

    public void setReferUserEarning(Integer referUserEarning) {
        this.referUserEarning = referUserEarning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

}
