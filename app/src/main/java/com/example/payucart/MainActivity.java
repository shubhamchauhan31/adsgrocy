package com.example.payucart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.payucart.activity.HomePageActivity;
import com.example.payucart.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        getSupportActionBar().hide(); // hide the action bar


        setContentView(R.layout.activity_main);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isConnected()){
//                    if (getSharedPrefBoolean(MainActivity.this, "hasLogin")) {
//                        startActivity(new Intent(MainActivity.this, HomePageActivity.class));
//                        finish();
//
                    SharedPreferences sharedPreferences = getSharedPreferences("hasLogin",MODE_PRIVATE);
                    boolean editor=sharedPreferences.getBoolean("hasLogin",false);



                    if (editor){
                        startActivity(new Intent(MainActivity.this,HomePageActivity.class));
                        finish();
                    }else{
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
//                    } else {
//                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(i);
//                        finish();
//
                   }
                else{
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        },2000);
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    public static boolean getSharedPrefBoolean(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("hasLogin",Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }
}