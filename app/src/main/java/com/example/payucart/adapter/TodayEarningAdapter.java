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
import com.example.payucart.fragment.TodayEarning;
import com.example.payucart.model.today_earn.TodayEarningModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TodayEarningAdapter extends RecyclerView.Adapter<TodayEarningAdapter.ViewHolder> {
    private ArrayList<TodayEarningModel> todayEarningModels;
    private Context context;

    public TodayEarningAdapter(ArrayList<TodayEarningModel> todayEarningModels, Context context) {
        this.todayEarningModels = todayEarningModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_today_earning,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodayEarningModel todayEarningModel=todayEarningModels.get(position);
        holder.imageViewVideoIcon.setImageResource(todayEarningModel.getVideoImage());
        holder.tvVideoName.setText(todayEarningModel.getVideoName());
        holder.tvVideoTime.setText(todayEarningModel.getVideoTime());
        holder.tvVideoPrice.setText(todayEarningModel.getVideoPrice());
    }

    @Override
    public int getItemCount() {
        return todayEarningModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewVideoIcon;
        private TextView tvVideoName;
        private TextView tvVideoTime;
        private TextView tvVideoPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewVideoIcon=itemView.findViewById(R.id.custom_today_earning_video_icon);

            tvVideoName=itemView.findViewById(R.id.custom_today_earning_video_name);

            tvVideoTime=itemView.findViewById(R.id.custom_today_earning_video_time);

            tvVideoPrice=itemView.findViewById(R.id.custom_today_earning_video_price);


        }
    }
}
