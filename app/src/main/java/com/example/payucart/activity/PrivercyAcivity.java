package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;

public class PrivercyAcivity extends AppCompatActivity {
    private WebView webView;
    private String url= ApiCheck.BASE_URL+"/privacy";
    private ProgressDialog progressDialog;

    private ImageView imgBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privercy_acivity);
        getSupportActionBar().hide();
        webView=findViewById(R.id.privecy_web_view);
        imgBackBtn=findViewById(R.id.privecy_back_img);
        webView.getSettings().setJavaScriptEnabled(true);


        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrivercyAcivity.this,HomePageActivity.class));
                finish();
            }
        });

        progressDialog=new ProgressDialog(PrivercyAcivity.this);
        startWebView();
    }

    private void startWebView() {
        progressDialog.setTitle("Payucart");
        progressDialog.setMessage("Loading...");
        progressDialog.show();

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