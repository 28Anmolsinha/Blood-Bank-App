package com.example.bloodblank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class AwarenessActivity extends AppCompatActivity {
    ViewPager awarenessViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);
        awarenessViewPager = findViewById(R.id.awarenessViewPager);

        setTitle("Blood Donation Awareness");
        AwarenessVPAdapter awarenessVPAdapter = new AwarenessVPAdapter(getSupportFragmentManager());
        awarenessVPAdapter.addFragment(new AwarenessFragment("Safe blood saves lives", "Blood is needed by women with complications during pregnancy and childbirth, children with severe anaemia, often resulting from malaria or malnutrition, accident victims and surgical and cancer patients.", false));
        awarenessVPAdapter.addFragment(new AwarenessFragment("Be a hero", "Blood is the most precious gift that anyone can give to another person. A decision to donate your blood can save a life, or even several if your blood is separated into its components – red cells, platelets and plasma – which can be used individually for patients with specific conditions.", false));
        awarenessVPAdapter.addFragment(new AwarenessFragment("Reduce risk of heart attacks and liver ailment", "Donating blood regularly is beneficial to prevent and reduce heart attacks and liver ailment. The risk of heart and liver related problem is caused by the iron overload in the body. Donating blood helps in maintaining the iron level in the body and thus reduce those risk.", false));
        awarenessVPAdapter.addFragment(new AwarenessFragment("Lower the risk of cancer", "Cancer is the most feared and deadly disease. Blood donation helps in lowering the risk of cancer. By donating blood regularly the iron level in the blood is balanced and the risk of cancer-related to the liver, lungs, and intestine gets lower.", false));
        awarenessVPAdapter.addFragment(new AwarenessFragment("Live a longer life", "The people who involve in the altruistic work have proven to live a longer life. Blood donation is altruistic works so it not only save lives of other but also helps you live longer and healthier", false));
        awarenessVPAdapter.addFragment(new AwarenessFragment("Thanks for donating!", "", true));
        awarenessViewPager.setAdapter(awarenessVPAdapter);
    }
}