package com.example.payucart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payucart.R;
import com.example.payucart.model.slider.SliderImageBody;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderImageAdapter extends SliderViewAdapter<SliderImageAdapter.ViewHolder> {
    private List<SliderImageBody> sliderImageBodies;
    private Context context;

    public SliderImageAdapter(List<SliderImageBody> sliderImageBodies, Context context) {
        this.sliderImageBodies = sliderImageBodies;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_image_slider,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        SliderImageBody sliderImageBody=sliderImageBodies.get(position);
        Picasso.get().load(sliderImageBody.getBannerImage()).into(viewHolder.imgBanner);
    }

    @Override
    public int getCount() {
        return sliderImageBodies.size();
    }


    public class ViewHolder extends SliderViewAdapter.ViewHolder {
        private ImageView imgBanner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner=itemView.findViewById(R.id.custom_image_slider);
        }
    }
}
