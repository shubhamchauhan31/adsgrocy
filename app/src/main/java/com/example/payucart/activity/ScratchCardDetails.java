package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.scratchCard.scratchApplyMoney.ScratchCardPrice;
import com.example.payucart.scratchCard.Utils;
import com.google.protobuf.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScratchCardDetails extends AppCompatActivity {

    private com.example.payucart.scratchCard.ScratchCard scratchCardWash;
    TextView codeTxt;
    String number;
    private String offerPrice;
    private TextView tvScratchCardTitle;
    private TextView tvScratchCardLimit;
    private String title;
    private String limit;
    private String id;
    private ImageView imgBackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_card_details);
        getSupportActionBar().hide();

        scratchCardWash=findViewById(R.id.scratchCard);
        codeTxt = findViewById(R.id.codeTxt);
        number = codeTxt.getText().toString();
        offerPrice=getIntent().getStringExtra("price");
        title=getIntent().getStringExtra("title");
        limit=getIntent().getStringExtra("limit");

        imgBackBtn=findViewById(R.id.scratch_card_details_back_btn);
        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        codeTxt.setText("₹ "+offerPrice);
        tvScratchCardTitle=findViewById(R.id.scratch_card_title);
        tvScratchCardLimit=findViewById(R.id.scratch_card_limit);
        tvScratchCardTitle.setText(title);
//        tvScratchCardLimit.setText(limit);
        id=getIntent().getStringExtra("id");
        handleListeners();


//        codeTxt.setText(Utils.generateNewCode());
    }

    private void scratch(boolean isScratched) {
        if (isScratched) {
            scratchCardWash.setVisibility(View.INVISIBLE);
        } else {
            scratchCardWash.setVisibility(View.VISIBLE);

        }
    }

    private void handleListeners() {
        scratchCardWash.setOnScratchListener(new com.example.payucart.scratchCard.ScratchCard.OnScratchListener() {
            @Override
            public void onScratch(com.example.payucart.scratchCard.ScratchCard scratchCard, float visiblePercent) {
                if (visiblePercent > 0.8) {
                    scratch(true);
                    SrctchCardMoney();

                }

            }
        });
    }

    private void SrctchCardMoney(){
        SharedPreferences preferences = ScratchCardDetails.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        ApiCheck.api.getScratchPrice(retrivedToken,id).enqueue(new Callback<ScratchCardPrice>() {
            @Override
            public void onResponse(Call<ScratchCardPrice> call, Response<ScratchCardPrice> response) {

                if (response.isSuccessful()){
                    Toast.makeText(ScratchCardDetails.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ScratchCardDetails.this, "Error ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ScratchCardPrice> call, Throwable t) {
                Toast.makeText(ScratchCardDetails.this,"onFailure "+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}