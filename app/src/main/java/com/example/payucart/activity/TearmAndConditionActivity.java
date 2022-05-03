package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;

public class TearmAndConditionActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressDialog progressDialog;
    private ImageView imgBackBtn;

    private String url= ApiCheck.BASE_URL+"/term&condition";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tearm_and_condition);
        getSupportActionBar().hide();
        webView=findViewById(R.id.term_and_condition_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        progressDialog=new ProgressDialog(TearmAndConditionActivity.this);
        progressDialog.setTitle("AdsGrocy");
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        imgBackBtn=findViewById(R.id.term_and_condition_back_btn);

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TearmAndConditionActivity.this,HomePageActivity.class));
                finish();
            }
        });

        startWebView();
    }
    private void startWebView() {

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                progressDialog.dismiss();
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
        progressDialog.dismiss();

    }

}