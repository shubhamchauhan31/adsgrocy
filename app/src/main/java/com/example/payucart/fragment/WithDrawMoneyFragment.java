package com.example.payucart.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.activity.WalletActivity;
import com.example.payucart.adapter.MyPagerAdapter;
import com.example.payucart.adapter.MyWithDrawAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.addmoney.AddMoneyReq;
import com.example.payucart.model.addmoney.AddMoneyResponse;
import com.example.payucart.model.benificial.BenificalReq;
import com.example.payucart.model.benificial.BenificialResponse;
import com.example.payucart.model.transfer.TransferResponse;
import com.example.payucart.model.withdraw.WithDrawReq;
import com.example.payucart.model.withdraw.WithDrawRes;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithDrawMoneyFragment extends Fragment {

    private RecyclerView recyclerViewWithDrawMoney;
    private List<TransferResponse> transferResponses;
    private MyWithDrawAdapter myWithDrawAdapter;
    private EditText etWithdrawAmount;
    private TextView tvWithDraw;
    private RadioGroup radioGroup;
    private RadioButton rUPI,rBank,rPaytm,rButton;
    private  String transferMode="";
    private ProgressDialog progressDialog;


    private String selectedText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view= inflater.inflate(R.layout.fragment_with_draw_money, container, false);
        recyclerViewWithDrawMoney=view.findViewById(R.id.with_draw_money_recyclerview);

        progressDialog=new ProgressDialog(getContext());
        recyclerViewWithDrawMoney.setHasFixedSize(true);
        recyclerViewWithDrawMoney.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        getAllTraction();

        tvWithDraw=view.findViewById(R.id.withdraw_money_payment_submit);
        radioGroup=view.findViewById(R.id.with_draw_radio_group);
        rPaytm=view.findViewById(R.id.withdraw_money_paytm);
        rBank=view.findViewById(R.id.with_draw_money_bank_account);
        rUPI=view.findViewById(R.id.withdraw_money_upi);
        etWithdrawAmount=view.findViewById(R.id.withdraw_money_amount);

        tvWithDraw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                progressDialog.setTitle("AdsGrocy");
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                String amount=etWithdrawAmount.getText().toString();
                if (amount.isEmpty()){
                    progressDialog.dismiss();
                    etWithdrawAmount.setError("Please Enter Amount");
                    return;
                }

                    if (rBank.isChecked()){
               transferMode="banktransfer";
               }else if (rPaytm.isChecked()){
                   transferMode="paytm";
               }else if (rUPI.isChecked()){
                   transferMode="upi";
               }
                SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

                WithDrawReq withDrawReq=new WithDrawReq();

                withDrawReq.setAmount(amount);
                withDrawReq.setTransferMode(transferMode);

            try{
                ApiCheck.api.getWithDrawMoney(retrivedToken,withDrawReq).enqueue(new Callback<WithDrawRes>() {
                    @Override
                    public void onResponse(Call<WithDrawRes> call, Response<WithDrawRes> response) {
                        if (response.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(getContext(),"Successfully withdraw ",Toast.LENGTH_SHORT).show();

                            Intent intent = getActivity().getIntent();
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            getActivity().finish();
                            startActivity(intent);

                            //Toast.makeText(getContext(),"Withdraw money : "+response.body().getStatus(),Toast.LENGTH_SHORT).show();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(getContext(),"Error  : ",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<WithDrawRes> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),"Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }catch (Exception e){
                progressDialog.dismiss();
                Toast.makeText(getContext(),"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
                        }
                    });

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
                    recyclerViewWithDrawMoney.setAdapter(myWithDrawAdapter);
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


}