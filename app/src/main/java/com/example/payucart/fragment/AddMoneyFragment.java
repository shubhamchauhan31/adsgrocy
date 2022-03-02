package com.example.payucart.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.activity.AddMoneyActivity;
import com.example.payucart.activity.AddProfileActivity;
import com.example.payucart.adapter.AddMoneyAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.addmoney.AddMoneyModel;
import com.example.payucart.model.addmoney.AddMoneyReq;
import com.example.payucart.model.addmoney.AddMoneyResponse;
import com.example.payucart.model.profile.UserResModel;
import com.google.protobuf.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMoneyFragment extends Fragment {
    private RecyclerView recyclerViewAddMoney;
    private ArrayList<AddMoneyModel> addMoneyModels;
    private AddMoneyAdapter addMoneyAdapter;
    private EditText etAddMoney;

    private ProgressDialog progressDialog;


    private TextView textViewAddPayment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_money, container, false);

        recyclerViewAddMoney=view.findViewById(R.id.add_money_recyclerview);
        textViewAddPayment=view.findViewById(R.id.add_money_payment_submit);
        etAddMoney=view.findViewById(R.id.add_money_amount);
        progressDialog=new ProgressDialog(getContext());


        textViewAddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoney();
            }
        });

        recyclerViewAddMoney.setHasFixedSize(true);

        recyclerViewAddMoney.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

//        addMoneyModels=new ArrayList<>();
//        addMoneyModels.add(new AddMoneyModel(R.drawable.logo,"Payucart","xxxxxxxxxxxxx123","1234.98"));
//        addMoneyModels.add(new AddMoneyModel(R.drawable.logo,"SBI","xxxxxxxxxxxxx123","1234.98"));
//        addMoneyModels.add(new AddMoneyModel(R.drawable.logo,"PNB","xxxxxxxxxxxxx123","1234.98"));
//        addMoneyModels.add(new AddMoneyModel(R.drawable.logo,"SBI","xxxxxxxxxxxxx123","1234.98"));
//        addMoneyModels.add(new AddMoneyModel(R.drawable.logo,"BOI","xxxxxxxxxxxxx123","1234.98"));
//        addMoneyModels.add(new AddMoneyModel(R.drawable.logo,"SBI","xxxxxxxxxxxxx123","1234.98"));
//        addMoneyModels.add(new AddMoneyModel(R.drawable.logo,"BOI","xxxxxxxxxxxxx123","1234.98"));
//        addMoneyAdapter=new AddMoneyAdapter(addMoneyModels,getActivity());
//
//        recyclerViewAddMoney.setAdapter(addMoneyAdapter);

        return view;
    }

    private void addMoney(){
        progressDialog.setTitle("AdsGrocy");
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        String amount=etAddMoney.getText().toString();

        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        AddMoneyReq addMoneyReq=new AddMoneyReq();
        addMoneyReq.setAmount(amount);
        addMoneyReq.setTranferFor("addwallet");

        try{

            ApiCheck.api.getMoney(retrivedToken,addMoneyReq).enqueue(new Callback<AddMoneyResponse>() {
                @Override
                public void onResponse(Call<AddMoneyResponse> call, Response<AddMoneyResponse> response) {
                    if (response.isSuccessful()){
                        progressDialog.dismiss();
                        String getUrl=response.body().getPaymentLink();
                        Intent intent=new Intent(getContext(), AddMoneyActivity.class);
                        intent.putExtra("addmoney",getUrl);
                        startActivity(intent);

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),"Error : ",Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AddMoneyResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Response : ",Toast.LENGTH_SHORT).show();

                }
            });
        }catch (Exception e){
            e.printStackTrace();
            progressDialog.dismiss();
            Toast.makeText(getContext(),"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}