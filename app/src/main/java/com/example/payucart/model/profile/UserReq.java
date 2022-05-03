package com.example.payucart.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserReq {
    @SerializedName("user")
    @Expose
    private UserResModel user;

    public UserReq(UserResModel user) {
        this.user = user;
    }

    public UserResModel getUser() {
        return user;
    }

    public void setUser(UserResModel user) {
        this.user = user;
    }
}
