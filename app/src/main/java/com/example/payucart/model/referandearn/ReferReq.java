package com.example.payucart.model.referandearn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReferReq {

    @SerializedName("referBy")
    @Expose
    private String referBy;

    public String getReferBy() {
        return referBy;
    }

    public void setReferBy(String referBy) {
        this.referBy = referBy;
    }
}
