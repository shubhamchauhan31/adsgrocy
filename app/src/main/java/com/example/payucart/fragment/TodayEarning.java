package com.example.payucart.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.activity.InstantPayoutActivity;
import com.example.payucart.adapter.MyWithDrawAdapter;
import com.example.payucart.adapter.TodayEarningAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.profile.UserResModel;
import com.example.payucart.model.today_earn.TodayEarningModel;
import com.example.payucart.model.transfer.TransferResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodayEarning extends Fragment {

    private RecyclerView recyclerViewTodayEarning;
    private List<TransferResponse> transferResponses;
    private MyWithDrawAdapter myWithDrawAdapter;
    private TextView tvWallet;
    private ProgressDialog progressDialog;




    public TodayEarning() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_today_earning, container, false);
        recyclerViewTodayEarning=view.findViewById(R.id.today_earning_recyclerview);
        recyclerViewTodayEarning.setHasFixedSize(true);
        recyclerViewTodayEarning.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        tvWallet=view.findViewById(R.id.total_earning_wallet);
        progressDialog=new ProgressDialog(getContext());
        getAllTraction();
        addMoneyInWallet();
        return view;
    }
    private void getAllTraction(){
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        ApiCheck.api.getTransaction(retrivedToken).enqueue(new Callback<List<TransferResponse>>() {
            @Override
            public void onResponse(Call<List<TransferResponse>> call, Response<List<TransferResponse>> response) {
                if (response.isSuccessful()){
                    transferResponses=response.body();
                    myWithDrawAdapter=new MyWithDrawAdapter(transferResponses,getContext());
                    recyclerViewTodayEarning.setAdapter(myWithDrawAdapter);
                    myWithDrawAdapter.notifyDataSetChanged();

                }else{
                    Toast.makeText(getContext(), "Error : -", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<TransferResponse>> call, Throwable t) {
                Toast.makeText(getContext(),"OnFailure"+t.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.print("OnFailure"+t.getMessage());
                Log.d("Shu","Shu"+t.getMessage());
            }
        });
    }

    private void addMoneyInWallet(){

        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
            @Override
            public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {
                UserResModel userResModel=response.body();
                if (response.isSuccessful()){
                    String wallet= String.valueOf(userResModel.getWallet());
                    tvWallet.setText("₹ "+wallet);
                    progressDialog.dismiss();

                }else{
                    progressDialog.dismiss();

                    Toast.makeText(getContext(), "Error :-"+userResModel.getWallet(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserResModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),"Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}