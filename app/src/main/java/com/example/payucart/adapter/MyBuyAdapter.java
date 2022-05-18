package com.example.payucart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payucart.R;
import com.example.payucart.activity.BuyActivity;
import com.example.payucart.activity.PackageWebViewAcivity;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.interfaces.OnClickPackageList;
import com.example.payucart.model.Result;
import com.example.payucart.model.buy.BuyBody;
import com.example.payucart.model.buy.BuyModel;
import com.example.payucart.model.buy.getPackages.PackgesBody;
import com.example.payucart.model.buy.getPackages.PackgesModel;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class MyBuyAdapter extends RecyclerView.Adapter<MyBuyAdapter.ViewHolder> {
    private List<BuyBody> models;
    private Context context;
    private String sendToken = "";
    BuyBody buyBody;
    private boolean isPurchased=false;
    private TextView show_snack_txt;
    private LinearLayout snack_container;

    OnClickPackageList onClickPackageList;

    public MyBuyAdapter(List<BuyBody> models, Context context, OnClickPackageList onClickPackageList) {
        this.models = models;
        this.context = context;
        this.onClickPackageList=onClickPackageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_package,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try{
            buyBody=models.get(position);
            String price= String.valueOf(Integer.parseInt(buyBody.getPlan().toString()));
            String time= String.valueOf(Integer.parseInt(buyBody.getDailyAds().toString()));
            String money= String.valueOf(Integer.parseInt(buyBody.getCommission().toString()));
            String valditity=String.valueOf(Integer.parseInt(buyBody.getExpireIn().toString()));
            holder.tvPrice.setText("Plan : ₹ "+price);
            holder.tvTimePeriod.setText("Daily Task : "+time);
            holder.tvMoney.setText("Commission : "+money);
            holder.tvValidity.setText("Validity : "+valditity+"Days  ");
//            holder.tvBuy.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String amount=buyBody.getPlan().toString();
//                    getPackage(amount,view);
//                }
//            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int amount =models.get(position).getPlan();

                    onClickPackageList.getPackageId(models.get(position));
                    onClickPackageList.getPackageAmount(models.get(position));
                    getPackage(String.valueOf(amount),view);

                }
            });

        }catch (NumberFormatException exception){
            exception.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPrice;
        private TextView tvMoney;
        private TextView tvTimePeriod;
        private TextView tvBuy;
        private TextView tvValidity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPrice=itemView.findViewById(R.id.custom_package_price);

            tvMoney=itemView.findViewById(R.id.custom_package_money);

            tvTimePeriod=itemView.findViewById(R.id.custom_package_time_period);

            tvValidity=itemView.findViewById(R.id.custom_package_validity);

            tvBuy=itemView.findViewById(R.id.custom_package_buy_package);

            show_snack_txt=itemView.findViewById(R.id.show_snack_txt);

            snack_container =itemView.findViewById(R.id.snack_container);

//          tvBuy.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View view) {
////                   Intent intent=new Intent(itemView.getContext(), BuyActivity.class);
////                   itemView.getContext().startActivity(intent);
//
//                   String amount =buyBody.getPlan().toString();
//
//                   getPackage(amount,view);
//
//                   }
//           });
        }
    }
    private void getPackage(String money, View view){

        PackgesModel packgesModel=new PackgesModel();
//        packgesModel.();

        //Retrieve token wherever necessary
        SharedPreferences preferences = context.getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
        PackgesModel packgesModel1=new PackgesModel();
        packgesModel1.setAmount(money);
        try{
            ApiCheck.api
                    .buyPackages(retrivedToken,packgesModel1
                    )
                    .enqueue(new Callback<PackgesBody>() {
                        @Override
                        public void onResponse(Call<PackgesBody> call, Response<PackgesBody> response) {
                            PackgesBody packgesBody=response.body();
                            String token=response.headers().get("auth-token");
                            try{
                                if (response.isSuccessful()){
                                    Intent getLink=new Intent(context.getApplicationContext(), PackageWebViewAcivity.class);
                                    getLink.putExtra("payment",response.body().getPaymentLink());
                                    context.startActivity(getLink);
                                }else{
                                    Toast.makeText(context.getApplicationContext(),"Error :"+response.body().getError(),Toast.LENGTH_SHORT).show();
                                }
                            }catch(Exception e){
                                //Creating the LayoutInflater instance
//                                View view= LayoutInflater.inflate(R.layout.custom_tost);
//
//                                //Creating the Toast object
//                                Toast toast = new Toast(getApplicationContext());
//                                toast.setDuration(Toast.LENGTH_SHORT);
//                                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                                toast.setView(layout);//setting the view of custom toast layout
//                                toast.show();
                               Toast.makeText(context.getApplicationContext(),"Packages Already Buy",Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<PackgesBody> call, Throwable t) {
                            Toast.makeText(context.getApplicationContext(), "onFailure "+t.getMessage(),Toast.LENGTH_SHORT).show();
                            //Snackbar snackbar=Snackbar.make()
                        }
                    });

        }catch (Exception socketTimeoutException){
            socketTimeoutException.printStackTrace();
        }

    }


    @SuppressLint("ResourceAsColor")
    public void showSnack(String message, boolean isError, View view) {

        if (isError)
            show_snack_txt.setBackgroundColor(R.color.purple_700);
        else
            show_snack_txt.setBackgroundColor(R.color.teal_200);

        snack_container.setVisibility(View.VISIBLE);

        show_snack_txt.setText(message);
        Animation slide_down = AnimationUtils.loadAnimation(view.getRootView().getContext(),
                R.anim.slide_down);

        snack_container.startAnimation(slide_down);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideSnack(view);
            }
        }, 2000);

        //viewHolder.snack_container.setVisibility(0);
    }

    private void hideSnack(View view) {
        snack_container.setVisibility(View.GONE);
        Animation slide_up = AnimationUtils.loadAnimation(view.getRootView().getContext(),
                R.anim.slide_up);

        snack_container.startAnimation(slide_up);
    }

}
