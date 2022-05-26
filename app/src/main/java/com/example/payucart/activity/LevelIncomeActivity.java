package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.adapter.LevelIncomeAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.chart.ChartData;
import com.example.payucart.model.chart.ChartResponse;
import com.example.payucart.model.levelIncome.LevelIncomeModel;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LevelIncomeActivity extends AppCompatActivity {
    @BindView(R.id.level_income_back_btn)
    ImageView imgBackbtn;

    private List<ChartData> chartData;
    private LevelIncomeAdapter levelIncomeAdapter;

    private RecyclerView rvLevelIncome;
    private List<ChartData> levelIncomeModelList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_income);
        getSupportActionBar().hide();
        ButterKnife.bind(LevelIncomeActivity.this);

        init();

        getLevelChart();

    }

    @OnClick(R.id.level_income_back_btn) void BackButtom(){
        onBackPressed();
    }

    private void init(){
        levelIncomeModelList=new ArrayList<>();

        rvLevelIncome=findViewById(R.id.level_income_rv);
        rvLevelIncome.setHasFixedSize(true);
        rvLevelIncome.setLayoutManager(new LinearLayoutManager(LevelIncomeActivity.this,LinearLayoutManager.VERTICAL,false));
//        levelIncomeModelList.add(new LevelIncomeModel("Level 1","2","3","4","2"));
//        levelIncomeModelList.add(new LevelIncomeModel("Level 2","2","3","4","2"));
//        levelIncomeModelList.add(new LevelIncomeModel("Level 3","2","3","4","2"));
//        levelIncomeModelList.add(new LevelIncomeModel("Level 4","2","3","4","2"));
//        levelIncomeModelList.add(new LevelIncomeModel("Level 5","2","3","4","2"));
//        levelIncomeModelList.add(new LevelIncomeModel("Level 6","2","3","4","2"));
//        levelIncomeModelList.add(new LevelIncomeModel("Level 7","2","3","4","2"));
//        levelIncomeModelList.add(new LevelIncomeModel("Level 8","2","3","4","2"));
//




    }

    private void getLevelChart(){
        SharedPreferences preferences = LevelIncomeActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        ApiCheck.api.getChartResponse().enqueue(new Callback<ChartResponse>() {
            @Override
            public void onResponse(Call<ChartResponse> call, Response<ChartResponse> response) {

                if (response.isSuccessful()){
                    levelIncomeModelList=response.body().getChartDataList();

                    if (levelIncomeModelList==null){

                        Toast.makeText(LevelIncomeActivity.this, "You don't share app", Toast.LENGTH_SHORT).show();

                    }else{
                        for (int i =0; i<levelIncomeModelList.size(); i++){

                            levelIncomeAdapter=new LevelIncomeAdapter(levelIncomeModelList,LevelIncomeActivity.this);
                             rvLevelIncome.setAdapter(levelIncomeAdapter);
                             levelIncomeAdapter.notifyDataSetChanged();

                        }
                    }
                }else{

                    Toast.makeText(LevelIncomeActivity.this, "Error ", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ChartResponse> call, Throwable t) {
                Toast.makeText(LevelIncomeActivity.this,"onFailure : "+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}