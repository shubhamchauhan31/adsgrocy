package com.example.payucart.model.benificial;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BenificialResponse {
    @SerializedName("User_Beneficiary")
    @Expose
    private UserBeneficiary userBeneficiary;

    public UserBeneficiary getUserBeneficiary() {
        return userBeneficiary;
    }

    public void setUserBeneficiary(UserBeneficiary userBeneficiary) {
        this.userBeneficiary = userBeneficiary;
    }
}
