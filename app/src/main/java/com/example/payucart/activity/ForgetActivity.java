package com.example.payucart.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.forget.ForgetReq;
import com.example.payucart.model.forget.ForgetRes;
import com.example.payucart.model.forget.OTPReq;
import com.example.payucart.model.forget.OTPRes;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetActivity extends AppCompatActivity {
    private ImageView imgBackBtn;
    private TextInputEditText forwordMobile ;
    private TextView tvSendOtp;
    private String mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        getSupportActionBar().hide();
        imgBackBtn=findViewById(R.id.forget_password_back_btn);
        forwordMobile = findViewById(R.id.custom_login_mobile);
        tvSendOtp=findViewById(R.id.forget_Send_otp);
        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetActivity.this,LoginActivity.class));
                finish();
            }
        });
        tvSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentOtp();
            }
        });
    }

    private void sentOtp(){
         try{
             mobile=forwordMobile.getText().toString();

             if (mobile.isEmpty()){
                 forwordMobile.setError("Enter 10-digit Mobile Number");
                 return;
             }
             ForgetReq forgetReq=new ForgetReq();
             forgetReq.setMobile(mobile);

             ApiCheck.api.getMobile(forgetReq).enqueue(new Callback<ForgetRes>() {
                 @Override
                 public void onResponse(Call<ForgetRes> call, Response<ForgetRes> response) {
                     if (response.isSuccessful()){
                         Toast.makeText(getApplicationContext(), "Successful OTP Send ", Toast.LENGTH_SHORT).show();
                         showAlertDialogButtonClicked();
                     }
                 }

                 @Override
                 public void onFailure(Call<ForgetRes> call, Throwable t) {
                     Toast.makeText(getApplicationContext(), "Failure Send "+t.getMessage(), Toast.LENGTH_SHORT).show();

                 }
             });
         }catch (Exception e){
             Toast.makeText(ForgetActivity.this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
         }

    }


    public void showAlertDialogButtonClicked()
    {

        // Create an alert builder
        AlertDialog.Builder builder
                = new AlertDialog.Builder(ForgetActivity.this);

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.custom_otp,
                        null);
        builder.setView(customLayout);




        // add a button
        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    EditText otp=customLayout.findViewById(R.id.custom_otp);

//                    EditText otp1 =customLayout.findViewById(R.id.custom_otp1);
//                    EditText otp2 =customLayout.findViewById(R.id.custom_otp2);
//                    EditText otp3 = customLayout.findViewById(R.id.custom_otp3);
//                    EditText otp4 =customLayout.findViewById(R.id.custom_otp4);
//                    EditText otp5 = customLayout.findViewById(R.id.custom_otp5);
//                    EditText otp6 = customLayout.findViewById(R.id.custom_otp6);


                    @Override
                    public void onClick(
                            DialogInterface dialog,int which) {

//                            String otpOne=otp1.getText().toString();
//                            String otpTwo=otp2.getText().toString();
//                            String otpThree=otp3.getText().toString();
//                            String otpFour=otp4.getText().toString();
//                            String otpFive=otp5.getText().toString();
//                            String otpSix=otp6.getText().toString();

//                        String verifyOtp=otpOne+otpTwo+otpThree+otpFour+otpFive+otpSix;
                        String verifyOtp=otp.getText().toString();

                        if (verifyOtp.isEmpty()){
                            Toast.makeText(ForgetActivity.this, "In valid OTP", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        OTPReq otpReq=new OTPReq();
                        otpReq.setMobile(mobile);
                        otpReq.setOtp(verifyOtp);
                        try{
                            ApiCheck.api.verifyOtp(otpReq).enqueue(new Callback<OTPRes>() {
                                @Override
                                public void onResponse(Call<OTPRes> call, Response<OTPRes> response) {

                                    if (response.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"OTP Successfully Verified",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(ForgetActivity.this,ChangePasswordActivity.class);
                                        intent.putExtra("mobile",mobile);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Error :-",Toast.LENGTH_SHORT).show();

                                    }

                                }

                                @Override
                                public void onFailure(Call<OTPRes> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(),"onFailure :-"+t.getMessage(),Toast.LENGTH_SHORT).show();

                                }
                            });

                        }catch (Exception e){
                            Toast.makeText(ForgetActivity.this, "In Valid OTP "+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

        // create and show
        // the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}