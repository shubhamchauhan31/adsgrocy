package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.benificial.BenificalReq;
import com.example.payucart.model.benificial.BenificialResponse;
import com.google.gson.JsonObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyActivity extends AppCompatActivity {

    boolean isAccountAdded = false;

    @BindView(R.id.buy_package_benifical_detail_submit)
    TextView tvBuyPackage;

    @BindView(R.id.buy_package_back_btn)
    ImageView imgBAckBtn;

    private EditText etBuyPackageMobile; //1
    private EditText etBuyHolderName;//2
    private EditText etBuyPackageBankAccountNumber; //3
    private EditText etBuyPackageBankIfscCode;//4
    private EditText etBuyPackageAddrress;//5
    private EditText etVPA;//6
    private EditText etCardName;//7
    private EditText etCity;//8
    private EditText etPincode;//9
    private EditText etState;//10
    private SharedPreferences sharedPreferences;
    private CheckBox checkBoxCondition;
    private ProgressDialog progressDialog;

    // regex validation

    private String regIFSC = "^[A-Z]{4}[0][A-Z0-9]{6}$";
    private boolean isvalid = false;
    private String regAccount="[0-9]{9,18}";
    private String MobileRegex="(0|91)?[6-9][0-9]{9}";
    private String pincodeRegx="^[1-9][0-9]{5}$";
    private String upiRegex="^[\\w\\.\\-_]{3,}@[a-zA-Z]{3,}";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        getSupportActionBar().hide();

        ButterKnife.bind(BuyActivity.this);

        etBuyPackageMobile=findViewById(R.id.buy_package_mobile_number); //1

        etBuyHolderName=findViewById(R.id.buy_package_user_name);//2

        etVPA=findViewById(R.id.buy_package_card_vpa);//3

//        etCardName=findViewById(R.id.buy_package_bank_card_name);//4

        etCity=findViewById(R.id.buy_package_city);//5

        etPincode=findViewById(R.id.buy_package_pin_code_code);//6

        etBuyPackageBankAccountNumber=findViewById(R.id.buy_package_bank_account_number);//7

        etBuyPackageBankIfscCode=findViewById(R.id.buy_package_account_ifc_code);//8

        etBuyPackageAddrress=findViewById(R.id.buy_package_bank_address);//9

        etState=findViewById(R.id.buy_package_bank_state);//10

        checkBoxCondition=findViewById(R.id.benificial_checkbox);

        progressDialog=new ProgressDialog(BuyActivity.this);

        progressDialog.setTitle("AdsGrocy");
        progressDialog.setMessage("Loading");


        sharedPreferences= BuyActivity.this.getSharedPreferences("benifical", Context.MODE_PRIVATE);


    }

    @OnClick(R.id.buy_package_back_btn) void BuyPackageBackBtn(){
        startActivity(new Intent(BuyActivity.this,HomePageActivity.class));
        finish();
    }

    @OnClick(R.id.buy_package_benifical_detail_submit) void BuyPackageSuccess(){

        if (checkBoxCondition.isChecked()){
            sharedPreferences.edit().putBoolean("benifical",true).apply();
            AddAccountDetail();

        }else{
            Toast.makeText(BuyActivity.this, "Please Select the Check Box Button For Verification", Toast.LENGTH_SHORT).show();
        }
    }

    private void AddAccountDetail(){

        // validation

        progressDialog.show();


        String holderName=etBuyHolderName.getText().toString();  //1
        String accountNumber=etBuyPackageBankAccountNumber.getText().toString();//2
        String vpa=etVPA.getText().toString();//3
//        String cardName=etCardName.getText().toString();//4
        String mobile=etBuyPackageMobile.getText().toString();//5
        String city=etCity.getText().toString(); //6
        String state=etState.getText().toString(); //7
        String pinCode=etPincode.getText().toString(); //8
        String address=etBuyPackageAddrress.getText().toString(); //9
        String ifcCode=etBuyPackageBankIfscCode.getText().toString(); //10

        if (holderName.isEmpty()){
            progressDialog.dismiss();
            etBuyHolderName.setError("Please Enter Account Holder Name");
            return;
        }



        if (accountNumber.isEmpty()){
            progressDialog.dismiss();
            etBuyPackageBankAccountNumber.setError("Please Enter Bank Account Number ");
            return;
        }

        if(!accountNumber.matches(regAccount)){
            progressDialog.dismiss();
            etBuyPackageBankAccountNumber.setError("In-Valid Bank Account Details");

            return;
        }

        if (vpa.isEmpty()){
            progressDialog.dismiss();

            etVPA.setError("Please Enter UPI");
            return;
        }


//        if (cardName.isEmpty()){
//            etCardName.setError("Please Enter Card Name");
//            return;
//        }

        if (mobile.isEmpty()){
            progressDialog.dismiss();

            etBuyPackageMobile.setError("Please Enter Mobile Number ");
            return;
        }

        if(!mobile.matches(MobileRegex)){
            progressDialog.dismiss();
            etBuyPackageMobile.setError("In-Valid Mobile Number");
            return;
        }

        if (city.isEmpty()){
            progressDialog.dismiss();
            etCity.setError("Please Enter City");
            return;
        }



        if (state.isEmpty()){
            progressDialog.dismiss();
            etState.setError("Please Enter State");
            return;
        }


        Pattern pattern=Pattern.compile(pincodeRegx);
        Matcher m=pattern.matcher(pinCode);

        if(!pinCode.matches(pincodeRegx)){
            progressDialog.dismiss();
            etPincode.setError("In-Valid Pincode");
            return;
        }


        if (pinCode.isEmpty()){
            progressDialog.dismiss();
            etPincode.setError("Please Enter Pin code");
            return;
        }


        if (ifcCode.isEmpty() ){
            progressDialog.dismiss();
            etBuyPackageBankIfscCode.setError("Please Enter IFSC CODE");
            return;
        }

        if (!ifcCode.matches(regIFSC)){
            etBuyPackageBankIfscCode.setError("Invalid IFSC ");
            progressDialog.dismiss();

            return;
        }

        if (address.isEmpty()){
            progressDialog.dismiss();
            etBuyPackageAddrress.setError("Please Enter Addrress");
            return;
        }

        SharedPreferences preferences = BuyActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        BenificalReq benificalReq=new BenificalReq();
        benificalReq.setIfsc(ifcCode);//1
        benificalReq.setBankAccount(accountNumber);//2
        benificalReq.setAddress1(address);//3
        benificalReq.setCity(city);//4
        benificalReq.setName(holderName); //5
//        benificalReq.setCardNo(cardName); //6
        benificalReq.setPhone(mobile); //7
        benificalReq.setPincode(pinCode); //8
        benificalReq.setVpa(vpa); //9
        benificalReq.setState(state); //10

        ApiCheck.api.getWalletMoney(retrivedToken,benificalReq).enqueue(new Callback<BenificialResponse>() {
            @Override
            public void onResponse(Call<BenificialResponse> call, Response<BenificialResponse> response) {
//                JsonObject res = response.body();
                if(response.isSuccessful()){


                    progressDialog.dismiss();
//                    isAccountAdded = true;
                    startActivity(new Intent(BuyActivity.this,WalletActivity.class));
                    finish();
                }else if(response.code()==422){
                    progressDialog.dismiss();
                    if (!vpa.matches(upiRegex)){
                        Toast.makeText(getApplicationContext(),"InValid UPI Address",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"InValid IFSC Code",Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(Call<BenificialResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(BuyActivity.this, "onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}