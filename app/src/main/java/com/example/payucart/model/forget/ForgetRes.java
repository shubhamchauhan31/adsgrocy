package com.example.payucart.model.forget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetRes {
    @SerializedName("sendOtp")
    @Expose
    private String sendOtp;

    public String getSendOtp() {
        return sendOtp;
    }

    public void setSendOtp(String sendOtp) {
        this.sendOtp = sendOtp;
    }

}
