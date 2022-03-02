package com.example.payucart.model.buy.getPackages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackgesBody {
    @SerializedName("paymentLink")
    @Expose
    private String paymentLink;

    @SerializedName("orderLink")
    @Expose
    private String oderLink;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("error")
    @Expose
    private String error;

    public String getOderLink() {
        return oderLink;
    }

    public void setOderLink(String oderLink) {
        this.oderLink = oderLink;
    }

    public String getPaymentLink() {
        return paymentLink;
    }

    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
