package com.example.payucart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payucart.R;
import com.example.payucart.model.chart.ChartData;
import com.example.payucart.model.levelIncome.LevelIncomeModel;

import java.util.List;

public class LevelIncomeAdapter extends RecyclerView.Adapter<LevelIncomeAdapter.ViewHolder> {
    private List<ChartData> chartDataList;
    private Context context;

    public LevelIncomeAdapter(List<ChartData> chartDataList, Context context) {
        this.chartDataList = chartDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public LevelIncomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_level_income, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LevelIncomeAdapter.ViewHolder holder, int position) {
        ChartData chartData=chartDataList.get(position);
        holder.tvLevelName.setText(String.valueOf(chartDataList.get(position).getLevel()));
        holder.tvInActiveMember.setText(String.valueOf(chartDataList.get(position).getInActiveMember()));
        holder.tvActiveMember.setText(String.valueOf(chartDataList.get(position).getActiveMember()));
        holder.tvRate.setText(String.valueOf(chartDataList.get(position).getRate()));
        holder.tvLevelIncomePrice.setText(String.valueOf(chartDataList.get(position).getCommission()));

    }

    @Override
    public int getItemCount() {
        return chartDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLevelName;
        private TextView tvActiveMember;
        private TextView tvInActiveMember;
        private TextView tvRate;
        private TextView tvLevelIncomePrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLevelName = itemView.findViewById(R.id.custom_level_name);
            tvActiveMember = itemView.findViewById(R.id.custom_level_active_member);
            tvInActiveMember = itemView.findViewById(R.id.custom_level_in_active_member);
            tvRate = itemView.findViewById(R.id.custom_level_rate);
            tvLevelIncomePrice = itemView.findViewById(R.id.custom_level_daily_income);
        }
    }
}