package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.howTouseApp.HowTOUseResponse;
import com.example.payucart.model.howTouseApp.HowToUSeApp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HowToUseViedo extends AppCompatActivity {
    private VideoView videoViewAbout;
    private ProgressDialog progressDialog;
    private List<HowToUSeApp> howToUSeAppList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use_viedo);
        getSupportActionBar().hide();
        init();
    }
    private void init(){
        videoViewAbout=findViewById(R.id.how_to_use_video);
        howToUSeAppList=new ArrayList<>();

        progressDialog=new ProgressDialog(HowToUseViedo.this);
        videoViewAbout.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

//                HowToUseViedo.this.onBackPressed();
                onBackPressed();
            }
        });

        WatchVideo();
    }

    private void WatchVideo(){

        progressDialog.show();
//        String getUrl=getIntent().getExtras().getString("url");
//        MediaController mediaController=new MediaController(VedioViewActivity.this);
//        mediaController.setAnchorView(videoView);


        ApiCheck.api.howToUSeApp().enqueue(new Callback<HowTOUseResponse>() {
            @Override
            public void onResponse(Call<HowTOUseResponse> call, Response<HowTOUseResponse> response) {
                if (response.isSuccessful()){
//                    howToUSeAppList.add(response.body().getHowToUSeApp());
                    String useApp=response.body().getHowToUSeApp().getUrl();
                    Uri uri=Uri.parse("http://www.adsgrocy.com/"+useApp);
                    //  videoView.setMediaController(mediaController);
                    videoViewAbout.setVideoURI(uri);
                    videoViewAbout.start();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<HowTOUseResponse> call, Throwable t) {
                Toast.makeText(HowToUseViedo.this,"onFailure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}