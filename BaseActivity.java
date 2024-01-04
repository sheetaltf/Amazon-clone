package com.example.AmazonC.Menufiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.AmazonC.HomeActivity;
import com.example.AmazonC.R;

public class BaseActivity extends AppCompatActivity {

    RadioGroup radioGroup1;
    RadioButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup1);
        home = (RadioButton)findViewById(R.id.bottom_home);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                Intent in;

                    if (i==R.id.bottom_home) {
                        in = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);

                    } else if (i==R.id.bottom_addprod) {
                        in = new Intent(getBaseContext(), AddProduct.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);

                    } else if(i==R.id.bottom_search) {
                        in = new Intent(getBaseContext(), SearchActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);

                    }else if(i==R.id.bottom_cart) {
                        in = new Intent(getBaseContext(), CartActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);

                    }else if(i== R.id.bottom_profile) {
                        in = new Intent(getBaseContext(), ProfileActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);

                    }
                }

        });
    }
}
