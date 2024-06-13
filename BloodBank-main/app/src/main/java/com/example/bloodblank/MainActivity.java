package com.example.bloodblank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.bloodDonations) {
            Intent intent = new Intent(this, BloodDonationsActivity.class);
            intent.putExtra("TYPE", "");
            startActivity(intent);
        } else if (item.getItemId() == R.id.aboutUs) {
            startActivity(new Intent(this, AboutUsActivity.class));
        }else if (item.getItemId() == R.id.awareness) {
            startActivity(new Intent(this, AwarenessActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);
        MainActivityVPAdapter adapter = new MainActivityVPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new AvailabilityFragment(), "AVAILABILITY");
        adapter.addFragment(new DonateBloodFragment(), "DONATE BLOOD");
        viewPager.setAdapter(adapter);
        Intent intent = getIntent();
        int i = intent.getIntExtra("Donation", 0);
        if(i == 1){
            viewPager.setCurrentItem(1);
        }


    }
}