package com.example.payucart.model.changePassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePassReq {
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("confrimPassword")
    @Expose
    private String confrimPassword;
    @SerializedName("password")
    @Expose
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getConfrimPassword() {
        return confrimPassword;
    }

    public void setConfrimPassword(String confrimPassword) {
        this.confrimPassword = confrimPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
