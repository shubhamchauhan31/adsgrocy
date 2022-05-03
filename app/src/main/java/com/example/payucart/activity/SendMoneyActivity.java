package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.payucart.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendMoneyActivity extends AppCompatActivity {
    @BindView(R.id.send_money_back_btn)
    ImageView sendMoneyBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        getSupportActionBar().hide(); // hide the action bar
        setContentView(R.layout.activity_send_money);
        ButterKnife.bind(SendMoneyActivity.this);

    }
    @OnClick(R.id.send_money_back_btn) void sendMoneyBack(){
        startActivity(new Intent(SendMoneyActivity.this,HomePageActivity.class));
        finish();
    }
}