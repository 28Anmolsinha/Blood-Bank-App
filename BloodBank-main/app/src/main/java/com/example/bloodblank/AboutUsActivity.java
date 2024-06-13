package com.example.bloodblank;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    View developerAnmolCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().hide();

        developerAnmolCard = findViewById(R.id.developerAnmolCard);


        ((TextView) developerAnmolCard.findViewById(R.id.developerNameTextView)).setText("Anmol Sinha");
        ((TextView) developerAnmolCard.findViewById(R.id.developerEmailTextView)).setText("mailmeatanmol@gmail.com");
        ((TextView) developerAnmolCard.findViewById(R.id.developerPhoneNumberEmailTextView)).setText("+91 8509741481");
        ((TextView) developerAnmolCard.findViewById(R.id.developerRollNumberEmailTextView)).setText("2020UCB6038");


    }
}