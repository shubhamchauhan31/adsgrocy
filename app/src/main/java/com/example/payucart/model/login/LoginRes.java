package com.example.payucart.model.login;

import com.example.payucart.model.signup.SignUpBody;
import com.example.payucart.model.signup.SignUpModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRes {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("authtoken")
    @Expose
    private String authtoken;

    private SignUpModel signUpModel;

    public SignUpModel getSignUpModel() {
        return signUpModel;
    }

    public void setSignUpModel(SignUpModel signUpModel) {
        this.signUpModel = signUpModel;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
