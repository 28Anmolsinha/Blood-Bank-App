package com.example.bloodblank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BloodDonationsActivity extends AppCompatActivity {

    RecyclerView bloodDonationsRecyclerView;
    DonationRVAdapter donationRVAdapter;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    View noDonationAvailable;

    ArrayList<DonorInformation> listOfDonor = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donations);
        bloodDonationsRecyclerView = findViewById(R.id.bloodDonationsRecyclerView);
        noDonationAvailable = findViewById(R.id.noDonationAvailable);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(getString(R.string.DONATION));
        initRecyclerView();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait.");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Intent intent = getIntent();
        String str = intent.getStringExtra("TYPE");
        if (str.isEmpty()) {
            setTitle("All Blood Donations");
        } else {
            setTitle(str + " Blood Donations");
        }

        databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    if (!str.isEmpty() && !snap.child(getString(R.string.blood_group)).getValue(String.class).equals(str)) {
                        continue;
                    }
                    DonorInformation donorInformation = new DonorInformation();
                    donorInformation.setDonorID(snap.getKey());
                    donorInformation.setDonorName(snap.child(getString(R.string.name)).getValue(String.class));
                    donorInformation.setEmail(snap.child(getString(R.string.email)).getValue(String.class));
                    donorInformation.setMobileNumber(snap.child(getString(R.string.mobile_number)).getValue(String.class));
                    donorInformation.setAddress(snap.child(getString(R.string.address)).getValue(String.class));
                    donorInformation.setPostalCode(snap.child(getString(R.string.postal_code)).getValue(String.class));
                    donorInformation.setHospitalName(snap.child(getString(R.string.hospital_name)).getValue(String.class));
                    donorInformation.setBloodDonated(snap.child(getString(R.string.blood_donated)).getValue(String.class));
                    donorInformation.setGender(snap.child(getString(R.string.gender)).getValue(String.class));
                    donorInformation.setDateOfBirth(snap.child(getString(R.string.date_of_birth)).getValue(String.class));
                    donorInformation.setBloodGroup(snap.child(getString(R.string.blood_group)).getValue(String.class));
                    donorInformation.setDateOfDonation(snap.child(getString(R.string.date_of_donation)).getValue(String.class));
                    listOfDonor.add(donorInformation);
                }
                donationRVAdapter.notifyDataSetChanged();
                if (listOfDonor.size() == 0) {
                    noDonationAvailable.setVisibility(View.VISIBLE);
                    Button btn = noDonationAvailable.findViewById(R.id.donateButton);
                    btn.setOnClickListener(view -> {
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        intent1.putExtra("Donation", 1);
                        startActivity(intent1);
                        finish();
                    });
                } else {
                    noDonationAvailable.setVisibility(View.GONE);
                }
                progressDialog.cancel();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.cancel();
            }
        });
    }

    private void initRecyclerView() {
        donationRVAdapter = new DonationRVAdapter(this, listOfDonor);
        bloodDonationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bloodDonationsRecyclerView.setAdapter(donationRVAdapter);
    }
}