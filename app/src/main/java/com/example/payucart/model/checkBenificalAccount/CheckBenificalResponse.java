package com.example.payucart.model.checkBenificalAccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckBenificalResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("error")
    @Expose
    private String error;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
