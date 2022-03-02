package com.example.payucart.model.rewards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Check {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("otp")
    @Expose
    private Integer otp;

    @SerializedName("perDayAddLimit")
    @Expose
    private Integer perDayAddLimit;

    @SerializedName("plan")
    @Expose
    private Integer plan;
    @SerializedName("planExpireOn")
    @Expose
    private Long planExpireOn;
    @SerializedName("beneficiary")
    @Expose
    private String beneficiary;
    @SerializedName("commission")
    @Expose
    private Integer commission;
    @SerializedName("totalROI")
    @Expose
    private Integer totalROI;
    @SerializedName("referCode")
    @Expose
    private String referCode;
    @SerializedName("referBy")
    @Expose
    private String referBy;
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("fcm_token")
    @Expose
    private String fcmToken;
    @SerializedName("profile")
    @Expose
    private String profile;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Integer getPerDayAddLimit() {
        return perDayAddLimit;
    }

    public void setPerDayAddLimit(Integer perDayAddLimit) {
        this.perDayAddLimit = perDayAddLimit;
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    public Long getPlanExpireOn() {
        return planExpireOn;
    }

    public void setPlanExpireOn(Long planExpireOn) {
        this.planExpireOn = planExpireOn;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getTotalROI() {
        return totalROI;
    }

    public void setTotalROI(Integer totalROI) {
        this.totalROI = totalROI;
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    public String getReferBy() {
        return referBy;
    }

    public void setReferBy(String referBy) {
        this.referBy = referBy;
    }


    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
