package com.example.payucart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payucart.R;
import com.example.payucart.model.addmoney.AddMoneyModel;

import java.util.List;

public class AddMoneyAdapter extends RecyclerView.Adapter<AddMoneyAdapter.ViewHolder> {
    private List<AddMoneyModel> addMoneyModels;
    private Context context;

    public AddMoneyAdapter(List<AddMoneyModel> addMoneyModels, Context context) {
        this.addMoneyModels = addMoneyModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_add_money_layoout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AddMoneyModel add=addMoneyModels.get(position);
        holder.imageViewBank.setImageResource(add.getBankImage());
        holder.tvBankUserName.setText(add.getBankUserName());
        holder.tvBankUserAccountNumber.setText(add.getBankUserAccountNumber());
        holder.tvBankUserAmount.setText(add.getBankUserName());

    }

    @Override
    public int getItemCount() {
        return addMoneyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private ImageView imageViewBank;
       private TextView tvBankUserName;
       private TextView tvBankUserAccountNumber;
       private TextView tvBankUserAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewBank=itemView.findViewById(R.id.custom_add_money_bank_image_view_tv);
            tvBankUserName=itemView.findViewById(R.id.custom_add_money_user_name_tv);
            tvBankUserAccountNumber=itemView.findViewById(R.id.custom_add_money_account_number_tv);
            tvBankUserAmount=itemView.findViewById(R.id.custom_add_money_price_tv);
        }
    }

}
