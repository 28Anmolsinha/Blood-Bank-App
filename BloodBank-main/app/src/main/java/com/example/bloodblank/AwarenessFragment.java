package com.example.bloodblank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AwarenessFragment extends Fragment {
    private TextView textView;
    private TextView titleTextView;
    private Button awarenessButton;
    private ImageButton awarenessNextButton;
    String titleText = "";
    String text = "";
    boolean buttonEnabled;

    public AwarenessFragment(String titleText, String text, boolean buttonEnabled) {
        this.titleText = titleText;
        this.text = text;
        this.buttonEnabled = buttonEnabled;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_awareness, container, false);
        textView = view.findViewById(R.id.awarenessTextView);
        titleTextView = view.findViewById(R.id.awarenessTitleTextView);
        awarenessButton = view.findViewById(R.id.awarenessButton);
        awarenessNextButton = view.findViewById(R.id.awarenessNextButton);

        titleTextView.setText(titleText);
        textView.setText(text);

        awarenessButton.setVisibility(buttonEnabled ? View.VISIBLE : View.GONE);
        awarenessNextButton.setVisibility(!buttonEnabled ? View.VISIBLE : View.GONE);

        awarenessButton.setOnClickListener(view2 -> {
            requireActivity().finish();
        });

        awarenessNextButton.setOnClickListener(view2 -> {
            ViewPager viewPager = ((AwarenessActivity) requireActivity()).awarenessViewPager;
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        });
        return view;
    }
}