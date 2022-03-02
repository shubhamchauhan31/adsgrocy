package com.example.payucart.model.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpBody {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("authtoken")
    @Expose
    private String authtoken;

    @SerializedName("error")
    @Expose
    private String error;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
