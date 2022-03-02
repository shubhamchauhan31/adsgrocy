package com.example.payucart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("plan")
    @Expose
    private Integer plan;
    @SerializedName("dailyAds")
    @Expose
    private Integer dailyAds;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("expireIn")
    @Expose
    private Integer expireIn;
    @SerializedName("totalROI")
    @Expose
    private String totalROI;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    public Integer getDailyAds() {
        return dailyAds;
    }

    public void setDailyAds(Integer dailyAds) {
        this.dailyAds = dailyAds;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public Integer getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Integer expireIn) {
        this.expireIn = expireIn;
    }

    public String getTotalROI() {
        return totalROI;
    }

    public void setTotalROI(String totalROI) {
        this.totalROI = totalROI;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
