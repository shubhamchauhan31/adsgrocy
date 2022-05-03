package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.rewards.RewardResponse;
import com.example.payucart.model.video.VedioResponse;
import com.example.payucart.model.video.VideoData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VedioViewActivity extends AppCompatActivity {
    private VideoView videoView;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_vedio_view);

        videoView=findViewById(R.id.video_view_watch);
        progressDialog=new ProgressDialog(VedioViewActivity.this);
        progressDialog.setTitle("AdsGrocy");
        progressDialog.setMessage("Loading Please Wait...");

        watchVideo();
      //  getVideo();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                VedioViewActivity.this.onBackPressed();
                getRewards();
                finish();
            }
        });
    }


    private  void  getVideo(){
        SharedPreferences preferences = VedioViewActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        ApiCheck.api.getViedo(retrivedToken).enqueue(new Callback<VedioResponse>() {
            @Override
            public void onResponse(Call<VedioResponse> call, Response<VedioResponse> response) {
                try{

                    if (response.isSuccessful()){
                        String vedio=response.body().getVideos().toString();
                        Toast.makeText(VedioViewActivity.this,"List"+vedio,Toast.LENGTH_SHORT).show();

//                       for(int i=0; i<vedio.length(); i++){
////
//                       }
                    }

                }catch (Exception e){
                    Toast.makeText(VedioViewActivity.this, "Exception : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VedioResponse> call, Throwable t) {
                Toast.makeText(VedioViewActivity.this, "onFailure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void watchVideo(){

        progressDialog.show();
        String getUrl=getIntent().getExtras().getString("url");
//        MediaController mediaController=new MediaController(VedioViewActivity.this);
//        mediaController.setAnchorView(videoView);
        Uri uri=Uri.parse(getUrl);
      //  videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.start();
        progressDialog.dismiss();

    }

    public void getRewards(){
        String id=getIntent().getExtras().getString("id");
        VideoData videoData=new VideoData();
        videoData.setId(id);

        SharedPreferences preferences = VedioViewActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        try{
            ApiCheck.api.getRewards(retrivedToken,videoData).enqueue(new Callback<RewardResponse>() {
                @Override
                public void onResponse(Call<RewardResponse> call, Response<RewardResponse> response) {
                    if (response.isSuccessful()){
                        String amount=response.body().getCheck().getWallet();
//                        int task=response.body().getCheck().getPerDayAddLimit();
//                        tvTask.setText(String.valueOf("Daily Task : "+task));
                        Intent intent=new Intent(VedioViewActivity.this,EarnMoneyActivity.class);
                        intent.putExtra("amount",amount);
                        VedioViewActivity.this.startActivity(intent);

                        //           tvAmount.setText(amount);

//                    Toast.makeText(getApplicationContext(), "Your Rewards"+response.body().getCheck().getCommission(), Toast.LENGTH_SHORT).show();
                    }else{

                    }
                }

                @Override
                public void onFailure(Call<RewardResponse> call, Throwable t) {
                    Toast.makeText(VedioViewActivity.this, "OnFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }catch(Exception e){
            Toast.makeText(VedioViewActivity.this, "Exception : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}