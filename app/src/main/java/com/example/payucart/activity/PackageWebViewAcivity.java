package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.payucart.R;

public class PackageWebViewAcivity extends AppCompatActivity {
    private WebView webViewPayment;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_package_web_view_acivity);
        webViewPayment=findViewById(R.id.payment_web_view);
        WebSettings webSettings=webViewPayment.getSettings();
      //  webSettings.setJavaScriptEnabled(true);
        webViewPayment.getSettings().setJavaScriptEnabled(true);


        startWebView();
    }

    private void startWebView() {
        url=getIntent().getExtras().getString("payment");


        webViewPayment.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            @Override
            public void onLoadResource(WebView view, String url) {
            }

            @Override
            public void onPageFinished(WebView view, String url) {
              //  startActivity(new Intent(PackageWebViewAcivity.this,WalletActivity.class));

            }

        });

//       Load url in webView
        webViewPayment.loadUrl(url);

    }
}