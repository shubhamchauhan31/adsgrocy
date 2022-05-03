package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.adapter.SliderImageAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.fragment.CompletedTaskFragment;
import com.example.payucart.fragment.TodayEarning;
import com.example.payucart.fragment.WithDrawMoneyFragment;
import com.example.payucart.model.slider.SliderImageBody;
import com.example.payucart.model.slider.SliderImageModel;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackEarningActivity extends AppCompatActivity {
    @BindView(R.id.track_earning_today_earning_layout)
    LinearLayout layoutTrackTodayEarning;
    @BindView(R.id.track_earning_today_earning_tv)
    TextView tvTrackTodayEarning;

    @BindView(R.id.track_earning_back_btn)
    ImageView imageBackBtn;

    private SliderImageAdapter sliderImageAdapter;
    private List<SliderImageBody> sliderImageBodies;
    private SliderView sliderView;
    private LinearLayout layoutTrcking;
    private FrameLayout frameLayout;


//    @BindView(R.id.track_earning_completed_task_layout)
//    LinearLayout layoutTrackCompletedTask;
//    @BindView(R.id.track_earning_completed_task_tv)
//    TextView tvCompletedTask;
//
//    @BindView(R.id.track_earning_total_withdraw_layout)
//    LinearLayout layoutTrackWithDrawEarning;
//    @BindView(R.id.track_earning_total_withdraw_tv)
//    TextView tvTrackingWithdraw;


    private TodayEarning todayEarning;
    private CompletedTaskFragment completedTask;
    private WithDrawMoneyFragment withDrawMoneyFragment;
    private ProgressDialog progressDialog;
    private ImageView noTraction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_earning);
        getSupportActionBar().hide();
        ButterKnife.bind(TrackEarningActivity.this);
        todayEarning=new TodayEarning();
        sliderView=findViewById(R.id.track_earning_slider);
        noTraction=findViewById(R.id.track_earning_no_traction);
        layoutTrcking=findViewById(R.id.track_earning_today_earning_layout);
        progressDialog=new ProgressDialog(TrackEarningActivity.this);
        frameLayout=findViewById(R.id.track_earning_frame_layout);
        checktrackEarning();
        getBanner();
        setFragment(todayEarning);

    }

    @OnClick(R.id.track_earning_back_btn) void BackBtn(){
        startActivity(new Intent(TrackEarningActivity.this,HomePageActivity.class));
        finish();
    }

    @OnClick({R.id.track_earning_today_earning_layout,R.id.track_earning_today_earning_tv}) void TodayEarningScreen(){
        withDrawMoneyFragment=new WithDrawMoneyFragment();
       // setFragment(withDrawMoneyFragment);
        //setFragment(todayEarning);
    }
//    @OnClick({R.id.track_earning_completed_task_layout,R.id.track_earning_completed_task_tv}) void CompletedTaskScreen(){
//        completedTask=new CompletedTaskFragment();
//        setFragment(completedTask);
//    }

    private void setFragment(Fragment f){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.track_earning_frame_layout,f).commit();

    }

    private void getBanner() {
        try{
            progressDialog.setTitle("Payucart");
            progressDialog.setMessage("Loading Package...");
            progressDialog.show();
            SharedPreferences preferences = TrackEarningActivity.this.getSharedPreferences("MY_APP", MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

            ApiCheck.api.getBanner(retrivedToken).enqueue(new Callback<SliderImageModel>() {
                @Override
                public void onResponse(Call<SliderImageModel> call, Response<SliderImageModel> response) {

                    try{
                        sliderImageBodies=new ArrayList<>();

                        SliderImageBody sliderImageBody=new SliderImageBody();

                        sliderImageBody.setBannerImage(sliderImageBody.getBannerImage());

                        sliderImageBodies.addAll(response.body().getImages());


                        sliderImageAdapter=new SliderImageAdapter(sliderImageBodies,TrackEarningActivity.this);

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
                        Toast.makeText(TrackEarningActivity.this,"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<SliderImageModel> call, Throwable t) {
                    Toast.makeText(TrackEarningActivity.this,"Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(TrackEarningActivity.this,"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }

    public void checktrackEarning(){
        boolean isTrue=true;
        if (isTrue){
            layoutTrcking.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.VISIBLE);
            noTraction.setVisibility(View.GONE);

        }else {
            layoutTrcking.setVisibility(View.GONE);
            noTraction.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.GONE);

        }
    }

}