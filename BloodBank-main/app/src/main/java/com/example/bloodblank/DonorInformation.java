package com.example.bloodblank;

import java.util.Calendar;

public class DonorInformation {
    private String donorID;
    private String donorName;
    private String email;
    private String mobileNumber;
    private String address;
    private String postalCode;
    private String hospitalName;
    private String bloodDonated;
    private String gender;
    private String dateOfBirth;
    private String bloodGroup;
    private String dateOfDonation;

    public DonorInformation() {

    }

    public DonorInformation(String donorID, String donorName, String email, String mobileNumber, String address, String postalCode, String hospitalName, String bloodDonated, String gender, String dateOfBirth, String bloodGroup) {
        this.donorID = donorID;
        this.donorName = donorName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.postalCode = postalCode;
        this.hospitalName = hospitalName;
        this.bloodDonated = bloodDonated;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month++;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        this.dateOfDonation = day + "/" + month + "/" + year;
    }

    public String getDonorID() {
        return donorID;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getBloodDonated() {
        return bloodDonated;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getDateOfDonation() {
        return dateOfDonation;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setBloodDonated(String bloodDonated) {
        this.bloodDonated = bloodDonated;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setDateOfDonation(String dateOfDonation) {
        this.dateOfDonation = dateOfDonation;
    }
}
