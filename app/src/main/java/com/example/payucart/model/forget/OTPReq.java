package com.example.payucart.model.forget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPReq {

    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
