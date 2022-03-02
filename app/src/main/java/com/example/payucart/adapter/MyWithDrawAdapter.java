package com.example.payucart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payucart.R;
import com.example.payucart.model.transfer.TransferResponse;
import com.example.payucart.model.withdraw.WithDrawReq;

import java.util.List;

public class MyWithDrawAdapter extends RecyclerView.Adapter<MyWithDrawAdapter.ViewHolder> {
    private List<TransferResponse> withDrawReqs;
    private Context context;

    public MyWithDrawAdapter(List<TransferResponse> withDrawReqs, Context context) {
        this.withDrawReqs = withDrawReqs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_waithdraw,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransferResponse drawReq=withDrawReqs.get(position);
        holder.tvAmount.setText("₹ "+drawReq.getAmount());
//        holder.tvWithDrawUserName.setText(drawReq.getUsers());
        holder.tvRemark.setText(drawReq.getRemark());

    }

    @Override
    public int getItemCount() {
        return withDrawReqs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAmount,tvWithDrawUserName,tvRemark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAmount=itemView.findViewById(R.id.custom_with_draw_amount);
//            tvWithDrawUserName=itemView.findViewById(R.id.withdraw_paid_to_user_name);
            tvRemark=itemView.findViewById(R.id.with_draw_status);
        }
    }
}
