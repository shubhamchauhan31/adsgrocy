package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.fragment.AddMoneyFragment;
import com.example.payucart.fragment.WithDrawMoneyFragment;
import com.example.payucart.model.profile.UserResModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletActivity extends AppCompatActivity {

    @BindView(R.id.wallet_add_money_layout)
    LinearLayout addMoneyLayout;

    @BindView(R.id.wallet_add_money_tv)
    TextView tvAddMoney;

    @BindView(R.id.wallet_with_draw_money_layout)
    LinearLayout withdrawMoneyLayout;

    @BindView(R.id.wallet_with_draw_money_tv)
    TextView withdrawMoneyTv;

    @BindView(R.id.wallet_activity_back_btn)
     ImageView imgWalletBAckBtn;

    private TextView setMoney;

    private AddMoneyFragment addMoneyFragment;
    private WithDrawMoneyFragment withDrawMoneyFragment;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        getSupportActionBar().hide();
        setContentView(R.layout.activity_wallet);

        ButterKnife.bind(this);
        addMoneyFragment=new AddMoneyFragment();


        setMoney=findViewById(R.id.wallet_available_balance);

     //   setFragment(addMoneyFragment);
        withDrawMoneyFragment=new WithDrawMoneyFragment();

        setFragment(withDrawMoneyFragment);

        progressDialog=new ProgressDialog(getApplicationContext());
        progressDialog.setTitle("Payucart");
        progressDialog.setMessage("Loading...");
//        progressDialog.show();

        addMoneyInWallet();



    }

    @OnClick({R.id.wallet_add_money_layout,R.id.wallet_add_money_tv}) void addMoneyScreen(){
        setFragment(addMoneyFragment);
    }
    @OnClick({R.id.wallet_with_draw_money_layout,R.id.wallet_with_draw_money_tv}) void withDrawMoney(){
        setFragment(withDrawMoneyFragment);
    }

    @OnClick(R.id.wallet_activity_back_btn) void WalletBackBtn(){
        startActivity(new Intent(WalletActivity.this,HomePageActivity.class));
        finish();
    }


    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.wallet_frame_layout,fragment).commit();
    }
    private void addMoneyInWallet(){

        SharedPreferences preferences = WalletActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
            @Override
            public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {
                UserResModel userResModel=response.body();
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    String wallet= String.valueOf(userResModel.getWallet());
                    setMoney.setText("₹ "+wallet);

                }else{
                    progressDialog.dismiss();

                    Toast.makeText(getApplicationContext(), "Your Wallet Balance Is Low  :-"+userResModel.getWallet(), Toast.LENGTH_SHORT).show();
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