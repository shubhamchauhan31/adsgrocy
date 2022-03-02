package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.payucart.R;

public class AddMoneyActivity extends AppCompatActivity {

    private WebView webViewAddMoney;
    String url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_money);
        webViewAddMoney=findViewById(R.id.add_money_profile_web_view);
        webViewAddMoney.getSettings().setJavaScriptEnabled(true);

        startWebView();


    }

    private void startWebView() {
        url=getIntent().getExtras().getString("addmoney");
        webViewAddMoney.setWebViewClient(new WebViewClient() {
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

            }

        });

//       Load url in webView
        webViewAddMoney.loadUrl(url);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}