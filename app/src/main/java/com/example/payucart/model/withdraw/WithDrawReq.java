package com.example.payucart.model.withdraw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithDrawReq {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("transferMode")
    @Expose
    private String transferMode;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransferMode() {
        return transferMode;
    }

    public void setTransferMode(String transferMode) {
        this.transferMode = transferMode;
    }
}
