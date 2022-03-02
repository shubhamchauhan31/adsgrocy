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
import com.example.payucart.model.completed.CompletedTask;

import java.util.ArrayList;

public class CompletedTaskAdapter extends RecyclerView.Adapter<CompletedTaskAdapter.ViewHolder> {
    private ArrayList<CompletedTask> completedTasks;
    private Context context;

    public CompletedTaskAdapter(ArrayList<CompletedTask> completedTasks, Context context) {
        this.completedTasks = completedTasks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_comppleted_task,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CompletedTask completedTask=completedTasks.get(position);
        holder.imageViewCompletedTaskVideo.setImageResource(completedTask.getVideoImage());
        holder.tvCompletedTaskVideoName.setText(completedTask.getVideoName());
        holder.tvCompletedTaskVideoTime.setText(completedTask.getVideoTime());
    }

    @Override
    public int getItemCount() {
        return completedTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewCompletedTaskVideo;
        private TextView tvCompletedTaskVideoName;
        private TextView tvCompletedTaskVideoTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCompletedTaskVideo=itemView.findViewById(R.id.custom_completed_task_image_view);
            tvCompletedTaskVideoName=itemView.findViewById(R.id.custom_completed_task_video);
            tvCompletedTaskVideoTime=itemView.findViewById(R.id.custom_completed_task_video_time);

        }
    }
}
