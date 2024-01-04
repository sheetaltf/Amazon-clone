package com.example.AmazonC.Menufiles;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;


import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.AmazonC.HomeActivity;
import com.example.AmazonC.R;
import com.example.AmazonC.ProductDetails;
import com.example.AmazonC.viewholder.ViewProductsHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import Model.AddProdModel;

public class SearchActivity extends BaseActivity {

    EditText inputText;
    AppCompatButton searchBtn;
    RecyclerView searchList;
    String searchInput;
    ImageView backHome;

    Toolbar viewToolbar;
    LinearLayout dynamicContent, bottonNavBar;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_search);


        dynamicContent = findViewById(R.id.dynamicContent);
        bottonNavBar = findViewById(R.id.bottomNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_search, null);
        dynamicContent.addView(wizard);


        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton) findViewById(R.id.bottom_search);

        rb.setBackgroundColor(R.color.item_selected);
        rb.setTextColor(Color.parseColor("#3F5185"));


        viewToolbar = findViewById(R.id.viewtoolbar);
        viewToolbar.setBackgroundResource(R.drawable.bg_color);

        inputText = findViewById(R.id.searchEditText);
        searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setBackgroundResource(R.drawable.intro_signin);

        searchList = findViewById(R.id.searchList);
        backHome = findViewById(R.id.backHome);
        searchList.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInput = inputText.getEditableText().toString();
                onStart();
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference prodListRef = FirebaseDatabase.getInstance().getReference().child("View All")
                .child("User View").child("Products");

        FirebaseRecyclerOptions<AddProdModel> options = new FirebaseRecyclerOptions.Builder<AddProdModel>()
                .setQuery(prodListRef.orderByChild("name").startAt(searchInput), AddProdModel.class).build();

        FirebaseRecyclerAdapter<AddProdModel, ViewProductsHolder> adapter =
                new FirebaseRecyclerAdapter<AddProdModel, ViewProductsHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewProductsHolder holder, int position, @NonNull AddProdModel model) {
                        String name = model.getName().replaceAll("\n", " ");
                        String price = model.getPrice();
                        String imgUri = model.getImg();

                        holder.addProudName.setText(name);
                        holder.addProudPrice.setText(price);

                        Picasso.get().load(imgUri).into(holder.addProudImg);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(SearchActivity.this, ProductDetails.class);
                                intent.putExtra("id", 4);
                                intent.putExtra("uniqueId", name);
                                intent.putExtra("addProdName", name);
                                intent.putExtra("addProdPrice", price);
                                intent.putExtra("addProdDesc", model.getDescription());
                                intent.putExtra("addProdCategory", model.getCategory());
                                intent.putExtra("img", imgUri);
                                startActivity(intent);
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public ViewProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_products_adapter,parent, false);
                        ViewProductsHolder holder= new ViewProductsHolder(view);
                        return holder;

                    }
                };

        searchList.setAdapter(adapter);
        adapter.startListening();
    }

}