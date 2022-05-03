package com.example.payucart.model.buy;

import com.example.payucart.model.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BuyModel {

//    @SerializedName("result")
@SerializedName("packagess")

@Expose
    private List<BuyBody> result = null;

    public List<BuyBody> getResult() {
        return result;
    }

    public void setResult(List<BuyBody> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BuyModel{" +
                "result=" + result +
                '}';
    }
}
