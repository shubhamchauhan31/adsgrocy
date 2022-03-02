package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.adapter.InstantPayoutAdapter;
import com.example.payucart.adapter.MyWithDrawAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.instant_payout.InstantPayoutModel;
import com.example.payucart.model.profile.UserResModel;
import com.example.payucart.model.transfer.TransferResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstantPayoutActivity extends AppCompatActivity {
    private RecyclerView instantPayoutRecyclerView;
    private InstantPayoutAdapter instantPayoutAdapter;
    private ProgressDialog progressDialog;
    private TextView tvWallet;
    private List<TransferResponse> transferResponses;
    private MyWithDrawAdapter myWithDrawAdapter;
    private ImageView instantTransactionImage;

    @BindView(R.id.instant_payout_back_btn)
    ImageView imageViewBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant_payout);
        getSupportActionBar().hide();

        ButterKnife.bind(InstantPayoutActivity.this);

        progressDialog=new ProgressDialog(InstantPayoutActivity.this);
        tvWallet=findViewById(R.id.instant_deposit_wallet_balance);

        instantPayoutRecyclerView=findViewById(R.id.instant_payout_recyclerview);

        instantPayoutRecyclerView.setHasFixedSize(false);

        instantPayoutRecyclerView.setLayoutManager(new LinearLayoutManager(InstantPayoutActivity.this,LinearLayoutManager.VERTICAL,false));

        instantTransactionImage=findViewById(R.id.instant_payout_no_transaction_image_view);
        checkValidation();
        addMoneyInWallet();
        getAllTraction();
    }

    @OnClick(R.id.instant_payout_back_btn) void BackToPage(){
        startActivity(new Intent(InstantPayoutActivity.this,HomePageActivity.class));
        finish();
    }

    private void addMoneyInWallet(){

        SharedPreferences preferences = InstantPayoutActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
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

    private void getAllTraction(){
        SharedPreferences preferences = InstantPayoutActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        ApiCheck.api.getTransaction(retrivedToken).enqueue(new Callback<List<TransferResponse>>() {
            @Override
            public void onResponse(Call<List<TransferResponse>> call, Response<List<TransferResponse>> response) {
                if (response.isSuccessful()){
                    transferResponses=response.body();
                    myWithDrawAdapter=new MyWithDrawAdapter(transferResponses,InstantPayoutActivity.this);
                    instantPayoutRecyclerView.setAdapter(myWithDrawAdapter);
                    myWithDrawAdapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(InstantPayoutActivity.this, "Error : -", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<TransferResponse>> call, Throwable t) {
                Toast.makeText(InstantPayoutActivity.this,"OnFailure"+t.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.print("OnFailure"+t.getMessage());
                Log.d("Shu","Shu"+t.getMessage());
            }
        });
    }

    private void checkValidation(){
        boolean isTrue=true;
        if (isTrue){
            instantPayoutRecyclerView.setVisibility(View.VISIBLE);
            instantTransactionImage.setVisibility(View.GONE);
        }else{
            instantPayoutRecyclerView.setVisibility(View.GONE);
            instantTransactionImage.setVisibility(View.VISIBLE);
        }
    }


}