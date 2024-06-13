package com.example.bloodblank;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DonateBloodFragment extends Fragment {
    View fragmentView;
    EditText donationNameEditText;
    EditText donationEmailEditText;
    EditText donationPhoneNumberEditText;
    EditText donationAddressEditText;
    EditText donationPostalCodeEditText;
    EditText donationHospitalNameEditText;
    EditText donationBloodDonatedEditText;
    RadioGroup genderRadioGroup;
    TextView donationDateOfBirthTextView;
    Spinner bloodGroupSpinner;
    Button submitButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    float currentAvailable = 0;
    DatePickerDialog datePickerDialog;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_donat_blood, container, false);

        donationNameEditText = fragmentView.findViewById(R.id.donationNameEditText);
        donationEmailEditText = fragmentView.findViewById(R.id.donationEmailEditText);
        donationPhoneNumberEditText = fragmentView.findViewById(R.id.donationPhoneNumberEditText);
        donationAddressEditText = fragmentView.findViewById(R.id.donationAddressEditText);
        donationPostalCodeEditText = fragmentView.findViewById(R.id.donationPostalCodeEditText);
        donationHospitalNameEditText = fragmentView.findViewById(R.id.donationHospitalNameEditText);
        donationBloodDonatedEditText = fragmentView.findViewById(R.id.donationBloodDonatedEditText);
        genderRadioGroup = fragmentView.findViewById(R.id.genderRadioGroup);
        donationDateOfBirthTextView = fragmentView.findViewById(R.id.donationDateOfBirthTextView);
        bloodGroupSpinner = fragmentView.findViewById(R.id.spinner);
        submitButton = fragmentView.findViewById(R.id.submitButton);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        ArrayAdapter adapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, Database.getInstance().BloodGroups);
        bloodGroupSpinner.setAdapter(adapter);

        submitButton.setOnClickListener(view -> submitButtonClicked());
        donationDateOfBirthTextView.setOnClickListener(view -> pickDate());

        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Please Wait!");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        return fragmentView;
    }

    private void submitButtonClicked() {
        DonorInformation donorInformation = getDonorInformation();
        if (donorInformation == null) {
            return;
        }
        progressDialog.show();
        DatabaseReference ref = databaseReference.child(getString(R.string.DONATION)).child(donorInformation.getDonorID());
        DatabaseReference ref2 = databaseReference.child(getString(R.string.AVAILABILITY)).child(donorInformation.getBloodGroup());
        ref.child(getString(R.string.name)).setValue(donorInformation.getDonorName());
        ref.child(getString(R.string.email)).setValue(donorInformation.getEmail());
        ref.child(getString(R.string.mobile_number)).setValue(donorInformation.getMobileNumber());
        ref.child(getString(R.string.address)).setValue(donorInformation.getAddress());
        ref.child(getString(R.string.postal_code)).setValue(donorInformation.getPostalCode());
        ref.child(getString(R.string.hospital_name)).setValue(donorInformation.getHospitalName());
        ref.child(getString(R.string.blood_donated)).setValue(donorInformation.getBloodDonated());
        ref.child(getString(R.string.gender)).setValue(donorInformation.getGender());
        ref.child(getString(R.string.date_of_birth)).setValue(donorInformation.getDateOfBirth());
        ref.child(getString(R.string.blood_group)).setValue(donorInformation.getBloodGroup());
        ref.child(getString(R.string.date_of_donation)).setValue(donorInformation.getDateOfDonation());

        ref2.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                if (s == null || s.isEmpty()) {
                    currentAvailable = 0;
                } else {
                    currentAvailable = Float.parseFloat(s);
                }
                currentAvailable += Float.parseFloat(donorInformation.getBloodDonated());
                ref2.setValue(String.valueOf(currentAvailable));
                startActivity(new Intent(requireContext(), DonationAppreciation.class));
                requireActivity().finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(requireContext(), "Something went Wrong, Data not stored", Toast.LENGTH_LONG).show();
                progressDialog.cancel();
            }
        });

    }

    private void pickDate() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String date = dayOfMonth + "/" + month + "/" + year;
                donationDateOfBirthTextView.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(requireContext(), dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private DonorInformation getDonorInformation() {
        DonorInformation donorInformation;
        String name = donationNameEditText.getText().toString().trim();
        String email = donationEmailEditText.getText().toString().trim();
        String phoneNumber = donationPhoneNumberEditText.getText().toString().trim();
        String address = donationAddressEditText.getText().toString().trim();
        String postalCode = donationPostalCodeEditText.getText().toString().trim();
        String hospitalName = donationHospitalNameEditText.getText().toString().trim();
        String bloodDonated = donationBloodDonatedEditText.getText().toString().trim();
        String gender = ((RadioButton) fragmentView.findViewById(genderRadioGroup.getCheckedRadioButtonId())).getText().toString().trim();
        String dateOfBirth = donationDateOfBirthTextView.getText().toString().trim();
        String bloodGroup = bloodGroupSpinner.getSelectedItem().toString().trim();

        if (name.length() < 3) {
            donationNameEditText.setError("Name should have at least 3 letters");
            donationNameEditText.requestFocus();
        } else if (email.isEmpty()) {
            donationEmailEditText.setError(getString(R.string.donation_info_error));
            donationEmailEditText.requestFocus();
        } else if (phoneNumber.length() != 10) {
            donationPhoneNumberEditText.setError(getString(R.string.donation_info_error));
            donationPhoneNumberEditText.requestFocus();
        } else if (address.length() < 10) {
            donationAddressEditText.setError(getString(R.string.donation_info_error));
            donationAddressEditText.requestFocus();
        } else if (postalCode.length() != 6) {
            donationPostalCodeEditText.setError(getString(R.string.donation_info_error));
            donationPostalCodeEditText.requestFocus();
        } else if (hospitalName.isEmpty()) {
            donationHospitalNameEditText.setError(getString(R.string.donation_info_error));
            donationHospitalNameEditText.requestFocus();
        } else if (bloodDonated.isEmpty()) {
            donationBloodDonatedEditText.setError(getString(R.string.donation_info_error));
            donationBloodDonatedEditText.requestFocus();
        } else if (dateOfBirth.isEmpty()) {
            donationDateOfBirthTextView.setError(getString(R.string.donation_info_error));
            donationDateOfBirthTextView.requestFocus();
        } else {
            String ID = String.valueOf(System.currentTimeMillis());
            donorInformation = new DonorInformation(ID, name, email, phoneNumber, address, postalCode, hospitalName, bloodDonated, gender, dateOfBirth, bloodGroup);
            return donorInformation;
        }
        return null;
    }
}