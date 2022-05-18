package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.shareSocialMedia.ShareFriendResponse;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareToFriendAcivity extends AppCompatActivity {
    @BindView(R.id.share_friend_whatsapp)
    TextView tvWhatsapp;

    @BindView(R.id.share_friend_facebook)
    TextView tvFacebook;

    @BindView(R.id.share_friend_instagram)
    TextView tvInstagram;

    private ImageView imgBackBtn;
    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_to_friend_acivity);
        getSupportActionBar().hide();
        ButterKnife.bind(ShareToFriendAcivity.this);
        init();

    }

    private void init(){
        imgBackBtn=findViewById(R.id.share_friend_back_btn);

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getImageData();
    }

    @OnClick(R.id.share_friend_whatsapp) void shareWhatsapp(){
        shareViaWhatsApp();
    }

    @OnClick(R.id.share_friend_facebook) void shareFacebook(){
        shareViaWithFacebook();
    }

    @OnClick(R.id.share_friend_instagram) void shareInstagram(){
        shareViaWithInstagram();
    }



    public void shareViaWhatsApp() {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        String url="http://www.adsgrocy.com/"+imgUrl;

        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, url);
        try {
            Objects.requireNonNull(ShareToFriendAcivity.this).startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
        }
    }
    public void shareViaWithFacebook() {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        String url="http://www.adsgrocy.com/"+imgUrl;

        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.facebook.katana");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, url);
        try {
            Objects.requireNonNull(ShareToFriendAcivity.this).startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.katana")));
        }
    }

    public void shareViaWithInstagram() {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        String url="http://www.adsgrocy.com/"+imgUrl;

        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.instagram.android");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, url);
        try {
            Objects.requireNonNull(ShareToFriendAcivity.this).startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.instagram.android")));
        }
    }


    private  void getImageData(){
        ApiCheck.api.getFriendResponse().enqueue(new Callback<ShareFriendResponse>() {
            @Override
            public void onResponse(Call<ShareFriendResponse> call, Response<ShareFriendResponse> response) {
                if (response.isSuccessful()){
                    imgUrl=response.body().getShareFriendData().getUrl();
                }else{
                    Toast.makeText(ShareToFriendAcivity.this, "Some thing went wrong ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShareFriendResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"onFailure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}