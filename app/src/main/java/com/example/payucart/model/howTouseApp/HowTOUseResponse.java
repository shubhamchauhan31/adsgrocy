package com.example.payucart.model.howTouseApp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HowTOUseResponse {

    @SerializedName("data")
    @Expose
    private HowToUSeApp howToUSeApp;

    public HowToUSeApp getHowToUSeApp() {
        return howToUSeApp;
    }

    public void setHowToUSeApp(HowToUSeApp howToUSeApp) {
        this.howToUSeApp = howToUSeApp;
    }
}
