package com.example.payucart.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.activity.AddProfileActivity;
import com.example.payucart.adapter.SliderImageAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.api.ApiInterface;
import com.example.payucart.model.benificial.BenificalReq;
import com.example.payucart.model.benificial.BenificialResponse;
import com.example.payucart.model.benificial.UserBeneficiary;
import com.example.payucart.model.profile.UserReq;
import com.example.payucart.model.profile.UserResModel;
import com.example.payucart.model.slider.SliderImageBody;
import com.example.payucart.model.slider.SliderImageModel;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    @BindView(R.id.profile_edit_tv)
    TextView tvProfile;

    @BindView(R.id.profile_buy_plan)
    TextView tvProfileBuyPlan;

    @BindView(R.id.profile_plan_status)
    TextView tvProfilePlanStatus;

    @BindView(R.id.profile_in_active)
    ImageView imageViewInActive;

    @BindView(R.id.profile_available_balance)
    TextView tvAvailableBalance;

    private TextView tvProfileUserName;

    private TextView tvProfileMobile;
    private TextView tvProfileLanguage;
    private ProgressDialog progressDialog;
    private TextView tvBenificalAccount;
    private TextView tvTodayEarning,tvYesterdayProfit;
    private SliderView sliderView;
    private SliderImageAdapter sliderImageAdapter;
    private List<SliderImageBody> sliderImageBodies;


    private TextView etBuyPackageMobile; //1
    private TextView etBuyHolderName;//2
    private TextView etBuyPackageBankAccountNumber; //3
    private TextView etBuyPackageBankIfscCode;//4
    private TextView etBuyPackageAddrress;//5
    private TextView etVPA;//6
    private EditText etCardName;//7
    private TextView etCity;//8
    private TextView etPincode;//9
    private TextView etState;//10

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        tvProfileUserName=view.findViewById(R.id.profile_userName);
        tvProfileMobile=view.findViewById(R.id.profile_user_mobile);
        tvBenificalAccount=view.findViewById(R.id.profile_benificial_account);
        progressDialog= new ProgressDialog(getContext());
        tvTodayEarning=view.findViewById(R.id.profile_today_earning);
        tvYesterdayProfit=view.findViewById(R.id.profile_yesterday_profit);
        sliderView=view.findViewById(R.id.profile_slider);

//        etBuyPackageMobile=view.findViewById(R.id.buy_package_mobile_number); //1

        etBuyHolderName=view.findViewById(R.id.profile_user_account_name);//2

        etVPA=view.findViewById(R.id.profile_card_upi);//3

//        etCardName=findViewById(R.id.buy_package_bank_card_name);//4

        etCity=view.findViewById(R.id.profile_city);//5

        etPincode=view.findViewById(R.id.profile_pin_code_code);//6

        etBuyPackageBankAccountNumber=view.findViewById(R.id.profile_bank_account_number);//7

        etBuyPackageBankIfscCode=view.findViewById(R.id.profile_ifc_code);//8

        etBuyPackageAddrress=view.findViewById(R.id.profile_bank_address);//9

        etState=view.findViewById(R.id.profile_bank_state);//10


        addProfile();
        getBankDetails();
        getBanner();
        getProfileData();
        return view;
    }

    @OnClick(R.id.profile_edit_tv) void changeProfileStatus(){
        startActivity(new Intent(getContext(), AddProfileActivity.class));
    }

    @OnClick(R.id.profile_buy_plan) void addProfileAccount(){

    }

    private void addProfile() {
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

try{
    progressDialog.setTitle("AdsGrocy");
    progressDialog.setMessage("Loading...");
    progressDialog.show();

    ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {
            try{
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    boolean planBuy = false;
                    String mobile=response.body().getMobile();
                    String status=response.body().getStatus();

                    int  plan = response.body().getPlan();
                    String profileAvaliableBalance=response.body().getWallet();
                    String profileTodayEarning=response.body().gettEarning();
                    String profileYesterday=response.body().getyEarning();
                    String benficialstatus=response.body().getBeneficiary();
                    tvProfileMobile.setText(mobile);
                    tvBenificalAccount.setText(benficialstatus.toUpperCase());
                    tvAvailableBalance.setText("Available Balance \n ₹ "+profileAvaliableBalance);
                    tvTodayEarning.setText("Today Earning \n ₹ "+profileTodayEarning);
                    tvYesterdayProfit.setText("Yesterday's Profit \n ₹ "+profileYesterday);

                    if (status.equals("active")){
                        tvProfileBuyPlan.setText(String.valueOf(plan));
                        tvProfilePlanStatus.setText(status.toUpperCase());
                        imageViewInActive.setImageResource(R.drawable.ic_baseline_circle_24);

                    }else {
                        tvProfileBuyPlan.setText("0");
                        tvProfilePlanStatus.setText(status.toUpperCase());
                        imageViewInActive.setImageResource(R.drawable.red_inactive);
                    }

                }else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Error ", Toast.LENGTH_SHORT).show();

                }
            }catch(Exception e){
                Toast.makeText(getContext(),"Exception "+e.getMessage(),Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        public void onFailure(Call<UserResModel> call, Throwable t) {
            progressDialog.dismiss();
            Toast.makeText(getContext(),"Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();

        }
    });
}catch (Exception e){
    Toast.makeText(getContext(),"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();

}


    }

    private void getBankDetails(){
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        try{
            ApiCheck.api.getBankDetail(retrivedToken).enqueue(new Callback<BenificalReq>() {
                @Override
                public void onResponse(Call<BenificalReq> call, Response<BenificalReq> response) {
                    if (response.isSuccessful()){
                       String accountName=response.body().getName(); //1
                       String accountNumber=response.body().getBankAccount();//2
                       String accountUPI=response.body().getVpa();//3
                       String accountCity=response.body().getCity();//4
                       String accountIFCCODE=response.body().getIfsc();//5
                       String accountState=response.body().getState();//6
                       String accountPinCode=response.body().getPincode();//7
                       String accountAddress=response.body().getAddress1();//8

                       etBuyHolderName.setText(accountName);
                       etBuyPackageBankAccountNumber.setText(accountNumber);
                       etVPA.setText(accountUPI);
                       etCity.setText(accountCity);
                       etBuyPackageBankIfscCode.setText(accountIFCCODE);
                       etState.setText(accountState);
                       etPincode.setText(accountPinCode);
                       etBuyPackageAddrress.setText(accountAddress);

                    }else{

                    }
                }

                @Override
                public void onFailure(Call<BenificalReq> call, Throwable t) {
                    Toast.makeText(getContext(),"OnFailure : "+t.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }

    private void getBanner() {
        try{
            SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

            ApiCheck.api.getBanner(retrivedToken).enqueue(new Callback<SliderImageModel>() {
                @Override
                public void onResponse(Call<SliderImageModel> call, Response<SliderImageModel> response) {

                    try{
                        sliderImageBodies=new ArrayList<>();

                        SliderImageBody sliderImageBody=new SliderImageBody();

                        sliderImageBody.setBannerImage(sliderImageBody.getBannerImage());

                        sliderImageBodies.addAll(response.body().getImages());


                        sliderImageAdapter=new SliderImageAdapter(sliderImageBodies,getContext());

                        sliderView.setSliderAdapter(sliderImageAdapter);

                        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                        // below line is for setting auto cycle duration.
                        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);

                        // below line is for setting
                        // scroll time animation
                        sliderView.setScrollTimeInSec(3);

                        // below line is for setting auto
                        // cycle animation to our slider
                        sliderView.setAutoCycle(true);

                        // below line is use to start
                        // the animation of our slider view.
                        sliderView.startAutoCycle();
                        //Toast.makeText(getContext(),"URL : "+response.body().getImages(),Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Toast.makeText(getContext(),"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<SliderImageModel> call, Throwable t) {
                    Toast.makeText(getContext(),"Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){

        }

    }

    private void getProfileData(){
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
            @Override
            public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {

                if (response.isSuccessful()){


                    String name=response.body().getName();



                    tvProfileUserName.setText(name.toUpperCase());


                }

            }

            @Override
            public void onFailure(Call<UserResModel> call, Throwable t) {

            }
        });
    }



    public static String getSharedPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("payucart", Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static void setSharedPref(Context context, String key, String value){
        SharedPreferences prefs = context.getSharedPreferences("payukart", Context.MODE_PRIVATE);
        prefs.edit().putString(key,value).commit();
    }




}