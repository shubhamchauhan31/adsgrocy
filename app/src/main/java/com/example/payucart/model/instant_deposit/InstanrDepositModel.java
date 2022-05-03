package com.example.payucart.model.instant_deposit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InstanrDepositModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("package")
    @Expose
    private InstantPackage instantPackage;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public InstantPackage getInstantPackage() {
        return instantPackage;
    }

    public void setInstantPackage(InstantPackage instantPackage) {
        this.instantPackage = instantPackage;
    }
}
