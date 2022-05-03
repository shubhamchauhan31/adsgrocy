package com.example.payucart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payucart.R;
import com.example.payucart.model.instant_payout.InstantPayoutModel;

import java.util.ArrayList;

public class InstantPayoutAdapter extends RecyclerView.Adapter<InstantPayoutAdapter.ViewHolder> {
    private ArrayList<InstantPayoutModel> instantPayoutModels;
    private Context context;

    public InstantPayoutAdapter(ArrayList<InstantPayoutModel> instantPayoutModels, Context context) {
        this.instantPayoutModels = instantPayoutModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_instant_payout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        InstantPayoutModel instantPayoutModel=instantPayoutModels.get(position);
        holder.tvWeekly.setText(instantPayoutModel.getInstantPayoutWeekly());
        holder.tvDate.setText(instantPayoutModel.getInstantPayoutDate());
        holder.tvPrice.setText(instantPayoutModel.getInstantPayoutPrice());

    }

    @Override
    public int getItemCount() {
        return instantPayoutModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvWeekly;
        private TextView tvDate;
        private TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWeekly=itemView.findViewById(R.id.custom_instant_payout_weekly);
            tvDate=itemView.findViewById(R.id.custom_instant_payout_date);
            tvPrice=itemView.findViewById(R.id.custom_instant_payucart_price);

        }
    }
}
