package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.levelCount.LevelCountResponse;
import com.example.payucart.model.profile.UserResModel;
import com.example.payucart.model.referCode.ReferCodeResponse;
import com.example.payucart.model.shareSocialMedia.ShareFriendResponse;
import com.google.protobuf.Api;

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

    @BindView(R.id.share_to_friend_withdraw_fake)
     TextView tvFakeWithdraw;

    private String inviteData;
    private TextView tvTotalCount;
    private TextView tvShareEarning;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_to_friend_acivity);
        getSupportActionBar().hide();
        progressDialog=new ProgressDialog(ShareToFriendAcivity.this);
        ButterKnife.bind(ShareToFriendAcivity.this);
        init();

    }

    private void init(){
        imgBackBtn=findViewById(R.id.share_friend_back_btn);
        tvTotalCount=findViewById(R.id.share_to_friend_total_count);
        tvShareEarning=findViewById(R.id.share_to_friend_sharing_earning);



        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        getImageData();
        levelDetails();
        inviteData=getIntent().getStringExtra("referCode");


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

    @OnClick(R.id.share_to_friend_withdraw_fake) void fakeWithdraw(){
        addProfile();
    }



    public void shareViaWhatsApp() {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        String url="Earn daily watching ads and using scratch card don't miss the opportunity\n \uD83C\uDF89 Get 25 rs welcome bonus on signup\n \uD83C\uDF89 Get 5 rs on each whatsapp share to friends\n \uD83C\uDF89 Get 100 rs on Facebook sharing\n \uD83C\uDF89 Get 100 rs on Instagram sharing\n For more details download app\n "+"http://www.adsgrocy.com/"+inviteData+"\n How to earn by watching ads and Scratching cards , how to earn by sharing for all click on the vedio top after signup.\n"+"Telegram link\n https://t.me/+wOEz5YFMvtEyYzVl";

        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        Uri uri = Uri.parse(url);
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, url);
        try {
            Objects.requireNonNull(ShareToFriendAcivity.this).startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
        }
    }

    public void shareViaWithFacebook() {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        String url="Earn daily watching ads and using scratch card don't miss the opportunity\n \uD83C\uDF89 Get 25 rs welcome bonus on signup\n \uD83C\uDF89 Get 5 rs on each whatsapp share to friends\n \uD83C\uDF89 Get 100 rs on Facebook sharing\n \uD83C\uDF89 Get 100 rs on Instagram sharing\n For more details download app\n "+"http://www.adsgrocy.com/"+inviteData+"\n How to earn by watching ads and Scratching cards , how to earn by sharing for all click on the vedio top after signup.\n"+"Telegram link\n https://t.me/+wOEz5YFMvtEyYzVl";

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
        String url="Earn daily watching ads and using scratch card don't miss the opportunity\n \uD83C\uDF89 Get 25 rs welcome bonus on signup\n \uD83C\uDF89 Get 5 rs on each whatsapp share to friends\n \uD83C\uDF89 Get 100 rs on Facebook sharing\n \uD83C\uDF89 Get 100 rs on Instagram sharing\n For more details download app\n "+"http://www.adsgrocy.com/"+inviteData+"\n How to earn by watching ads and Scratching cards , how to earn by sharing for all click on the vedio top after signup.\n"+"Telegram link\n https://t.me/+wOEz5YFMvtEyYzVl";

        whatsappIntent.setType("image/jpeg");
        Uri uri = Uri.parse(url);

        whatsappIntent.setPackage("com.instagram.android");
        whatsappIntent.putExtra(Intent.EXTRA_STREAM, url);
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

    private void addProfile() {
        SharedPreferences preferences = ShareToFriendAcivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        try{
            progressDialog.setTitle("AdsGrocy");
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {
                    try{
                        if (response.isSuccessful()){
                            progressDialog.dismiss();
                            boolean planBuy = false;
                            String mobile=response.body().getMobile();
                            String status=response.body().getStatus();

                            int  plan = response.body().getPlan();
                            String profileAvaliableBalance=response.body().getWallet();
                            String profileTodayEarning=response.body().gettEarning();
                            String profileYesterday=response.body().getyEarning();
                            String benficialstatus=response.body().getBeneficiary();

                            if (status.equals("active")){

                                Toast.makeText(ShareToFriendAcivity.this, "Please wait, your money will be added in 19 days to your wallet. ", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(ShareToFriendAcivity.this, "Please purchase membership then withdraw your amount", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            progressDialog.dismiss();

                        }
                    }catch(Exception e){
                        Toast.makeText(ShareToFriendAcivity.this,"Exception "+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<UserResModel> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(ShareToFriendAcivity.this,"Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            Toast.makeText(ShareToFriendAcivity.this,"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();

        }


    }

    private void levelDetails(){
        SharedPreferences preferences = ShareToFriendAcivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        ApiCheck.api.getLevelCount(retrivedToken).enqueue(new Callback<LevelCountResponse>() {
            @Override
            public void onResponse(Call<LevelCountResponse> call, Response<LevelCountResponse> response) {
                if (response.isSuccessful()){
                    int levelCount=response.body().getLevelCountData().getReferUserCount();
                    int levelCountEarning=response.body().getLevelCountData().getReferUserEarning();

                    tvShareEarning.setText(String.valueOf(levelCountEarning));
                    tvTotalCount.setText(String.valueOf(levelCount));


                }else{
                    Toast.makeText(ShareToFriendAcivity.this,"Error : ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LevelCountResponse> call, Throwable t) {

                Toast.makeText(ShareToFriendAcivity.this, "onFailure "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}