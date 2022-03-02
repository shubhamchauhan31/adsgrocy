package com.example.payucart.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.api.ApiInterface;
import com.example.payucart.model.signup.SignUpBody;
import com.example.payucart.model.signup.SignUpModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.sign_up_login_in)
     TextView tvSignIn;

    @BindView(R.id.sign_up_account)
    TextView tvSignUpAccount;

    @BindView(R.id.sign_up_layout)
    LinearLayout layout;

    @BindView(R.id.sign_up_term_condtion)
    TextView tvTermConditon;

    private EditText etUserName;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etPhoneNumber;
    private CheckBox checkBoxSignUp;
    private String url= ApiCheck.BASE_URL+"/term&condition";


    private WebView webView;
    private LinearLayout detailsLayout;
    private LinearLayout layoutWebView;
    private ImageView imgBackBtn;

    private ProgressDialog progressDialog;

   private ApiInterface apiInterface;

    private String MobileRegex="(0|91)?[6-9][0-9]{9}";
    private String passRegex="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        etUserName=findViewById(R.id.sign_up_user_name);
        etPassword=findViewById(R.id.sign_up_password);
        etConfirmPassword=findViewById(R.id.sign_up_confirm_password);
        etPhoneNumber=findViewById(R.id.sign_up_phone_number);
        checkBoxSignUp=findViewById(R.id.sign_up_check_condition);
        webView=findViewById(R.id.sign_up_term_web_view);
        detailsLayout=findViewById(R.id.sign_up_layout_detail);
        layoutWebView=findViewById(R.id.sign_up_web_view_layout);

        imgBackBtn=findViewById(R.id.sign_up_back_btn);
        progressDialog=new ProgressDialog(SignUpActivity.this);

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,SignUpActivity.class));
                finish();
            }
        });

        etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>10){
                  //  Toast.makeText(SignUpActivity.this,""+etPhoneNumber,Toast.LENGTH_SHORT).show();
                   // OtpDialogBox();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>10){
                    Toast.makeText(SignUpActivity.this,"OTP is Send "+etPhoneNumber,Toast.LENGTH_SHORT).show();
                    OtpDialogBox();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }
    @OnClick({R.id.sign_up_login_in,R.id.sign_up_layout})  void loginScreen(){
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.sign_up_account) void signUpAccount(){

        if (checkBoxSignUp.isChecked()){
            signUpData();
//            Toast.makeText(SignUpActivity.this, "Clicked ....", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(SignUpActivity.this, "Please agree to Terms & Condtion  ....", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.sign_up_term_condtion) void termConditon(){


        detailsLayout.setVisibility(View.GONE);
        layoutWebView.setVisibility(View.VISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        startWebView();


    }

    private void startWebView() {

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            @Override
            public void onLoadResource(WebView view, String url) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }

        });

//       Load url in webView
        webView.loadUrl(url);

    }

    @SuppressLint("NewApi")
    public  void signUpData() {
        String name = etUserName.getText().toString();
        String pass = etPassword.getText().toString();
        String confirm = etConfirmPassword.getText().toString();
        String mobile = etPhoneNumber.getText().toString();
        progressDialog.setTitle("AgsGrocy");
        progressDialog.setMessage("Loading Please Wait...");


        if (name.isEmpty()) {
            progressDialog.dismiss();
            etUserName.setError("Error : ");
            return;
        }

        if (pass.isEmpty()) {
            progressDialog.dismiss();

            etPassword.setError("Error : ");
            return;
        }
        if (confirm.isEmpty()) {
            progressDialog.dismiss();

            etConfirmPassword.setError("Error : ");
            return;
        }
        if (mobile.isEmpty()) {
            progressDialog.dismiss();

            etPhoneNumber.setError("Error  : ");
            return;
        }

        if (!mobile.matches(MobileRegex)){
            progressDialog.dismiss();

            etPhoneNumber.setError("In-Valid Mobile Number");
            return;
        }

        if (!pass.equals(confirm)) {
            progressDialog.dismiss();

            Toast.makeText(SignUpActivity.this,"Password Does not Matched",Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (!pass.matches(passRegex)){
            progressDialog.dismiss();

            Toast.makeText(SignUpActivity.this, "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character", Toast.LENGTH_LONG).show();
            return;
        }
        if (!pass.matches(passRegex)){
            progressDialog.dismiss();
            Toast.makeText(SignUpActivity.this, "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character", Toast.LENGTH_LONG).show();
            return;
        }

        try {

            SignUpModel signUpModel=new SignUpModel();
            signUpModel.setName(name);
            signUpModel.setMobile(mobile);
            signUpModel.setPassword(pass);

            ApiCheck.api.signUp(signUpModel).enqueue(new Callback<SignUpBody>() {
                @Override
                public void onResponse(Call<SignUpBody> call, @NonNull Response<SignUpBody> response) {
                    ResponseBody signUpErrorModel = response.errorBody();

                   try{
                       if (response.isSuccessful()) {

                           Toast.makeText(SignUpActivity.this, "Successful Create Account ", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                           finish();
                           progressDialog.dismiss();


                       }else if (response.code() == 405){
                           progressDialog.dismiss();

                           Toast.makeText(SignUpActivity.this,"Phone Number Already Exist", Toast.LENGTH_SHORT).show();

                       }
                   }catch (Exception e){
                       progressDialog.dismiss();

                       Toast.makeText(SignUpActivity.this,"Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();

                   }

                }

                @Override
                public void onFailure(Call<SignUpBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.dismiss();
            Toast.makeText(SignUpActivity.this, "Exception : " + e.getMessage(), Toast.LENGTH_SHORT);
        }

    }

    private void OtpDialogBox(){

        // Create an alert builder
        AlertDialog.Builder builder
                = new AlertDialog.Builder(SignUpActivity.this);

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_otp, null);
        builder.setView(customLayout);

        EditText otp1 = customLayout.findViewById(R.id.custom_otp1);
        EditText otp2 = customLayout.findViewById(R.id.custom_otp1);
        EditText otp3 = customLayout.findViewById(R.id.custom_otp3);
        EditText otp4 = customLayout.findViewById(R.id.custom_otp4);
        EditText otp5 = customLayout.findViewById(R.id.custom_otp5);
        EditText otp6 = customLayout.findViewById(R.id.custom_otp6);

        String otp_one=otp1.getText().toString();
        String otp_two=otp2.getText().toString();
        String otp_three=otp3.getText().toString();

        // add a button
        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(
                            DialogInterface dialog,int which) {
                        // send data from the
                        // AlertDialog to the Activity


//                                sendDialogDataToActivity(
//                                        editText
//                                                .getText()
//                                                .toString());
//                            }
                    }
                });

        // create and show
        // the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}