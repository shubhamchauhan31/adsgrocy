package com.example.payucart.model.forget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPRes {


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
