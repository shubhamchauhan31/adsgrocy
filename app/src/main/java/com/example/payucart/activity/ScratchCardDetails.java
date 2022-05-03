package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.payucart.R;
import com.example.payucart.scratchCard.Utils;

public class ScratchCardDetails extends AppCompatActivity {

    private com.example.payucart.scratchCard.ScratchCard scratchCardWash;
    TextView codeTxt;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_card_details);
        getSupportActionBar().hide();

        scratchCardWash=findViewById(R.id.scratchCard);
        codeTxt = findViewById(R.id.codeTxt);
        number = codeTxt.getText().toString();
        codeTxt.setText(number);
        codeTxt.setText(Utils.generateNewCode());
    }

    private void scratch(boolean isScratched) {
        if (isScratched) {
            scratchCardWash.setVisibility(View.INVISIBLE);
        } else {
            scratchCardWash.setVisibility(View.VISIBLE);
        }
    }

    private void handleListeners() {
        scratchCardWash.setOnScratchListener(new com.example.payucart.scratchCard.ScratchCard.OnScratchListener() {
            @Override
            public void onScratch(com.example.payucart.scratchCard.ScratchCard scratchCard, float visiblePercent) {
                if (visiblePercent > 0.8) {
                    scratch(true);
                }

            }
        });
    }
}