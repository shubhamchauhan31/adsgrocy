package com.example.payucart.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChartData {

    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("activeMember")
    @Expose
    private String activeMember;
    @SerializedName("inActiveMember")
    @Expose
    private String inActiveMember;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("commission")
    @Expose
    private Integer commission;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("isActive")
    @Expose
    private String isActive;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getActiveMember() {
        return activeMember;
    }

    public void setActiveMember(String activeMember) {
        this.activeMember = activeMember;
    }

    public String getInActiveMember() {
        return inActiveMember;
    }

    public void setInActiveMember(String inActiveMember) {
        this.inActiveMember = inActiveMember;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

}
