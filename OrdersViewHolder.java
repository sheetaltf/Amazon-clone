package com.example.AmazonC.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AmazonC.R;
import com.example.AmazonC.interfaces.ItemClickListener;

public class OrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView orderName, orderDate, orderAddr, orderCity, orderPrice, orderPhone;
    private ItemClickListener itemClickListener;
    public OrdersViewHolder(@NonNull android.view.View itemView) {
        super(itemView);

        orderName=itemView.findViewById(R.id.orderName);
        orderPhone=itemView.findViewById(R.id.orderPhone);
        orderAddr=itemView.findViewById(R.id.orderAddr);
        orderCity=itemView.findViewById(R.id.orderCity);
        orderDate=itemView.findViewById(R.id.orderDate);
        orderPrice=itemView.findViewById(R.id.orderPrice);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}

