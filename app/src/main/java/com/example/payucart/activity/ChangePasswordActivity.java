package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.changePassword.ChangePassReq;
import com.example.payucart.model.changePassword.ChangePassRes;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    private TextInputEditText tvNewPass,tvconfrimPass;
    private TextView tvChangePassword;
    private EditText etMobilePhone;
    private String sendMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().hide();
        tvNewPass=findViewById(R.id.change_password_new_pass);
        tvconfrimPass=findViewById(R.id.change_password_confrim_pass);
        tvChangePassword=findViewById(R.id.change_password_succesfully);
        etMobilePhone=findViewById(R.id.change_password_mobile);

        sendMobile=getIntent().getExtras().getString("mobile");
        etMobilePhone.setText(sendMobile);
        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();

            }
        });

    }

    private void changePassword(){
        String newPass=tvNewPass.getText().toString();
        String confrimPass=tvconfrimPass.getText().toString();
//        String mobile=etMobilePhone.getText().toString();

        ChangePassReq changePassReq=new ChangePassReq();
        changePassReq.setMobile(sendMobile);
        changePassReq.setPassword(newPass);
        changePassReq.setConfrimPassword(confrimPass);


//        if (sendMobile.isEmpty()){
//            etMobilePhone.setText("Enter Mobile Password");
//            return;
//        }

        if (newPass.isEmpty()){
            tvNewPass.setError("Enter New Password");
            return;
        }

        if (confrimPass.isEmpty()){
            tvChangePassword.setText("Enter Confirm Password");
            return;
        }

        if (!newPass.equals(confrimPass)){
           Toast.makeText(ChangePasswordActivity.this,"Password Does Not Match",Toast.LENGTH_SHORT).show();
//           tvNewPass.setText("");
//           tvChangePassword.setText("");
           return;
        }


        try{
            ApiCheck.api.changePassword(changePassReq).enqueue(new Callback<ChangePassRes>() {
                @Override
                public void onResponse(Call<ChangePassRes> call, Response<ChangePassRes> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(ChangePasswordActivity.this, "Status"+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ChangePasswordActivity.this,LoginActivity.class));
                        finish();
                    }else{
                        Toast.makeText(ChangePasswordActivity.this, "Error : ", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<ChangePassRes> call, Throwable t) {
                    Toast.makeText(ChangePasswordActivity.this, "onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            Toast.makeText(ChangePasswordActivity.this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }
}