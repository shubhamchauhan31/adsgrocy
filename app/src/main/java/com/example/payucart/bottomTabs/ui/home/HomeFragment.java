package com.example.payucart.bottomTabs.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payucart.R;
import com.example.payucart.activity.AllPackageActivity;
import com.example.payucart.activity.BuyActivity;
import com.example.payucart.activity.EarnMoneyActivity;
import com.example.payucart.activity.HomePageActivity;
import com.example.payucart.activity.InstantDepositActivity;
import com.example.payucart.activity.InstantPayoutActivity;
import com.example.payucart.activity.InvitationActivity;
import com.example.payucart.activity.SendMoneyActivity;
import com.example.payucart.activity.TrackEarningActivity;
import com.example.payucart.activity.WalletActivity;
import com.example.payucart.adapter.MyBuyAdapter;
import com.example.payucart.adapter.SliderImageAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.api.ApiInterface;
import com.example.payucart.fragment.PackageFragments;
import com.example.payucart.interfaces.OnClickPackageList;
import com.example.payucart.model.buy.BuyBody;
import com.example.payucart.model.buy.BuyModel;
import com.example.payucart.model.changePassword.ChangePassReq;
import com.example.payucart.model.checkBenificalAccount.CheckBenifiaclAccountReq;
import com.example.payucart.model.checkBenificalAccount.CheckBenificalResponse;
import com.example.payucart.model.profile.UserResModel;
import com.example.payucart.model.slider.SliderImageBody;
import com.example.payucart.model.slider.SliderImageModel;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.w3c.dom.Text;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    boolean isBenificialAccountAdded = false;

    @BindView(R.id.home_send_money_layout)
    LinearLayout layoutSendMoney;
    @BindView(R.id.home_send_money_image_view)
    ImageView imageViewSendMoney;
    @BindView(R.id.home_send_money_tv)
    TextView tvSendMoney;

    @BindView(R.id.home_wallet_layout)
    LinearLayout layoutWallet;
    @BindView(R.id.home_wallet_image_view)
    ImageView imageViewWallet;
    @BindView(R.id.home_wallet_tv)
    TextView tvWallet;
    
    @BindView(R.id.home_track_earning_layout)
    LinearLayout layoutTrackEarning;
    @BindView(R.id.home_tracking_earning_image_view)
    ImageView trackEarningImageView;
    @BindView(R.id.home_track_earning_tv)
    TextView trackEarningTv;

//    @BindView(R.id.home_instant_deposit)
//    TextView tvHomeDeposit;
//    @BindView(R.id.home_instant_payout)
//    TextView tvHomePayout;


    @BindView(R.id.home_available_balance)
    TextView tvAvailableBalance;
    @BindView(R.id.home_yesterday_profit)
    TextView tvYestradayProfit;

    @BindView(R.id.home_today_earning)
    TextView tvTotalEarning;

    @BindView(R.id.home_completed_order)
    TextView tvCompletedOrder;

    @BindView(R.id.home_remaining_order)
    TextView tvRemainingOrder;

    @BindView(R.id.home_invitation_layout)
    LinearLayout invitationLayout;
    @BindView(R.id.home_invitation_image_view)
    ImageView imgInvitation;

    private SliderImageAdapter sliderImageAdapter;
    private List<SliderImageBody> sliderImageBodies;
    private SliderView sliderView;
    private MyBuyAdapter myBuyAdapter;

    private List<BuyBody> buyModelArrayList;
    private ProgressDialog progressDialog;

    private RecyclerView recyclerViewPackage;
    private TextView tvViewAll;
    private TextView tvUserName;



    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        sliderView=view.findViewById(R.id.home_slider);

        recyclerViewPackage=view.findViewById(R.id.home_package);

        progressDialog=new ProgressDialog(getContext());
        recyclerViewPackage.setHasFixedSize(false);
        tvViewAll=view.findViewById(R.id.home_view_all);
        tvUserName=view.findViewById(R.id.home_user_name);
        tvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getContext(), AllPackageActivity.class));

            }
        });

        recyclerViewPackage.setLayoutManager(new GridLayoutManager(getContext(), 2));
        getBanner();
        addPackage();
        getProfileData();
        return view;
    }

    private void getBanner() {
        try{
            progressDialog.setTitle("AdsGrocy");
            progressDialog.setMessage("Loading Package...");
            progressDialog.show();
            SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

            Log.e("Shubham",retrivedToken);

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

                        progressDialog.dismiss();


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

    public void addPackage() {
        try {
            progressDialog.setTitle("AdsGrocy");
            progressDialog.setMessage("Loading Package...");
            progressDialog.show();

            SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
            ApiCheck.api.getPackage(retrivedToken).enqueue(new Callback<BuyModel>() {
                @Override
                public void onResponse(Call<BuyModel> call, Response<BuyModel> response) {
                    if (response.isSuccessful()) {
                        buyModelArrayList=response.body().getResult();

                        for(int i=0; i<buyModelArrayList.size(); i++){
                            myBuyAdapter = new MyBuyAdapter(buyModelArrayList, getContext(), new OnClickPackageList() {
                                @Override
                                public void getPackageId(BuyBody buyBody) {

                                }

                                @Override
                                public void getPackageAmount(BuyBody buyBody) {

                                }
                            });
                            recyclerViewPackage.setAdapter(myBuyAdapter);
                            myBuyAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();

                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BuyModel> call, Throwable t) {
                    Toast.makeText(getContext(),"Failure"+t.getMessage(),Toast.LENGTH_SHORT);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick({R.id.home_send_money_layout,R.id.home_send_money_image_view,R.id.home_send_money_tv})
    void SendMoneyPage(){
        startActivity(new Intent(getActivity(), EarnMoneyActivity.class));

    }


    @OnClick({R.id.home_wallet_tv,R.id.home_wallet_image_view,R.id.home_wallet_layout}) void  walletPage(){


        CheckBenificalAccount();


//
//        SharedPreferences sharedPreferences=getContext().getSharedPreferences("benifical",MODE_PRIVATE);
//        boolean editor=sharedPreferences.getBoolean("benifical",false);
//        if (editor){
//            startActivity(new Intent(getContext(),WalletActivity.class));
//        }else{
//            startActivity(new Intent(getContext(),BuyActivity.class));
//        }

    }

    @OnClick({R.id.home_track_earning_tv,R.id.home_track_earning_layout,R.id.home_tracking_earning_image_view}) void trackEarningPage(){
        startActivity(new Intent(getContext(),TrackEarningActivity.class));
    }

//    @OnClick(R.id.home_instant_deposit) void instantDepositScreen(){
//        startActivity(new Intent(getContext(), InstantDepositActivity.class));
//    }
//    @OnClick(R.id.home_instant_payout) void instantPayucartScreen(){
//        startActivity(new Intent(getContext(), InstantPayoutActivity.class));
//    }

   @OnClick({R.id.home_invitation_image_view,R.id.home_invitation_layout}) void Invitation(){
        startActivity(new Intent(getContext(), InvitationActivity.class));
   }

   private void getProfileData(){
       SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
       String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
            @Override
            public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {

                if (response.isSuccessful()){

                    String availableBalance=response.body().getWallet();

                    String name=response.body().getName();

                    String yesterdayProfit=response.body().getyEarning();

                    String todayEarning=response.body().gettEarning();

                    int completedOrder=response.body().getTcomplete();

                    int remainingOrder=response.body().getPerDayAddLimit();

                    tvAvailableBalance.setText(" Available Balance \n ₹ "+availableBalance);
                    tvUserName.setText(name);
                    tvYestradayProfit.setText("Yesterday's Profit \n ₹ "+yesterdayProfit);
                    tvTotalEarning.setText(String.valueOf(todayEarning));
                    tvCompletedOrder.setText(String.valueOf(completedOrder));
                    tvRemainingOrder.setText(String.valueOf(remainingOrder));

                }

            }

            @Override
            public void onFailure(Call<UserResModel> call, Throwable t) {

            }
        });
   }

    private void CheckBenificalAccount(){
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        SharedPreferences mobilePerf = getContext().getSharedPreferences("MOBILE", Context.MODE_PRIVATE);

        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String mobile=mobilePerf.getString("MOBILE",null);

        CheckBenifiaclAccountReq checkBenifiaclAccountReq=new CheckBenifiaclAccountReq();
        checkBenifiaclAccountReq.setMobile(mobile);

        try{
            ApiCheck.api.CheckBankAccount(retrivedToken,checkBenifiaclAccountReq).enqueue(new Callback<CheckBenificalResponse>() {
                @Override
                public void onResponse(Call<CheckBenificalResponse> call, Response<CheckBenificalResponse> response) {

                    Log.d("shub", "onResponse: "+response);

                    try {

                        if (response.isSuccessful()){


                                isBenificialAccountAdded = true;

                                startActivity(new Intent(getContext(),WalletActivity.class));




                        }
                        else {
                            isBenificialAccountAdded = false;
                            startActivity(new Intent(getContext(),BuyActivity.class));

                        }

                    }catch (Exception e){
                        Toast.makeText(getContext(),"Exception "+e.getMessage(),Toast.LENGTH_SHORT).show();
                       // startActivity(new Intent(getContext(),BuyActivity.class));

                    }



                }

                @Override
                public void onFailure(Call<CheckBenificalResponse> call, Throwable t) {

                    Toast.makeText(getContext(),"Exception "+t.getMessage(),Toast.LENGTH_SHORT).show();


                }
            });

        }catch (Exception e){

        Toast.makeText(getContext(),"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();

        }
//        ApiCheck.api.CheckBankAccount(retrivedToken,checkBenifiaclAccountReq).enqueue(new Callback<CheckBenificalResponse>() {
//            @Override
//            public void onResponse(Call<CheckBenificalResponse> call, Response<CheckBenificalResponse> response) {
//
//                Log.d("shub", "onResponse: "+response);
//
//                if(response.isSuccessful()){
//
//                    isBenificialAccountAdded = true;
//
//                    startActivity(new Intent(getContext(),WalletActivity.class));
//
//
//                }
//                else {
//                    isBenificialAccountAdded = false;
//                    startActivity(new Intent(getContext(),BuyActivity.class));
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<CheckBenificalResponse> call, Throwable t) {
//
//            }
//        });

    }

    public static String getMobile(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("payucart", Context.MODE_PRIVATE);
        return prefs.getString(key, "mobile");
    }
}