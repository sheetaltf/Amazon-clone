package com.example.AmazonC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        auth= FirebaseAuth.getInstance();

        runNextScreen();
        askForFullScreen();
    }

    private void runNextScreen() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (auth.getCurrentUser() == null) {
                    startActivity(new Intent(SplashScreen.this, IntroActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                    finish();
                }
            }
        }, 2500);
    }
    private void askForFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(
                          View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }
}