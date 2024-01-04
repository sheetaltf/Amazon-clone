package com.example.AmazonC;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


import com.example.AmazonC.Menufiles.BaseActivity;
import com.example.AmazonC.Menufiles.CartActivity;
import com.example.AmazonC.Menufiles.SearchActivity;
import com.example.AmazonC.constant.Constant;
import com.example.AmazonC.viewholder.ProductAdapter;
import com.example.AmazonC.viewholder.SliderAdapter;
import com.example.AmazonC.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import Model.Product;

public class HomeActivity extends BaseActivity {

    Toolbar toolbar;
    SliderView sliderView;
    int[] images= {R.drawable.one,R.drawable.two,
                   R.drawable.three,R.drawable.four,};


    CardView shoes1, shoes2, shoes3, shoes4, shoes5;

    TextView oddshoename, oddshoeprice, evenshoename, evenshoeprice, viewAll;

    LinearLayout dynamicContent;
    LinearLayout bottonNavBar;

    public static ImageView home_cart;
    Intent intentCart;
    String getcartupadte;

    FirebaseStorage storage;
    StorageReference storageReference;




    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //    setContentView(R.layout.activity_home);

        dynamicContent = findViewById(R.id.dynamicContent);
        bottonNavBar = findViewById(R.id.bottomNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_home, null);
        dynamicContent.addView(wizard);


        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton) findViewById(R.id.bottom_home);

        rb.setBackgroundColor(R.color.item_selected);

        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundResource(R.drawable.bg_color);

        shoes1 = findViewById(R.id.shoes1);
        shoes2 = findViewById(R.id.shoes2);
        shoes3 = findViewById(R.id.shoes3);
        shoes4 = findViewById(R.id.shoes4);
        shoes5 = findViewById(R.id.shoes5);

        oddshoename = findViewById(R.id.oddshoename);
        oddshoeprice = findViewById(R.id.oddshoeprice);
        evenshoename = findViewById(R.id.evenshoename);
        evenshoeprice = findViewById(R.id.evenshoeprice);

        viewAll = findViewById(R.id.viewAllProducts);
        home_cart = findViewById(R.id.home_cart);

        intentCart= getIntent();
        if(intentCart.getStringExtra("cartadd")!=null && intentCart.getStringExtra("cartadd").equals("yes")){
            home_cart.setImageResource(R.drawable.cart_notif);
        }else if (intentCart.getStringExtra("cartadd")!= null && intentCart.getStringExtra("cartadd").equals("no")){
            home_cart.setImageResource(R.drawable.cart);
        }else {
            home_cart.setImageResource(R.drawable.cart);
        }

        storage = FirebaseStorage.getInstance();

        shoes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", oddshoename.getText().toString());
                i.putExtra("category", "Men's Running Shoes");
                i.putExtra("price", oddshoeprice.getText().toString());
                i.putExtra("uniqueId", oddshoename.getText().toString());
                i.putExtra("id", 1);
                startActivity(i);
            }
        });

        shoes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", oddshoename.getText().toString());
                i.putExtra("category", "Men's Running Shoes");
                i.putExtra("price", oddshoeprice.getText().toString());
                i.putExtra("uniqueId", oddshoename.getText().toString());
                i.putExtra("id", 1);
                startActivity(i);
            }
        });

        shoes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", oddshoename.getText().toString());
                i.putExtra("category", "Men's Running Shoes");
                i.putExtra("price", oddshoeprice.getText().toString());
                i.putExtra("uniqueId", oddshoename.getText().toString());
                i.putExtra("id", 1);
                startActivity(i);
            }
        });

        shoes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", evenshoename.getText().toString());
                i.putExtra("category", "Men's Shoes");
                i.putExtra("price", evenshoeprice.getText().toString());
                i.putExtra("uniqueId", evenshoename.getText().toString());
                i.putExtra("id", 2);
                startActivity(i);
            }
        });

        shoes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ProductDetails.class);
                i.putExtra("name", oddshoename.getText().toString());
                i.putExtra("category", "Men's Running Shoes");
                i.putExtra("price", oddshoeprice.getText().toString());
                i.putExtra("uniqueId", oddshoename.getText().toString());
                i.putExtra("id", 1);
                startActivity(i);
            }
        });

        ListView lvProducts= findViewById(R.id.productslist);
        ProductAdapter productAdapter = new ProductAdapter(this);
        productAdapter.updateProducts(Constant.PRODUCT_LIST);

        lvProducts.setAdapter(productAdapter);

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = Constant.PRODUCT_LIST.get(position);
                Intent intent = new Intent(HomeActivity.this, ProductDetails.class);
                intent.putExtra("id", 3);
                intent.putExtra("uniqueId", product.getpName());
                intent.putExtra("name", product.getpName());
                intent.putExtra("description", product.getpDescription());
                intent.putExtra("category", "Smartphone");
                intent.putExtra("pprice", Constant.CURRENCY + String.valueOf(product.getpPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
                intent.putExtra("imageName", product.getpImageName());
                startActivity(intent);
            }
        });
                home_cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                        startActivity(intent);
                    }
                });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        addingToProdList();
    }

    private void addingToProdList() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());


        DatabaseReference prodListRef = FirebaseDatabase.getInstance().getReference().child("View All");
        String name = evenshoename.getText().toString().replaceAll("\n","");

        final HashMap<String, Object> prodMap = new HashMap<>();
        prodMap.put("pid", name);
        prodMap.put("name", name);
        prodMap.put("price", "₹9500");
        prodMap.put("category", "shoes");
        prodMap.put("description", "Nike Air technology consists of pressurised air inside a tough yet flexible bag, and provides more flexibility and spring without compromising structure");
        prodMap.put("date", saveCurrentDate);
        prodMap.put("time", saveCurrentTime);


        prodListRef.child("User View").child("Products").child(name).updateChildren(prodMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.i("Task", "successfull");

                        }
                    }
                });
        }

}