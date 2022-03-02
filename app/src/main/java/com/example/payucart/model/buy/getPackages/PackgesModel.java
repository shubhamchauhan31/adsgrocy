package com.example.payucart.model.buy.getPackages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackgesModel {

    @SerializedName("orderLink")
    @Expose
    private PackgesBody packgesBody;
    @SerializedName("amount")
    @Expose
    private  String amount;

    public PackgesBody getPackgesBody() {
        return packgesBody;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPackgesBody(PackgesBody packgesBody) {
        this.packgesBody = packgesBody;
    }
}
