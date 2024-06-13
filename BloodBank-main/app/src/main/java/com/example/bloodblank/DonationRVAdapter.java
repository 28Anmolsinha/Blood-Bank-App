package com.example.bloodblank;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonationRVAdapter extends RecyclerView.Adapter<DonationRVAdapter.DonationViewHolder> {

    ArrayList<DonorInformation> listOfDonors;
    Context context;

    public DonationRVAdapter(Context context, ArrayList<DonorInformation> listOfDonors) {
        this.context = context;
        this.listOfDonors = listOfDonors;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.donor_information_card, parent, false);
        return new DonationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        String text = listOfDonors.get(position).getDonorName().toUpperCase() + " has donated " + listOfDonors.get(position).getBloodDonated() + " mL of " + listOfDonors.get(position).getBloodGroup() + " blood group on " + listOfDonors.get(position).getDateOfDonation() + ".";
        holder.textView.setText(text);
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in_zoom_in));
        if (listOfDonors.get(position).getGender().equals(context.getString(R.string.male))) {
            holder.profilePhotoImageView.setImageDrawable(context.getDrawable(R.drawable.male));
        } else {
            holder.profilePhotoImageView.setImageDrawable(context.getDrawable(R.drawable.female));
        }
    }

    @Override
    public int getItemCount() {
        return listOfDonors.size();
    }

    public class DonationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        ImageView profilePhotoImageView;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = itemView.findViewById(R.id.textView);
            profilePhotoImageView = itemView.findViewById(R.id.profilePhotoImageView);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DonorInformationActivity.class);
            intent.putExtra(context.getString(R.string.ID), listOfDonors.get(getAdapterPosition()).getDonorID());
            intent.putExtra(context.getString(R.string.name), listOfDonors.get(getAdapterPosition()).getDonorName());
            intent.putExtra(context.getString(R.string.email), listOfDonors.get(getAdapterPosition()).getEmail());
            intent.putExtra(context.getString(R.string.address), listOfDonors.get(getAdapterPosition()).getAddress());
            intent.putExtra(context.getString(R.string.mobile_number), listOfDonors.get(getAdapterPosition()).getMobileNumber());
            intent.putExtra(context.getString(R.string.postal_code), listOfDonors.get(getAdapterPosition()).getPostalCode());
            intent.putExtra(context.getString(R.string.hospital_name), listOfDonors.get(getAdapterPosition()).getHospitalName());
            intent.putExtra(context.getString(R.string.blood_donated), listOfDonors.get(getAdapterPosition()).getBloodDonated());
            intent.putExtra(context.getString(R.string.blood_group), listOfDonors.get(getAdapterPosition()).getBloodGroup());
            intent.putExtra(context.getString(R.string.gender), listOfDonors.get(getAdapterPosition()).getGender());
            intent.putExtra(context.getString(R.string.date_of_birth), listOfDonors.get(getAdapterPosition()).getDateOfBirth());
            intent.putExtra(context.getString(R.string.date_of_donation), listOfDonors.get(getAdapterPosition()).getDateOfDonation());
            context.startActivity(intent);
        }
    }
}

