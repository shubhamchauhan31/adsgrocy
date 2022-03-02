package com.example.payucart.model.forget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetReq {
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
