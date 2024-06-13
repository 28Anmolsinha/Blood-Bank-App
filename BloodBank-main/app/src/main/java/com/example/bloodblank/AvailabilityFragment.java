package com.example.bloodblank;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AvailabilityFragment extends Fragment {
    RecyclerView bloodAvailabilityRecyclerView;
    BloodGroupRVAdapter bloodGroupRVAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_availability, container, false);
        bloodAvailabilityRecyclerView = fragmentView.findViewById(R.id.bloodAvailabilityRecyclerView);

        bloodGroupRVAdapter = new BloodGroupRVAdapter(requireContext(), Database.getInstance().BloodGroups);
        bloodAvailabilityRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        bloodAvailabilityRecyclerView.setAdapter(bloodGroupRVAdapter);
        return fragmentView;
    }
}