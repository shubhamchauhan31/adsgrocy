package com.example.payucart.model.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.Body;

public class SignUpModel {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("password")
    @Expose
    private String password;

    private SignUpBody signUpBody;


    public SignUpModel() {
    }

    public SignUpBody getSignUpBody() {
        return signUpBody;
    }

    public void setSignUpBody(SignUpBody signUpBody) {
        this.signUpBody = signUpBody;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
