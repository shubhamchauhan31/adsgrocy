package com.example.payucart.model.profile;

import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

public class EditProfileReq {
    @SerializedName("img")
    MultipartBody.Part image;


    public MultipartBody.Part getImage() {
        return image;
    }

    public void setImage(MultipartBody.Part image) {
        this.image = image;
    }

//    private String editProfile;
//
//
//    public String getEditProfile() {
//        return editProfile;
//    }
//
//    public void setEditProfile(String editProfile) {
//        this.editProfile = editProfile;
//    }

}
