package com.example.payucart.model.addmoney;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddMoneyReq {


    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("tranferFor")
    @Expose
    private String tranferFor;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTranferFor() {
        return tranferFor;
    }

    public void setTranferFor(String tranferFor) {
        this.tranferFor = tranferFor;
    }
}
