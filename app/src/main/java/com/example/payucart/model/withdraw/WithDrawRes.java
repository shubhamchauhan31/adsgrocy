package com.example.payucart.model.withdraw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithDrawRes {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("subCode")
    @Expose
    private String subCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private WithDrawData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WithDrawData getData() {
        return data;
    }

    public void setData(WithDrawData data) {
        this.data = data;
    }
}
