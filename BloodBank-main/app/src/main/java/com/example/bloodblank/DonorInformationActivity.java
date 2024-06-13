package com.example.bloodblank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DonorInformationActivity extends AppCompatActivity {

    TextView donorNameTextView;
    TextView donorEmailTextView;
    TextView donorIDTextView;
    TextView donorDateOfDonationTextView;
    TextView donorBloodGroupTextView;
    TextView donorMobileNumberTextView;
    TextView donorAddressTextView;
    TextView donorPostalCodeTextView;
    TextView donorHospitalNameTextView;
    TextView donorBloodDonatedTextView;
    TextView donorGenderTextView;
    TextView donorDateOfBirthTextView;

    ImageView profilePhotoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_information);

        donorNameTextView = findViewById(R.id.donorNameTextView);
        donorEmailTextView = findViewById(R.id.donorEmailTextView);
        donorIDTextView = findViewById(R.id.donorIDTextView);
        donorDateOfDonationTextView = findViewById(R.id.donorDateOfDonationTextView);
        donorBloodGroupTextView = findViewById(R.id.donorBloodGroupTextView);
        donorMobileNumberTextView = findViewById(R.id.donorMobileNumberTextView);
        donorAddressTextView = findViewById(R.id.donorAddressTextView);
        donorPostalCodeTextView = findViewById(R.id.donorPostalCodeTextView);
        donorHospitalNameTextView = findViewById(R.id.donorHospitalNameTextView);
        donorBloodDonatedTextView = findViewById(R.id.donorBloodDonatedTextView);
        donorGenderTextView = findViewById(R.id.donorGenderTextView);
        donorDateOfBirthTextView = findViewById(R.id.donorDateOfBirthTextView);
        profilePhotoImageView = findViewById(R.id.profilePhotoImageView);

        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.name));
        String email = intent.getStringExtra(getString(R.string.email));
        String ID = intent.getStringExtra(getString(R.string.ID));
        String dateOFDonation = intent.getStringExtra(getString(R.string.date_of_donation));
        String bloodGroup = intent.getStringExtra(getString(R.string.blood_group));
        String mobileNumber = intent.getStringExtra(getString(R.string.mobile_number));
        String address = intent.getStringExtra(getString(R.string.address));
        String postalCode = intent.getStringExtra(getString(R.string.postal_code));
        String HospitalName = intent.getStringExtra(getString(R.string.hospital_name));
        String bloodDonated = intent.getStringExtra(getString(R.string.blood_donated));
        String gender = intent.getStringExtra(getString(R.string.gender));
        String dateOfBirth = intent.getStringExtra(getString(R.string.date_of_birth));

        donorNameTextView.setText(name);
        donorEmailTextView.setText(email);
        donorIDTextView.setText(getString(R.string.ID) + " : " + ID);
        donorDateOfDonationTextView.setText(getString(R.string.date_of_donation) + " : " + dateOFDonation);
        donorBloodGroupTextView.setText(getString(R.string.blood_group) + " : " + bloodGroup);
        donorMobileNumberTextView.setText(getString(R.string.mobile_number) + " : " + mobileNumber);
        donorAddressTextView.setText(getString(R.string.address) + " : " + address);
        donorPostalCodeTextView.setText(getString(R.string.postal_code) + " : " + postalCode);
        donorHospitalNameTextView.setText(getString(R.string.hospital_name) + " : " + HospitalName);
        donorBloodDonatedTextView.setText(getString(R.string.blood_donated) + " : " + bloodDonated + " mL");
        donorGenderTextView.setText(getString(R.string.gender) + " : " + gender);
        donorDateOfBirthTextView.setText(getString(R.string.date_of_birth) + " : " + dateOfBirth);

        if (gender.equals(getString(R.string.male))) {
            profilePhotoImageView.setImageDrawable(getDrawable(R.drawable.male));
        } else {
            profilePhotoImageView.setImageDrawable(getDrawable(R.drawable.female));
        }

    }
}