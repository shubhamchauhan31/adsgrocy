package com.example.payucart.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payucart.R;
import com.example.payucart.model.shareListModel.ReferListModel;

import org.w3c.dom.Text;

import java.util.List;

public class ShareListAdapter extends RecyclerView.Adapter<ShareListAdapter.ViewHolder> {
    private List<ReferListModel> referListModels;
    private Context context;

    public ShareListAdapter(List<ReferListModel> referListModels, Context context) {
        this.referListModels = referListModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_share_app_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvUserName.setText(referListModels.get(position).getReferListName());
        holder.tvUserPrice.setText(referListModels.get(position).getReferListPrice());
        boolean isExpandable=referListModels.get(position).isExpandable();
        holder.cardViewSubCategory.setVisibility(isExpandable ? View.VISIBLE :View.GONE);

    }

    @Override
    public int getItemCount() {
        return referListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userImage;
        private TextView tvUserName;
        private TextView tvUserPrice;
        private CardView cardViewSubCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            userImage=itemView.findViewById(R.id.)
            tvUserName=itemView.findViewById(R.id.custom_share_app_name);
            tvUserPrice=itemView.findViewById(R.id.custom_share_app_price);
            cardViewSubCategory=itemView.findViewById(R.id.custom_share_app_label_cardView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    cardViewSubCategory.setVisibility(View.VISIBLE);
                    ReferListModel referListModel=referListModels.get(getAdapterPosition());
                    referListModel.setExpandable(!referListModel.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
