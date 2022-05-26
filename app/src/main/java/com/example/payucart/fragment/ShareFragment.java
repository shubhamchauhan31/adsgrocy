package com.example.payucart.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.activity.ListShareAcivity;
import com.example.payucart.activity.ShareToFriendAcivity;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.api.ApiInterface;
import com.example.payucart.model.profile.UserResModel;
import com.example.payucart.model.referCode.ReferCodeResponse;
import com.example.payucart.model.referandearn.ReferReq;
import com.example.payucart.model.referandearn.ReferRes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShareFragment extends Fragment {

    @BindView(R.id.share_and_earn_money)
    TextView tvShareAndEarnMoney;

    @BindView(R.id.share_show_refer_list)
    TextView listReferCode;

    private TextView tvInvite;
    private TextView tvPromoCode;
    private ImageView imageViewLogo;
    private EditText etReferCodeFriend;
    private TextView tvSubmitFriendCode;
    private ProgressDialog progressDialog;
    private String inviteData;
    private String refercode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_share, container, false);
        ButterKnife.bind(this,view);
        tvInvite=view.findViewById(R.id.share_invite_friend);
        tvPromoCode=view.findViewById(R.id.share_promo_code);
        imageViewLogo=view.findViewById(R.id.share_logo);
        etReferCodeFriend=view.findViewById(R.id.share_refer_Code_by);
        tvSubmitFriendCode=view.findViewById(R.id.share_refer_code_to_earn);

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("AdsGrocy");
        progressDialog.setMessage("Loading...");
        tvSubmitFriendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //progressDialog.show();

                String friendCode=etReferCodeFriend.getText().toString();
                if (friendCode.isEmpty()){
                    etReferCodeFriend.setError("Enter Friend Code...");
                    return;
                }

                getFriendCode();


            }
        });
        getReferCodeData();
        getReferCode();
        return view;
    }
    @OnClick(R.id.share_and_earn_money) void ShareMoneyScreen(){
     //   SharePromoCode();
        startActivity(new Intent(getContext(), ShareToFriendAcivity.class).putExtra("referCode",refercode));

    }

    @OnClick(R.id.share_show_refer_list) void sharedFriendList(){
        startActivity(new Intent(getContext(), ListShareAcivity.class));
    }

    private void SharePromoCode(){

        progressDialog.dismiss();
        String invite = tvInvite.getText().toString();
        String promoCode = tvPromoCode.getText().toString();
        // int image-imageViewLogo.setImageResource(R.drawable.logo);
        String appurl="app url : "+"http://adsgrocy.com/";
        String message = invite +"\n "+ promoCode +"\n"+appurl;
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "PayUcart");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(sharingIntent, "Message"));
    }

    private void getReferCode(){
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
            @Override
            public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {
                if (response.isSuccessful()){
                     refercode=response.body().getReferCode();
                    tvPromoCode.setText(refercode);
                }
            }

            @Override
            public void onFailure(Call<UserResModel> call, Throwable t) {

                Toast.makeText(getContext()," OnFailure "+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }

    private  void getReferCodeData(){
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        ApiCheck.api.getReferData(retrivedToken).enqueue(new Callback<ReferCodeResponse>() {
            @Override
            public void onResponse(Call<ReferCodeResponse> call, Response<ReferCodeResponse> response) {
                try{
                    if (response.isSuccessful()){
                         inviteData=response.body().getData();
                        tvInvite.setText(inviteData);
                    }else{
                        Toast.makeText(getContext()," Error : "+response.body().getError(),Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(getContext(),"Exception ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReferCodeResponse> call, Throwable t) {

            }
        });
    }

    private void getFriendCode(){
        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        String friendCode=etReferCodeFriend.getText().toString();

        ReferReq referReq=new ReferReq();
        referReq.setReferBy(friendCode);
try {
    ApiCheck.api.getRefer(retrivedToken,referReq).enqueue(new Callback<ReferRes>() {
        @Override
        public void onResponse(Call<ReferRes> call, Response<ReferRes> response) {
            try{
                if (response.isSuccessful()){
                    String getResponse=response.body().getMessage();
                    Toast.makeText(getContext(), "Reward Successfully Added.... : ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Already Used "+friendCode+" Refer Code ", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(getContext(), "Already Used  "+friendCode+" Refer Codee ", Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onFailure(Call<ReferRes> call, Throwable t) {
            Toast.makeText(getContext(), "onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();

        }
    });
}catch (Exception e){
    Toast.makeText(getContext(), "Exception :  "+e.getMessage(), Toast.LENGTH_SHORT).show();

}

    }
}