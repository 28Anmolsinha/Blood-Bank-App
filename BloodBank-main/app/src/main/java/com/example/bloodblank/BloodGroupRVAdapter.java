package com.example.bloodblank;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BloodGroupRVAdapter extends RecyclerView.Adapter<BloodGroupRVAdapter.BloodGroupRVHolder> {
    Context context;
    String[] bloodGroups = {};
    String[] listOfColors = {"#C50701", "#D80801", "#FF0800", "#FD1C1C", "#FB3808", "#FD4518", "#FD5228", "#FD623C", "#FD623C", "#FD623C"};

    public BloodGroupRVAdapter(Context context, String[] bloodGroups) {
        this.context = context;
        this.bloodGroups = bloodGroups;
    }

    @NonNull
    @Override
    public BloodGroupRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.blood_group_available_layout, parent, false);
        return new BloodGroupRVHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodGroupRVHolder holder, int position) {
        String bloodGroup = bloodGroups[holder.getAdapterPosition()];
        holder.bloodGroupTextView.setText(bloodGroup);
        holder.cardView.setCardBackgroundColor(Color.parseColor(listOfColors[holder.getAdapterPosition()]));
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in_zoom_in));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(context.getString(R.string.AVAILABILITY)).child(bloodGroup);
        databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                String str = dataSnapshot.getValue(String.class);
                if (str == null || str.isEmpty()) {
                    str = "0";
                    databaseReference.setValue(str);
                }
                holder.bloodGroupAvailableTextView.setText(str + " mL");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                holder.bloodGroupAvailableTextView.setText("-");
            }
        });
    }

    @Override
    public int getItemCount() {
        return bloodGroups.length;
    }

    public class BloodGroupRVHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView bloodGroupTextView;
        TextView bloodGroupAvailableTextView;
        CardView cardView;

        public BloodGroupRVHolder(@NonNull View itemView) {
            super(itemView);
            bloodGroupTextView = itemView.findViewById(R.id.bloodGroupTextView);
            bloodGroupAvailableTextView = itemView.findViewById(R.id.bloodGroupAvailableTextView);
            cardView = itemView.findViewById(R.id.cardView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String str = ((TextView) v.findViewById(R.id.bloodGroupTextView)).getText().toString();
            Intent intent = new Intent(context, BloodDonationsActivity.class);
            intent.putExtra("TYPE", str);
            context.startActivity(intent);
        }
    }
}
