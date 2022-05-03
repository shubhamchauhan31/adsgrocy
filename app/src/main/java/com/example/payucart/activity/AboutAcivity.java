package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;

public class AboutAcivity extends AppCompatActivity {
    private WebView aboutWebView;
    private String url= ApiCheck.BASE_URL +"/about";
    private ProgressDialog progressDialog;
    private ImageView imageViewBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_acivity);
        getSupportActionBar().hide();
        aboutWebView=findViewById(R.id.about_app_web_view);
        imageViewBackBtn=findViewById(R.id.about_us_back_btn);
        aboutWebView.getSettings().setJavaScriptEnabled(true);
        progressDialog=new ProgressDialog(AboutAcivity.this);
        startWebView();

        imageViewBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutAcivity.this,HomePageActivity.class));
                finish();
            }
        });
    }


    private void startWebView() {

        progressDialog.setTitle("AdsGrocy");
        progressDialog.setMessage("Loading...");

        aboutWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

//                progressDialog.dismiss();
                return true;
            }

            //Show loader on url load
            @Override
            public void onLoadResource(WebView view, String url) {
//                progressDialog.show();

            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }

        });
        progressDialog.show();

//       Load url in webView
        aboutWebView.loadUrl(url);
        progressDialog.dismiss();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}