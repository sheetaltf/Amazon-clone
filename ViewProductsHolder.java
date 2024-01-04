package com.example.AmazonC.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AmazonC.R;
import com.example.AmazonC.interfaces.ItemClickListener;

public class ViewProductsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView addProudName,addProudPrice;
    private ItemClickListener itemClickListener;
    public ImageView addProudImg;


    public ViewProductsHolder(@NonNull View itemView) {
        super(itemView);

        addProudName= itemView.findViewById(R.id.prod_name);
        addProudPrice= itemView.findViewById(R.id.prod_price);
        addProudImg= itemView.findViewById(R.id.prod_img);


    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false );

    }

    public void  setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
}
