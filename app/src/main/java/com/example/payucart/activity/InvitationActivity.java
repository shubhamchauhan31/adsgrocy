package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.adapter.ScratchCardAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.profile.UserResModel;
import com.example.payucart.model.referCode.ReferCodeResponse;
import com.example.payucart.model.scratchCard.ScratchCardData;
import com.example.payucart.model.scratchCard.ScratchCardResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvitationActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private TextView tvInvite;
    private TextView tvPromoCode;
    private TextView tvShareData;
    private ImageView imageViewLogo;
    private ImageView imageViewBackBtn;
    private List<ScratchCardData> scratchCardData;
    private ScratchCardAdapter scratchCardAdapter;


    private RecyclerView scratchCardRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        getSupportActionBar().hide();
        progressDialog=new ProgressDialog(InvitationActivity.this);
        tvInvite=findViewById(R.id.invitation_invite_Friend);
        tvPromoCode=findViewById(R.id.invitation_promo_code);
        imageViewLogo=findViewById(R.id.share_logo);
        imageViewBackBtn=findViewById(R.id.invitation_back_btn);
        tvShareData=findViewById(R.id.share_invite_friend);
        scratchCardRV=findViewById(R.id.scratch_card_award);
        scratchCardRV.setHasFixedSize(true);
        scratchCardRV.setLayoutManager(new GridLayoutManager(InvitationActivity.this,2));
        scratchCardData=new ArrayList<>();
        getScratchCard();

//        getReferCodeData();
        imageViewBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InvitationActivity.this,HomePageActivity.class));
                finish();
            }
        });

//        getReferCode();

        tvInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePromoCode();
            }
        });
    }

    private void SharePromoCode(){

        progressDialog.dismiss();
        String invite = tvInvite.getText().toString();
        String promoCode = tvPromoCode.getText().toString();
        // int image-imageViewLogo.setImageResource(R.drawable.logo);
        String appurl="app url : "+"http://adsgrocy.com/";
        String message = invite +"\n "+ promoCode +"\n"+appurl;
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "AdsGrocy");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(sharingIntent, "Message"));
    }

    private void getReferCode(){
        SharedPreferences preferences = InvitationActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
            @Override
            public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {
                if (response.isSuccessful()){
                    String refercode=response.body().getReferCode();
                    tvPromoCode.setText(refercode);
                }
            }

            @Override
            public void onFailure(Call<UserResModel> call, Throwable t) {

            }
        });


    }

    private  void getReferCodeData(){
        SharedPreferences preferences = InvitationActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        ApiCheck.api.getReferData(retrivedToken).enqueue(new Callback<ReferCodeResponse>() {
            @Override
            public void onResponse(Call<ReferCodeResponse> call, Response<ReferCodeResponse> response) {
                try{
                    if (response.isSuccessful()){
                        String inviteData=response.body().getData();
                        tvShareData.setText(inviteData);
                    }else{
                        Toast.makeText(InvitationActivity.this," Error : "+response.body().getError(),Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(InvitationActivity.this,"Exception ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReferCodeResponse> call, Throwable t) {

            }
        });
    }


    private void getScratchCard(){


        SharedPreferences preferences = InvitationActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        ApiCheck.api.getScratchCardDetails(retrivedToken).enqueue(new Callback<ScratchCardResponse>() {
            @Override
            public void onResponse(Call<ScratchCardResponse> call, Response<ScratchCardResponse> response) {

                if (response.isSuccessful()){
//
                    try{
                        scratchCardData=response.body().getScratchCardData();
                        if (scratchCardData.equals(0) || scratchCardData.equals("0") || scratchCardData==null ||scratchCardData.isEmpty() || scratchCardData.size()==0){
                            Toast.makeText(InvitationActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                        }else{
                            for (int i=0; i<scratchCardData.size(); i++){
                                scratchCardAdapter=new ScratchCardAdapter(InvitationActivity.this,scratchCardData);
                                scratchCardRV.setAdapter(scratchCardAdapter);
                                scratchCardAdapter.notifyDataSetChanged();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Call<ScratchCardResponse> call, Throwable t) {
                Toast.makeText(InvitationActivity.this, "onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getScratchCard();
    }
}