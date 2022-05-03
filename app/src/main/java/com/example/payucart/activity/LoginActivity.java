package com.example.payucart.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.fragment.LoginFragment;
import com.example.payucart.model.login.LoginReq;
import com.example.payucart.model.login.LoginRes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {



    private ProgressDialog progressDialog;

    @BindView(R.id.login_sign_up)
    TextView tvSignUp;

    @BindView(R.id.login_home_page)
    TextView tvHomePage;

    @BindView(R.id.login_forget_password)
    TextView tvLoginForgetPassword;

    @BindView(R.id.login_sign_up_layout)
    LinearLayout layoutSignUp;

    private  TextView tvMobile;

    private TextView tvPassword;

    private CheckBox checkBoxLogin;

    private SharedPreferences sharedPreferences;

    private String token;
    private String fcmToken;

    private String MobileRegex="(0|91)?[6-9][0-9]{9}";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        getSupportActionBar().hide(); // hide the action bar

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(LoginActivity.this);
        tvMobile=findViewById(R.id.login_mobile);
        tvPassword=findViewById(R.id.login_user_password);
        checkBoxLogin=findViewById(R.id.login_checkbox);
        progressDialog=new ProgressDialog(LoginActivity.this);
//        sharedPreferences= getSharedPreferences("hasLogin", Context.MODE_PRIVATE);
        getFCMToken();

    }




    @OnClick({R.id.login_sign_up,R.id.login_sign_up_layout}) void signupScreen(){
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        finish();
    }

    @OnClick(R.id.login_forget_password) void forgetScreen(){
        startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
    }
    @SuppressLint("NewApi")
    @OnClick(R.id.login_home_page) void homePageScreen(){
        //     startActivity(new Intent(getContext(),HomePageActivity.class));
        if (checkBoxLogin.isChecked()){
//            sharedPreferences.edit().putBoolean("hasLogin",true).apply();

            loginIn(tvMobile.getText().toString(),tvPassword.getText().toString());

        }else{
            Toast.makeText(LoginActivity.this, "Please Verify Login Details", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("NewApi")
    private void loginIn(String userPhone, String userPassword){

        try{

            progressDialog.setTitle("AdsGrocy");
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            LoginReq loginReq=new LoginReq();
            loginReq.setMobile(userPhone);
            loginReq.setPassword(userPassword);

            if (userPhone.isEmpty()){
                tvMobile.setError("Please Enter Mobile Number...");
                return;
            }

            if(!userPhone.matches(MobileRegex)){
                progressDialog.dismiss();
                tvMobile.setError("In-Valid Mobile Number");
                return;
            }

            if (userPassword.isEmpty()){
                tvPassword.setError("Please Enter User Password...");
                return;
            }



            ApiCheck.api.loginSucessFully(fcmToken,loginReq).enqueue(new Callback<LoginRes>() {
                @Override
                public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {
                    try{
                        LoginRes res = response.body();

                        if (response.isSuccessful()){

                            sharedPreferences= LoginActivity.this.getSharedPreferences("hasLogin", Context.MODE_PRIVATE);
                            sharedPreferences.edit().putBoolean("hasLogin",true).apply();
                            //Save app token here
                            String token1 = res.getAuthtoken();
                            SharedPreferences preferences = getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
                            preferences.edit().putString("TOKEN",token1).apply();



                            // mobile Number


                            SharedPreferences preferences1 = getSharedPreferences("MOBILE",Context.MODE_PRIVATE);
                            preferences1.edit().putString("MOBILE",userPhone).apply();
                            progressDialog.dismiss();



                            Intent intent=new Intent(LoginActivity.this,HomePageActivity.class);
                            startActivity(intent);
                            finish();


                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"In-Valid Login Details",Toast.LENGTH_SHORT).show();

                        }
                    }catch (Exception e){
                        // Toast.makeText(getContext(),"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();

                        Toast.makeText(LoginActivity.this,"Enter Valid Detail : "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Log.d("shu","shu...."+e);
                        finish();
                    }

                }



                @Override
                public void onFailure(Call<LoginRes> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();

                }
            });
        }catch (Exception e){
            progressDialog.dismiss();
            finish();

        }

    }


    public void getFCMToken(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()){
                    fcmToken=task.getResult();

                    //  Toast.makeText(getContext(),"FCM TOKEN : "+token,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void setMobile(Context context, String key, String value){
        SharedPreferences prefs = context.getSharedPreferences("payucart", Context.MODE_PRIVATE);
        prefs.edit().putString(key,value).commit();
    }



}