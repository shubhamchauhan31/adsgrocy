package com.example.payucart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payucart.R;
import com.example.payucart.activity.ScratchCard;
import com.example.payucart.activity.ScratchCardDetails;
import com.example.payucart.model.scratchCard.ScratchCardData;
import com.example.payucart.model.scratchModel.ScretchCardModel;
import com.example.payucart.scratchCard.Utils;

import java.util.ArrayList;
import java.util.List;


public class ScratchCardAdapter extends RecyclerView.Adapter<ScratchCardAdapter.ViewHolder> {
    private Context context;
    private List<ScratchCardData> list;
    private String price;
//    private ScratchCard mScratchCard;

    public ScratchCardAdapter(Context context, List<ScratchCardData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_scratch_card,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ScratchCardData scretchCardModel=list.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price=list.get(position).getPrice();
                String title=list.get(position).getTitle();
                String  limit= String.valueOf(list.get(position).getLimit());
                String id=String.valueOf(list.get(position).getId());

                Intent intent=new Intent(context,ScratchCardDetails.class);
                    intent.putExtra("price",price);
                    intent.putExtra("title",title);
                    intent.putExtra("limit",limit);
                    intent.putExtra("id",id);

                    context.startActivity(intent);

            }
        });



//        holder.codeTxt.setText(scretchCardModel.getAmount());
//        holder.codeTxt.setImageResource(scretchCardModel.getImage());

//        String number =holder.codeTxt.getText().toString();
//        holder.codeTxt.setText(number);
//        holder.codeTxt.setText(Utils.generateNewCode());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView codeTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//           mScratchCard=itemView.findViewById(R.id.custom_scratchCard);
            codeTxt=itemView.findViewById(R.id.custom_codeTxt);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    context.startActivity(new Intent(context,ScratchCardDetails.class));
//                    Intent intent=new Intent(context,ScratchCardDetails.class);
//                    intent.putExtra("price",price);
//                    context.startActivity(intent);
//
//                }
//            });

//            mScratchCard.setOnScratchListener(new ScratchCard.OnScratchListener() {
//                @Override
//                public void onScratch(ScratchCard scratchCard, float visiblePercent) {
//                    if (visiblePercent > 0.8) {
//                        scratch(true);
//                    }
//                }
//            });

        }
    }

//    private void scratch(boolean isScratched) {
//        if (isScratched) {
//            mScratchCard.setVisibility(View.INVISIBLE);
//        } else {
//            mScratchCard.setVisibility(View.VISIBLE);
//        }
//    }

}
