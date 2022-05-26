package com.example.payucart.model.scratchCard.scratchApplyMoney;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScratchCardPrice {

    @SerializedName("message")
    @Expose
    private String message;

    private ScratchError scratchError;

    public ScratchError getScratchError() {
        return scratchError;
    }

    public void setScratchError(ScratchError scratchError) {
        this.scratchError = scratchError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
