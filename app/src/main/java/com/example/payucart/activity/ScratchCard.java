package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.payucart.R;
import com.example.payucart.adapter.ScratchCardAdapter;
import com.example.payucart.model.scratchModel.ScretchCardModel;

import java.util.ArrayList;
import java.util.List;

public class ScratchCard extends AppCompatActivity {
    private RecyclerView scratchRecyclerView;
    private List<ScretchCardModel> scretchCardModels;
    private ScratchCardAdapter scratchCardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_card);
        getSupportActionBar().hide();
        scretchCardModels=new ArrayList<>();
        scratchRecyclerView=findViewById(R.id.scratchCardRecyclerview);
        scratchRecyclerView.setLayoutManager(new GridLayoutManager(ScratchCard.this,2));
//        scretchCardModels.add(new ScretchCardModel("Rs.123"));
//        scretchCardModels.add(new ScretchCardModel("Rs.123"));
//        scretchCardModels.add(new ScretchCardModel("Rs.123"));
//        scretchCardModels.add(new ScretchCardModel("Rs.123"));
//        scretchCardModels.add(new ScretchCardModel("Rs.123"));
//        scretchCardModels.add(new ScretchCardModel("Rs.123"));
//        scretchCardModels.add(new ScretchCardModel("Rs.123"));
//        scretchCardModels.add(new ScretchCardModel("Rs.123"));
        scratchCardAdapter=new ScratchCardAdapter(ScratchCard.this, scretchCardModels);
        scratchRecyclerView.setAdapter(scratchCardAdapter);







    }
}