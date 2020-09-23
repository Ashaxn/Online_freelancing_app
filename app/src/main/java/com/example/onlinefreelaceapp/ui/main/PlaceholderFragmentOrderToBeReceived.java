package com.example.onlinefreelaceapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.onlinefreelaceapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentOrderToBeReceived extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModelOrderToBeReceived pageViewModelOrderToBeReceived;

    public static PlaceholderFragmentOrderToBeReceived newInstance(int index) {
        PlaceholderFragmentOrderToBeReceived fragment = new PlaceholderFragmentOrderToBeReceived();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModelOrderToBeReceived = ViewModelProviders.of(this).get(PageViewModelOrderToBeReceived.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModelOrderToBeReceived.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_orders_to_be_received, container, false);
        final TextView textView = root.findViewById(R.id.section_label_orderstobereceived);
        pageViewModelOrderToBeReceived.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}