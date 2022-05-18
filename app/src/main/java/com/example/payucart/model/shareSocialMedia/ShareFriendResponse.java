package com.example.payucart.model.shareSocialMedia;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShareFriendResponse {
    @SerializedName("data")
    @Expose
    private ShareFriendData shareFriendData;

    public ShareFriendData getShareFriendData() {
        return shareFriendData;
    }

    public void setShareFriendData(ShareFriendData shareFriendData) {
        this.shareFriendData = shareFriendData;
    }
}
