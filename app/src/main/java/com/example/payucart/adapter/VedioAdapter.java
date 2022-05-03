package com.example.payucart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.payucart.R;
import com.example.payucart.activity.EarnMoneyActivity;
import com.example.payucart.activity.VedioViewActivity;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.rewards.RewardResponse;
import com.example.payucart.model.video.VideoData;

import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VedioAdapter extends RecyclerView.Adapter<VedioAdapter.ViewHolder> {
    private List<VideoData> videoData;
    private Context context;

    public VedioAdapter(List<VideoData> videoData, Context context) {
        this.videoData = videoData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_viedo_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            VideoData list = videoData.get(position);

            Glide.with(context).load(list.getAdminVideo()).into(holder.imgVedioImage);
            holder.videoView.setKeepScreenOn(true);
            holder.videoView.setVideoPath(list.getAdminVideo());

            try{
                holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        holder.imgVedioImage.setVisibility(View.VISIBLE);
                        // holder.videoView.setVisibility(View.VISIBLE);
                        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                // holder.videoView.stopPlayback();
                                holder.imgVedioImage.setVisibility(View.VISIBLE);
                                //   holder.videoView.setVisibility(View.GONE);


                            }
                        });
                        holder.cardView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String url=videoData.get(position).getAdminVideo();
                                String urlID=videoData.get(position).getId();

                                Intent intent=new Intent(context,VedioViewActivity.class);
                                intent.putExtra("url", url);
                                intent.putExtra("id",urlID);
                                context.startActivity(intent);

                            }
                        });
                        holder.imgVedioImagePlay.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String url=videoData.get(position).getAdminVideo();
                                String urlID=videoData.get(position).getId();
                                Intent intent=new Intent(context,VedioViewActivity.class);
                                intent.putExtra("url", url);
                                intent.putExtra("id",urlID);
                                context.startActivity(intent);

                            }
                        });
                    }
                });
            }catch (Exception e){
                Toast.makeText(context, "Exception"+e.getMessage(),Toast.LENGTH_SHORT).show();

            }

    }

    @Override
    public int getItemCount() {
        return videoData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private VideoView videoView;
        private ImageView imgVedioImagePlay,imgVedioImage;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgVedioImage = itemView.findViewById(R.id.videoView_thumbnail);
            imgVedioImagePlay=itemView.findViewById(R.id.video_view_image_view_btn);
            videoView=itemView.findViewById(R.id.video_view_image);
            cardView=itemView.findViewById(R.id.custom_video_card_view);
        }
    }


}
