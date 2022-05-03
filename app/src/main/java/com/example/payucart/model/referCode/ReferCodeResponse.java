package com.example.payucart.model.referCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReferCodeResponse {

    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("data")
    @Expose
    private String data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
