package com.example.medicinapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medicinapp.R;
import com.example.medicinapp.activities.BitacoraActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class BinnacleFragment extends Fragment {

        View mView;
        FloatingActionButton mFab;

        public BinnacleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_binnacle, container, false);
        mFab = mView.findViewById(R.id.fabBitacora);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBitacora();
            }
        });
        return mView;
    }

    private void goToBitacora() {
        Intent intent = new Intent(getContext(), BitacoraActivity.class);
        startActivity(intent);
    }
}