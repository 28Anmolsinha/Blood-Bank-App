package com.example.bloodblank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    ImageView logoImageView;
    TextView splashTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        logoImageView = findViewById(R.id.logoImageView);
        splashTextView = findViewById(R.id.splashTextView);

        logoImageView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.logo_fade_in_zoom_in));
        splashTextView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.logo_fade_in_zoom_in));

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }, 1000);
    }
}