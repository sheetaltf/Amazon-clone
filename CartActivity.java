package com.example.AmazonC.Menufiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AmazonC.PlaceOrderActivity;
import com.example.AmazonC.R;
import com.example.AmazonC.viewholder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Cart;

public class CartActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private AppCompatButton nextBtn;
    RecyclerView.LayoutManager layoutManager;
    TextView totalprice;
    private int overallPrice=0;
    Toolbar cartToolbar;

    FirebaseAuth auth;


    LinearLayout dynamicContent, bottomNavBar;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_cart);

        dynamicContent= findViewById(R.id.dynamicContent);
        bottomNavBar= findViewById(R.id.bottomNavBar);
        View wizard= getLayoutInflater().inflate(R.layout.activity_cart, null);
        dynamicContent.addView(wizard);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton) findViewById(R.id.bottom_cart);

        rb.setBackgroundColor(R.color.item_selected);
        rb.setTextColor(Color.parseColor("#3F5185"));

        cartToolbar=findViewById(R.id.cart_toolbar);
        cartToolbar.setBackgroundResource(R.drawable.bg_color);

        auth=FirebaseAuth.getInstance();

        recyclerView=findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        nextBtn=findViewById(R.id.next_button);
        totalprice=findViewById(R.id.totalprice);

        nextBtn.setBackgroundResource(R.drawable.bg_color);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalprice.getText().toString().equals("₹0") || totalprice.getText().toString().length()==0) {
                    Toast.makeText(CartActivity.this, "Cannot place order with 0 items", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(CartActivity.this, PlaceOrderActivity.class);
                    intent.putExtra("totalAmount", totalprice.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options= new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User View").child(auth.getCurrentUser().getUid())
                        .child("Products"),Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter=
                new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {
                        String name=model.getName().replaceAll("\n"," ");
                        holder.cartProductName.setText(name);
                        holder.cartProductPrice.setText(model.getPrice());
                        String intPrice= model.getPrice().replace("₹","");
                        overallPrice+=Integer.valueOf(intPrice);
                        totalprice.setText("₹"+String.valueOf(overallPrice));


                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                                builder.setTitle("Delete item");
                                builder.setMessage("Do you want to remove this product from  the cart?")
                                        .setCancelable(false)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int i) {
                                                cartListRef.child("User View")
                                                        .child(auth.getCurrentUser().getUid())
                                                        .child("Products")
                                                        .child(model.getPid())
                                                        .removeValue()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    String intPrice = model.getPrice().replace("₹", "");
                                                                    overallPrice -= Integer.valueOf(intPrice);
                                                                    totalprice.setText("₹" + String.valueOf(overallPrice));
                                                                    Toast.makeText(CartActivity.this, "Item removed successfully", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                            }

                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //  Action for 'NO' Button
                                                dialog.cancel();
                                            }
                                        });
                                builder.show();

                            }
                        });

                    }


                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout,parent,false);
                        CartViewHolder holder=new CartViewHolder(view);
                        return holder;
                    }

                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}