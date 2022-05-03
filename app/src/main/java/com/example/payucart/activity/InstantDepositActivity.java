package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.adapter.MyBuyAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.buy.BuyBody;
import com.example.payucart.model.buy.BuyModel;
import com.example.payucart.model.instant_deposit.InstanrDepositModel;
import com.example.payucart.model.profile.EditProfileResponse;
import com.example.payucart.model.profile.UserResModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstantDepositActivity extends AppCompatActivity {
    @BindView(R.id.instant_deposit_back_btn)
    ImageView imgBackBtn;

    private MyBuyAdapter myBuyAdapter;

    private List<UserResModel> buyModelArrayList;
    private ProgressDialog progressDialog;
    private TextView tvPrice,tvPrice1;
    private TextView tvMoney,tvMoney1;
    private TextView tvTimePeriod,tvTimePeriod1;
    private TextView tvBuy,tvBuy1;
    private TextView tvValidity;
    private TextView tvWallet;
    private ImageView imageView;
    private LinearLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant_deposit);
        getSupportActionBar().hide();

        ButterKnife.bind(InstantDepositActivity.this);

        progressDialog=new ProgressDialog(InstantDepositActivity.this);

        progressDialog.setTitle("AdsGrocy");
        progressDialog.setMessage("Loading Package...");
        progressDialog.show();

        tvPrice=findViewById(R.id.instant_deposit_package_price);

        tvMoney=findViewById(R.id.instant_deposit_package_money);

        tvTimePeriod=findViewById(R.id.instant_deposit_package_time_period);
        tvWallet=findViewById(R.id.instant_deposit_balance);
        tvValidity=findViewById(R.id.instant_deposit_package_validity);
        imageView =findViewById(R.id.instant_deposit_not_buy_pan);
        layout=findViewById(R.id.instant_deposit_layout);
        checkValidation();


    }

    private void checkValidation(){
        boolean isbuy=true;
        if (isbuy){

            instantDeposit();
            layout.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            addMoneyInWallet();


        }else{
            layout.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }
    }
    @OnClick(R.id.instant_deposit_back_btn) void instantBackBtn(){
        startActivity(new Intent(InstantDepositActivity.this,HomePageActivity.class));
        finish();
    }



    private void instantDeposit() {
        SharedPreferences preferences = InstantDepositActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        try{
            progressDialog.setTitle("Payucart");
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            ApiCheck.api.getInstant(retrivedToken).enqueue(new Callback<InstanrDepositModel>() {
                @Override
                public void onResponse(Call<InstanrDepositModel> call, Response<InstanrDepositModel> response) {

                    if (response.isSuccessful()){
                        int plan=response.body().getInstantPackage().getPlan();
                        int getAds=response.body().getInstantPackage().getDailyAds();
                        int profit=response.body().getInstantPackage().getCommission();
                        int validity=response.body().getInstantPackage().getExpireIn();


                        tvPrice.setText(String.valueOf("₹"+plan));
                        tvTimePeriod.setText(String.valueOf("Daily Ads "+getAds));
                        tvMoney.setText(String.valueOf("Profit "+profit));
                        tvValidity.setText(String.valueOf(" Validity "+validity));
                        progressDialog.dismiss();


                    }
                }

                @Override
                public void onFailure(Call<InstanrDepositModel> call, Throwable t) {

                }
            });

        }catch (Exception e){
            Toast.makeText(InstantDepositActivity.this,"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();

        }


    }

    private void addMoneyInWallet(){

        SharedPreferences preferences = InstantDepositActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
            @Override
            public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {
                UserResModel userResModel=response.body();
                if (response.isSuccessful()){
                    String wallet= String.valueOf(userResModel.getWallet());
                    tvWallet.setText("₹ "+wallet);
                    progressDialog.dismiss();

                }else{
                    progressDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "Error :-"+userResModel.getWallet(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserResModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}