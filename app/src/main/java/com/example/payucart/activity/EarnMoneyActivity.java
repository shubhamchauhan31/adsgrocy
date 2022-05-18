package com.example.payucart.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.payucart.R;
import com.example.payucart.adapter.ScratchCardAdapter;
import com.example.payucart.adapter.VedioAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.profile.UserResModel;
import com.example.payucart.model.rewards.Check;
import com.example.payucart.model.rewards.RewardResponse;
import com.example.payucart.model.scratchCard.ScratchCardData;
import com.example.payucart.model.scratchCard.ScratchCardResponse;
import com.example.payucart.model.scratchModel.ScretchCardModel;
import com.example.payucart.model.video.VedioResponse;
import com.example.payucart.model.video.VideoData;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.protobuf.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarnMoneyActivity extends AppCompatActivity implements OnUserEarnedRewardListener {
    private TextView tvShowAdd;
    private TextView tvSubmitAdd;
    private ImageView imgEarnMoneyBackBtn;
    private RewardedInterstitialAd rewardedInterstitialAd;
    private RewardedAd mRewardedAd;
    private ProgressDialog progressDialog;
    private TextView tvTask;
    private TextView tvAmount;
    private TextView earnMoneyBuyPackage;
    private LinearLayout layoutShowAds,layoutBuy;
    private TextView tvCompletedTask;
    private TextView tvTodayProfit;
    private int task;
    private VideoView videoView;
    private RecyclerView recyclerviewVedioView;
    private List<VideoData> vedioResponses;
    private VedioAdapter vedioAdapter;
    private ImageView imgBuyPackage;
    private List<ScretchCardModel> scretchCardModels;
    private ScratchCardAdapter scratchCardAdapter;
    private List<ScratchCardData> scratchCardData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_earn_money);
        tvShowAdd=findViewById(R.id.eran_money_show_add);
//        tvSubmitAdd=findViewById(R.id.eran_money_show_add_submit);
        imgEarnMoneyBackBtn=findViewById(R.id.earn_money_back_btn);
        progressDialog=new ProgressDialog(EarnMoneyActivity.this);
        tvTask=findViewById(R.id.earn_money_task);
        tvAmount=findViewById(R.id.earn_money_amount);
        earnMoneyBuyPackage=findViewById(R.id.eran_money_show_buy);
        layoutBuy=findViewById(R.id.earn_money_buy_package_layout);
        layoutShowAds=findViewById(R.id.earn_money_layout);
        tvCompletedTask=findViewById(R.id.earn_money_completed_task);
        tvTodayProfit=findViewById(R.id.earn_money_today_profit);
//        videoView=findViewById(R.id.earn_money_watch_video);
        imgBuyPackage=findViewById(R.id.earn_money_no_task);
        scretchCardModels=new ArrayList<>();

        recyclerviewVedioView=findViewById(R.id.earn_money_vedio_recyclerview);
        recyclerviewVedioView.setHasFixedSize(false);
//        recyclerviewVedioView.setLayoutManager(new LinearLayoutManager(EarnMoneyActivity.this,LinearLayoutManager.VERTICAL,false));
        recyclerviewVedioView.setLayoutManager(new GridLayoutManager(EarnMoneyActivity.this,2));

//
        vedioResponses=new ArrayList<>();
        scratchCardData=new ArrayList<>();
//        scretchCardModels.add(new ScretchCardModel(R.drawable.ic_cup));
//        scretchCardModels.add(new ScretchCardModel(R.drawable.ic_cup));
//        scretchCardModels.add(new ScretchCardModel(R.drawable.ic_cup));
//        scretchCardModels.add(new ScretchCardModel(R.drawable.ic_cup));
//        scretchCardModels.add(new ScretchCardModel(R.drawable.ic_cup));
//        scretchCardModels.add(new ScretchCardModel(R.drawable.ic_cup));
//        scretchCardModels.add(new ScretchCardModel(R.drawable.ic_cup));
//        scretchCardModels.add(new ScretchCardModel(R.drawable.ic_cup));
//        scretchCardModels.add(new ScretchCardModel(R.drawable.ic_cup));



        checkValidation();


        earnMoneyBuyPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EarnMoneyActivity.this,AllPackageActivity.class));
                finish();
            }
        });
       getTask();

        imgEarnMoneyBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EarnMoneyActivity.this,HomePageActivity.class));
                finish();
            }
        });

        tvShowAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int totalAdd=0;



            }
        });


    }


    public void watchVideo(){

        MediaController mediaController=new MediaController(EarnMoneyActivity.this);
        mediaController.setAnchorView(videoView);
        Uri uri=Uri.parse("http://techslides.com/demos/sample-videos/small.mp4");
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    public void adMob(){

//        Toast.makeText(getApplicationContext(),"Show Add ",Toast.LENGTH_SHORT).show();

        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-9619547171870234/5118519258",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                        // Handle the error.
                        mRewardedAd = null;
                        Toast.makeText(getApplicationContext(), "loadAdError : "+loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        rewardedAd.show(EarnMoneyActivity.this,EarnMoneyActivity.this);
//                        Toast.makeText(getApplicationContext(), " : "+rewardedAd.getRewardItem(), Toast.LENGTH_SHORT).show();

//                        progressDialog.dismiss();

                    }
                });
    }

    @Override
    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
        String rewardType = rewardItem.getType();
        int rewardAmount = rewardItem.getAmount();

//        Toast.makeText(EarnMoneyActivity.this,"Money Added : "+rewardAmount,Toast.LENGTH_SHORT).show();
        // hit api to add money
      //  getRewards();



    }

    public void checkTask(){
        if(task==0){
            imgBuyPackage.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Your Task Completed  "+task, Toast.LENGTH_SHORT).show();
        }else{
            //  adMob();
//                    watchVideo();
            getVideo();
            imgBuyPackage.setVisibility(View.GONE);


//            startActivity(new Intent(EarnMoneyActivity.this,VedioViewActivity.class));
//            finish();

            //    getRewards();

        }
    }


    private void checkValidation(){
//        SharedPreferences preferences = EarnMoneyActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
//        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
//        ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
//            @Override
//            public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {
//                if (response.isSuccessful()){
//                    String checkPackage=response.body().getStatus();
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResModel> call, Throwable t) {
//
//            }
//        });
//        boolean isTrue=true;
//        if (isTrue){
//            layoutShowAds.setVisibility(View.VISIBLE);
//            layoutBuy.setVisibility(View.GONE);
//
//        }else{
//            layoutShowAds.setVisibility(View.GONE);
//            layoutBuy.setVisibility(View.VISIBLE);
//        }
    }


    private void getTask(){
        try {
            SharedPreferences preferences = EarnMoneyActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
            ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
                @Override
                public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {
                    if (response.isSuccessful()){
                        task=response.body().getPerDayAddLimit();
                        String amount=response.body().getWallet();
                        String todayEarning=response.body().gettEarning();
                        int completedOrder=response.body().getTcomplete();
                        tvTask.setText("Daily Task : "+task);
                        tvAmount.setText(" ₹ "+amount);
                        tvTodayProfit.setText(String.valueOf(todayEarning));
                        tvCompletedTask.setText(String.valueOf(completedOrder));
                        checkTask();

                    }else{
                        Toast.makeText(getApplicationContext(), "Error : ", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<UserResModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"onFailure "+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(EarnMoneyActivity.this, "Exception :  "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private  void  getVideo(){
        SharedPreferences preferences = EarnMoneyActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        ApiCheck.api.getViedo(retrivedToken).enqueue(new Callback<VedioResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<VedioResponse> call, Response<VedioResponse> response) {
                try{

                    if (response.isSuccessful()){
                        try{
                            vedioResponses = response.body().getVideos();
                            for(int i=0; i<vedioResponses.size(); i++){
                                //   Log.d("Shu", "onResponse: "+vedioResponses.get(i).getAdminVideo());
                                vedioAdapter=new VedioAdapter(vedioResponses,EarnMoneyActivity.this);
                               // vedioAdapter.notifyDataSetChanged();

                                recyclerviewVedioView.setAdapter(vedioAdapter);
                                vedioAdapter.notifyDataSetChanged();
                            }
                        }catch (Exception e){
                            Toast.makeText(EarnMoneyActivity.this,"Exception "+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }

                 //      Toast.makeText(EarnMoneyActivity.this,"hfedkc"+vedioResponses,Toast.LENGTH_SHORT).show();


                      //  vedioResponses.addAll(response.body().getVideos());
                     //   VedioResponse v=response.body().getVideos();


//                       for(int i=0; i<vedio.length(); i++){
////
//                       }
                    }else{
                              Toast.makeText(EarnMoneyActivity.this,"Error From Server",Toast.LENGTH_SHORT).show();

                    }

                }catch (Exception e){
                    Toast.makeText(EarnMoneyActivity.this, "Exception : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VedioResponse> call, Throwable t) {
                Toast.makeText(EarnMoneyActivity.this, "onFailure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void getScratchCard(){
//
//        ApiCheck.api.getScratchCardDetails().enqueue(new Callback<ScratchCardResponse>() {
//            @Override
//            public void onResponse(Call<ScratchCardResponse> call, Response<ScratchCardResponse> response) {
//
//                if (response.isSuccessful()){
////
//                    try{
//                        scratchCardData=response.body().getScratchCardData();
//                        if (scratchCardData.equals(0) || scratchCardData.equals("0") || scratchCardData==null ||scratchCardData.isEmpty() || scratchCardData.size()==0){
//                            Toast.makeText(EarnMoneyActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
//                        }else{
//                            for (int i=0; i<scratchCardData.size(); i++){
//                                scratchCardAdapter=new ScratchCardAdapter(EarnMoneyActivity.this,scratchCardData);
//                                recyclerviewVedioView.setAdapter(scratchCardAdapter);
//                                scratchCardAdapter.notifyDataSetChanged();
//                            }
//                        }
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ScratchCardResponse> call, Throwable t) {
//                Toast.makeText(EarnMoneyActivity.this, "onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    @Override
    protected void onResume() {
        super.onResume();
        getTask();


    }


}