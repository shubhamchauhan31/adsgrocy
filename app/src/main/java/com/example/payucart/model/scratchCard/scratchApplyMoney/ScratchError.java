package com.example.payucart.model.scratchCard.scratchApplyMoney;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScratchError {
    @SerializedName("error")
    @Expose
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
