package com.example.payucart.model.scratchCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScratchCardResponse {

    @SerializedName("data")
    @Expose
    private List<ScratchCardData> scratchCardData = null;

    public List<ScratchCardData> getScratchCardData() {
        return scratchCardData;
    }

    public void setScratchCardData(List<ScratchCardData> scratchCardData) {
        this.scratchCardData = scratchCardData;
    }
}
