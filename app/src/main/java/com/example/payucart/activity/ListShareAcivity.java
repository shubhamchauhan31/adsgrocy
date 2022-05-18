package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.payucart.R;
import com.example.payucart.adapter.ShareListAdapter;
import com.example.payucart.model.shareListModel.ReferListModel;

import java.util.ArrayList;
import java.util.List;

public class ListShareAcivity extends AppCompatActivity {
    private RecyclerView rvShareList;
    private ImageView imgBackBtn;
    private ShareListAdapter shareListAdapter;
    private List<ReferListModel> referListModelList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_share_acivity);
        getSupportActionBar().hide();
        init();
    }

    private void init(){
        referListModelList=new ArrayList<>();
        imgBackBtn=findViewById(R.id.list_share_back_btn);
        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rvShareList=findViewById(R.id.list_share_rv);
        rvShareList.setHasFixedSize(true);
        rvShareList.setLayoutManager(new LinearLayoutManager(ListShareAcivity.this,LinearLayoutManager.VERTICAL,false));
        referListModelList.add(new ReferListModel("Shubham","Rs.1234"));
        referListModelList.add(new ReferListModel("Shubham","Rs.1234"));
        referListModelList.add(new ReferListModel("Shubham","Rs.1234"));
        referListModelList.add(new ReferListModel("Shubham","Rs.1234"));
        referListModelList.add(new ReferListModel("Shubham","Rs.1234"));
        referListModelList.add(new ReferListModel("Shubham","Rs.1234"));
        referListModelList.add(new ReferListModel("Shubham","Rs.1234"));
        referListModelList.add(new ReferListModel("Shubham","Rs.1234"));
        referListModelList.add(new ReferListModel("Shubham","Rs.1234"));
        referListModelList.add(new ReferListModel("Shubham","Rs.1234"));
        shareListAdapter=new ShareListAdapter(referListModelList,ListShareAcivity.this);
        rvShareList.setAdapter(shareListAdapter);



    }
}